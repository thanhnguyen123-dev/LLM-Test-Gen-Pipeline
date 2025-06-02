import os
import subprocess
import csv
from pathlib import Path
from openai_service import client

CSV_INPUT_PATH  = (
    Path(__file__).resolve().parent.parent.parent
    / "Data"
    / "Formatted_Generated_Test_Data.csv"
)
CSV_OUTPUT_PATH = (
    Path(__file__).resolve().parent.parent.parent
    / "Data"
    / "Runnable_Test_Data.csv"
)

# The root of your workspace (folder containing lang_1_buggy/ and LLM_Test_Gen/)
WORKSPACE_ROOT = Path(__file__).resolve().parent.parent.parent.parent

# The directory where each test .java lives:
SRC_TEST_ROOT  = WORKSPACE_ROOT / "lang_1_buggy" / "src" / "test" / "java"

# Compile‐output directory (we’ll compile each test into target/test-classes)
TARGET_TEST_CLASSES = WORKSPACE_ROOT / "lang_1_buggy" / "target" / "test-classes"

MAX_ITERATIONS = 3

REFINEMENT_PROMPT_TEMPLATE_PATH = (
    Path(__file__).resolve().parent.parent.parent
    / "Data" / "Prompts" / "refinement_template.txt"
)
# ────────────────────────────────────────────────────────────────────────────────


def compile_single_test(file_path: Path):
    """
    Compile exactly one JUnit test file (file_path) using javac.
    - Adds `target/classes` + `junit-4.13.2.jar` to the classpath.
    - Writes all resulting .class files (outer + inner) into target/test-classes/<same package>.
    Returns: (True, "") if compilation succeeded, or (False, stderr_text) if it failed.
    """
    # Make sure main code is already compiled (target/classes exists)
    target_classes = WORKSPACE_ROOT / "lang_1_buggy" / "target" / "classes"
    if not target_classes.exists():
        return (False, "Missing target/classes (run `mvn compile` first)")

    # 2Locate JUnit 4 JAR in the local ~/.m2 repo:
    junit_jar = (
        Path.home()
        / ".m2"
        / "repository"
        / "junit"
        / "junit"
        / "4.13.2"
        / "junit-4.13.2.jar"
    )
    if not junit_jar.exists():
        return (False, f"Missing JUnit JAR: {junit_jar}")

    # Build classpath string: "target/classes:<path to junit-4.13.2.jar>"
    cp_entries = [ str(target_classes), str(junit_jar)]
    classpath = os.pathsep.join(cp_entries)

    # Ensure our target/test-classes folder exists (javac -d will place .class files there)
    TARGET_TEST_CLASSES.mkdir(parents=True, exist_ok=True)

    # Run javac on exactly one file:
    #    -cp <classpath> 
    #    -d <target/test-classes> 
    #    <that test file>
    p = subprocess.run(
        [
            "javac",
            "-cp", classpath,
            "-d", str(TARGET_TEST_CLASSES),
            str(file_path)
        ],
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE
    )
    stderr_text = p.stderr.decode("utf-8", errors="ignore")
    return (p.returncode == 0, stderr_text)


def load_refinement_template() -> str:
    """
    Read the prompt template (must contain %%TEST_CODE%% and %%ERROR_MSGS%%).
    If the file does not exist, return None.
    """
    if not REFINEMENT_PROMPT_TEMPLATE_PATH.exists():
        print(f"[WARN] No refinement template at {REFINEMENT_PROMPT_TEMPLATE_PATH}")
        return None
    return REFINEMENT_PROMPT_TEMPLATE_PATH.read_text(encoding="utf-8")


def build_refinement_prompt(failing_test_code: str, compiler_errors: str) -> str:
    """
    Substitute %%TEST_CODE%% and %%ERROR_MSGS%% into the template.
    """
    template = load_refinement_template()
    if not template:
        raise FileNotFoundError(f"No template at {REFINEMENT_PROMPT_TEMPLATE_PATH}")
    s = template.replace("%%TEST_CODE%%", failing_test_code)
    s = s.replace("%%ERROR_MSGS%%", compiler_errors)
    return s


def read_entire_file(path: Path) -> str:
    """
    Return the entire contents of the file at `path`.
    """
    return path.read_text(encoding="utf-8")


def write_entire_file(path: Path, contents: str) -> None:
    """
    Overwrite (or create) the file at `path` with exactly `contents`.
    """
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(contents, encoding="utf-8")


def main():
    """
    Implements the “feedback loop” (Task 5) as specified.
    """
    print(f"[INFO] Running feedback_loop.py")
    print(f"[INFO] CSV_INPUT_PATH  = {CSV_INPUT_PATH}")
    print(f"[INFO] CSV_OUTPUT_PATH = {CSV_OUTPUT_PATH}")
    print(f"[INFO] SRC_TEST_ROOT   = {SRC_TEST_ROOT}\n")

    if not CSV_INPUT_PATH.exists():
        print(f"[ERROR] Input CSV not found: {CSV_INPUT_PATH}")
        return

    # Read all rows from the input CSV
    all_rows = []
    with CSV_INPUT_PATH.open(newline="", encoding="utf-8") as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            all_rows.append(row)

    # Add new columns if they are not already in the CSV
    header = reader.fieldnames[:]
    if "Runnable Test Code" not in header:
        header.append("Runnable Test Code")
    if "Final Compile Status" not in header:
        header.append("Final Compile Status")

    # Process each row serially
    for idx, row in enumerate(all_rows, start=1):
        fqn = row.get("FQN", "").strip()
        saved_path_str = row.get("Saved Path", "").strip()
        print(f"\n--- Processing row {idx}: FQN = {fqn} ---")

        if not saved_path_str:
            row["Runnable Test Code"] = ""
            row["Final Compile Status"] = "no_saved_path"
            continue

        # Resolve the exact test file under src/test/java/…
        test_file_path = SRC_TEST_ROOT / saved_path_str
        if not test_file_path.exists():
            row["Runnable Test Code"] = ""
            row["Final Compile Status"] = "file_not_found"
            continue

        # Try to compile it on the first pass
        ok, stderr_text = compile_single_test(test_file_path)
        if ok:
            # “Formatted Code” from Task 4 is already known to be syntactically valid,
            # so if it compiles, we simply record that.
            formatted_code = row.get("Formatted Code", "").strip()
            row["Runnable Test Code"] = formatted_code
            row["Final Compile Status"] = "compiled_first_try"
            continue

        # If we reach here, the first compile failed. Enter the LLM‐driven refinement loop.
        failing_code = read_entire_file(test_file_path)
        fixed_code   = None
        last_errors  = stderr_text

        for iteration in range(1, MAX_ITERATIONS + 1):
            print(f"[INFO] Iteration {iteration}: calling LLM to fix {saved_path_str}")

            # Build the prompt (failing test code + compiler errors)
            prompt_str = build_refinement_prompt(failing_code, last_errors)
            try:
                response = client.responses.create(
                    model="gpt-4o-mini",
                    input=prompt_str
                )
            except Exception as e:
                print(f"[ERROR] LLM call threw exception: {e}")
                row["Runnable Test Code"] = ""
                row["Final Compile Status"] = "llm_error"
                break

            response_code = response.output_text or ""
            cleaned = response_code.strip()

            # Strip markdown fences if the LLM wrapped its answer in ```java … ```
            if cleaned.startswith("```java"):
                lines = cleaned.splitlines()
                if lines[-1].strip() == "```":
                    lines = lines[1:-1]
                else:
                    lines = lines[1:]
                cleaned = "\n".join(lines)

            # Overwrite the .java on disk with the LLM’s suggestion
            write_entire_file(test_file_path, cleaned)
            fixed_code = cleaned

            # Re‐compile that single test
            ok2, stderr2 = compile_single_test(test_file_path)
            if ok2:
                row["Runnable Test Code"] = fixed_code
                row["Final Compile Status"] = f"compiled_after_{iteration}"
                break
            else:
                failing_code = fixed_code
                last_errors  = stderr2
        else:
            # If we exhausted all 3 iterations without success:
            row["Runnable Test Code"] = fixed_code or ""
            row["Final Compile Status"] = "failed_after_all_iterations"

    # Write out the new CSV, quoting all fields
    with CSV_OUTPUT_PATH.open("w", newline="", encoding="utf-8") as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=header, quoting=csv.QUOTE_ALL)
        writer.writeheader()
        for row in all_rows:
            writer.writerow(row)

    print("\n[DONE] Feedback loop finished.\n")


if __name__ == "__main__":
    main()
