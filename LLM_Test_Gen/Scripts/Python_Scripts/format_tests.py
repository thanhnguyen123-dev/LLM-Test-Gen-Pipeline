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
    Remove the markdown fences from the generated code if present.
    """
    lines = java_code.splitlines()
    if not lines:
        return java_code

    first = lines[0].strip()
    last = lines[-1].strip()

    # Check if the first and last lines are markdown fences
    if first.lower() == "```java" and last == "```":
        inner = lines[1:-1]
        cleaned_inner = "\n".join(line.rstrip() for line in inner).rstrip() + "\n"
        return cleaned_inner
    else:
        return java_code

def extract_class_name(java_code: str) -> str:
    """
    Extract the class name from the generated code.
    """
    # Find the first public class declaration to extract the class name
    pattern = re.compile(r"^\s*public\s+class\s+([A-Za-z_]\w*)\b", re.MULTILINE)
    match = pattern.search(java_code)
    if not match:
        raise RuntimeError(
            "Could not find a 'public class <Name>' declaration in the Generated Code."
        )
    return match.group(1)


def write_java_file(target_folder: Path, class_name: str, java_code: str):
    """
    Write the generated code to a new file in the target folder.
    """
    # Compute the file path
    file_path = target_folder / f"{class_name}.java"

    # Clean the code
    lines = java_code.splitlines()
    cleaned = [line.rstrip() for line in lines]
    normalized = "\n".join(cleaned).strip() + "\n"

    # Write the code to the file
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(normalized)
    print(f"[INFO] Wrote: {file_path.name}")


def main():
    """
    Main function to format the generated test data.
    """
    if not CSV_PATH.exists():
        raise FileNotFoundError(f"CSV not found at {CSV_PATH}")

    BASE_TEST_DIR.mkdir(parents=True, exist_ok=True)

    output_csv = CSV_PATH.with_name("Formatted_Generated_Test_Data.csv")

    # Open the input and output CSV files
    with open(CSV_PATH, newline="", encoding="utf-8") as csvfile, \
         open(output_csv, "w", newline="", encoding="utf-8") as outfile:

        # Create a CSV reader
        reader = csv.DictReader(csvfile)
        fieldnames = reader.fieldnames.copy()
        if "Generated Code" not in fieldnames:
            raise RuntimeError(
                f"Column 'Generated Code' not found. Got: {fieldnames}"
            )

        # Add the formatted code column
        formatted_column = "Formatted Code"
        if formatted_column in fieldnames:
            raise RuntimeError(f"Column '{formatted_column}' already exists!")
        fieldnames.append(formatted_column)

        # Add the saved path column
        saved_path_column = "Saved Path"
        if saved_path_column in fieldnames:
            raise RuntimeError(f"Column '{saved_path_column}' already exists!")
        fieldnames.append(saved_path_column)

        # Create a CSV writer
        writer = csv.DictWriter(outfile, fieldnames=fieldnames, quoting=csv.QUOTE_ALL)
        writer.writeheader()

        # Process each row in the input CSV
        for idx, row in enumerate(reader, start=1):
            raw_code = row["Generated Code"]
            if not raw_code or raw_code.isspace():
                print(f"[WARNING] Row {idx} has empty 'Generated Code'. Skipping.")
                row[formatted_column] = ""
                row[saved_path_column] = ""
                writer.writerow(row)
                continue

            java_code = strip_markdown_fences(raw_code)

            try:
                class_name = extract_class_name(java_code)
            except RuntimeError as e:
                print(f"[ERROR] Row {idx}: {e} Skipping.")
                row[formatted_column] = ""
                row[saved_path_column] = ""
                writer.writerow(row)
                continue

            write_java_file(BASE_TEST_DIR, class_name, java_code)

            normalized_lines = java_code.splitlines()
            cleaned = [line.rstrip() for line in normalized_lines]
            normalized = "\n".join(cleaned).strip() + "\n"
            row[formatted_column] = normalized

            relative_path = f"org/apache/commons/lang3/{class_name}.java"
            row[saved_path_column] = relative_path

            writer.writerow(row)

    print(f"[INFO] Wrote updated CSV with formatted code and Saved Path: {output_csv}")

if __name__ == "__main__":
    main()
