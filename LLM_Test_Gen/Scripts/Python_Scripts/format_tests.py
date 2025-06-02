import csv
import re
from pathlib import Path

CSV_PATH = (
    Path(__file__).resolve().parent.parent.parent
    / "Data"
    / "Generated_Test_Data.csv"
)

PROJECT_ROOT = Path(__file__).resolve().parent.parent.parent.parent
BASE_TEST_DIR = (
    PROJECT_ROOT
    / "lang_1_buggy"
    / "src"
    / "test"
    / "java"
    / "org"
    / "apache"
    / "commons"
    / "lang3"
)

def strip_markdown_fences(java_code: str) -> str:
    """
    If the code is wrapped like:
      ```java
      <actual Java source>
      ```
    then remove the first line (```java) and the last line (```).
    Otherwise, return java_code unchanged.
    """
    lines = java_code.splitlines()
    if not lines:
        return java_code

    first = lines[0].strip()
    last = lines[-1].strip()

    if first.lower() == "```java" and last == "```":
        inner = lines[1:-1]
        cleaned_inner = "\n".join(line.rstrip() for line in inner).rstrip() + "\n"
        return cleaned_inner
    else:
        return java_code

def extract_class_name(java_code: str) -> str:
    """
    Look for a declaration like:
      public class SomeTestClassName {
    and return 'SomeTestClassName'. Raise if not found.
    """
    pattern = re.compile(r"^\s*public\s+class\s+([A-Za-z_]\w*)\b", re.MULTILINE)
    match = pattern.search(java_code)
    if not match:
        raise RuntimeError(
            "Could not find a 'public class <Name>' declaration in the Generated Code."
        )
    return match.group(1)

def write_java_file(target_folder: Path, class_name: str, java_code: str):
    """
    Writes the normalized java_code to target_folder/<class_name>.java,
    normalizing line endings and trailing whitespace.
    """
    file_path = target_folder / f"{class_name}.java"

    # Normalize line endings to Unix style and strip trailing whitespace on each line
    lines = java_code.splitlines()
    cleaned = [line.rstrip() for line in lines]
    normalized = "\n".join(cleaned).strip() + "\n"

    with open(file_path, "w", encoding="utf-8") as f:
        f.write(normalized)

    print(f"[INFO] Wrote: {str(file_path).split('/')[-1]}")

def main():
    if not CSV_PATH.exists():
        raise FileNotFoundError(f"CSV not found at {CSV_PATH}")

    BASE_TEST_DIR.mkdir(parents=True, exist_ok=True)

    output_csv = CSV_PATH.with_name("Generated_Test_Data_WithFormatted.csv")

    with open(CSV_PATH, newline="", encoding="utf-8") as csvfile, \
         open(output_csv, "w", newline="", encoding="utf-8") as outfile:

        reader = csv.DictReader(csvfile)
        fieldnames = reader.fieldnames.copy()
        if "Generated Code" not in fieldnames:
            raise RuntimeError(
                f"Column 'Generated Code' not found. Got: {fieldnames}"
            )

        # Add new column for the (normalized) formatted code
        formatted_column = "Formatted Code"
        if formatted_column in fieldnames:
            raise RuntimeError(f"Column '{formatted_column}' already exists!")
        fieldnames.append(formatted_column)

        writer = csv.DictWriter(outfile, fieldnames=fieldnames)
        writer.writeheader()

        for idx, row in enumerate(reader, start=1):
            raw_code = row["Generated Code"]
            if not raw_code or raw_code.isspace():
                print(f"[WARNING] Row {idx} has empty 'Generated Code'. Skipping.")
                row[formatted_column] = ""
                writer.writerow(row)
                continue

            java_code = strip_markdown_fences(raw_code)

            try:
                class_name = extract_class_name(java_code)
            except RuntimeError as e:
                print(f"[ERROR] Row {idx}: {e} Skipping.")
                row[formatted_column] = ""
                writer.writerow(row)
                continue

            write_java_file(BASE_TEST_DIR, class_name, java_code)

            normalized_lines = java_code.splitlines()
            cleaned = [line.rstrip() for line in normalized_lines]
            normalized = "\n".join(cleaned).strip() + "\n"
            row[formatted_column] = normalized

            writer.writerow(row)

    print(f"[INFO] Wrote updated CSV with formatted code: {output_csv}")

if __name__ == "__main__":
    main()
