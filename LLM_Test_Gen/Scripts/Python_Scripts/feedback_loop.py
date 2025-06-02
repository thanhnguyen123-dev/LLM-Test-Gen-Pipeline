import os
import subprocess
import csv
from pathlib import Path
from openai_service import client

CSV_INPUT_PATH  = Path(__file__).resolve().parent.parent.parent / "Data" / "Formatted_Generated_Test_Data.csv"
CSV_OUTPUT_PATH = Path(__file__).resolve().parent.parent.parent / "Data" / "Runnable_Test_Data.csv"
PROJECT_ROOT    = Path(__file__).resolve().parent.parent.parent
SRC_TEST_ROOT   = PROJECT_ROOT / "LLM_Test_Gen" / "src" / "test" / "java"
MAX_ITERATIONS  = 3
REFINEMENT_PROMPT_TEMPLATE_PATH = Path(__file__).resolve().parent.parent / "Data" / "Prompts" / "refinement_template.txt"

def compile_java(file_path):
    p = subprocess.run(["javac", str(file_path)], stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    stderr_text = p.stderr.decode("utf-8", errors="ignore")
    return (p.returncode == 0, stderr_text)


def load_refinement_template():
    if not REFINEMENT_PROMPT_TEMPLATE_PATH.exists():
        return None
    return REFINEMENT_PROMPT_TEMPLATE_PATH.read_text(encoding="utf-8")


def build_refinement_prompt(failing_test_code, compiler_errors): 
    template = load_refinement_template()
    if template:
        s = template.replace("%%TEST_CODE%%", failing_test_code)
        s = s.replace("%%ERROR_MSGS%%", compiler_errors)
        return s
    return f"@persona {{\nYou are an expert Java developer and test‐generation specialist.\n}}\n\n@instruction {{\nFix the Java test code so that it compiles under JDK 8 and JUnit 4 without removing existing test logic.\n}}\n\n@format {{\n@input (failing_test_code):\n{{\n{failing_test_code}\n}}\n\n@input (compiler_errors):\n{{\n{compiler_errors}\n}}\n\n@output (fixed_test_code):\n{{\nProvide only the corrected Java test code. No additional explanation!\n}}\n}}"

def read_entire_file(path):
    return path.read_text(encoding="utf-8")


def write_entire_file(path, contents):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(contents, encoding="utf-8")


def main():
    all_rows = []
    with CSV_INPUT_PATH.open(newline="", encoding="utf-8") as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            all_rows.append(row)
    header = reader.fieldnames[:]
    if "Runnable Test Code" not in header:
        header.append("Runnable Test Code")
    if "Final Compile Status" not in header:
        header.append("Final Compile Status")
    for row in all_rows:
        saved_path_str = row.get("Saved Path", "").strip()
        if not saved_path_str:
            row["Runnable Test Code"] = ""
            row["Final Compile Status"] = "no_saved_path"
            continue
        test_file_path = SRC_TEST_ROOT / saved_path_str
        if not test_file_path.exists():
            row["Runnable Test Code"] = ""
            row["Final Compile Status"] = "file_not_found"
            continue
        success, errors = compile_java(test_file_path)
        if success:
            row["Runnable Test Code"] = row.get("Code After Formatting", "").strip()
            row["Final Compile Status"] = "compiled_first_try"
            continue
        failing_code = read_entire_file(test_file_path)
        fixed_code = None
        last_errors = errors
        for iteration in range(1, MAX_ITERATIONS + 1):
            prompt_str = build_refinement_prompt(failing_code, last_errors)
            response_code = client.responses.create(
                model="gpt-4o-mini",
                input=prompt_str
            ).output_text
            cleaned = response_code.strip()
            if cleaned.startswith("```java"):
                lines = cleaned.splitlines()
                if lines[-1].strip() == "```":
                    lines = lines[1:-1]
                else:
                    lines = lines[1:]
                cleaned = "\n".join(lines)
            write_entire_file(test_file_path, cleaned)
            fixed_code = cleaned
            success2, errors2 = compile_java(test_file_path)
            if success2:
                row["Runnable Test Code"] = fixed_code
                row["Final Compile Status"] = f"compiled_after_{iteration}"
                break
            else:
                failing_code = fixed_code
                last_errors = errors2
        else:
            row["Runnable Test Code"] = fixed_code or ""
            row["Final Compile Status"] = "failed_after_all_iterations"
    with CSV_OUTPUT_PATH.open("w", newline="", encoding="utf-8") as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=header, quoting=csv.QUOTE_ALL)
        writer.writeheader()
        for row in all_rows:
            writer.writerow(row)

if __name__ == "__main__":
    main()
