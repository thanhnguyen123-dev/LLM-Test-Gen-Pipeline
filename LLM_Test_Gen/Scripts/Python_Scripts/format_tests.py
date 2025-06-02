#!/usr/bin/env python3
import csv
import os
import re
from pathlib import Path
import subprocess


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

RUN_GOOGLE_JAVA_FORMAT = True
GJF_CMD = "google-java-format" 


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
    Writes the exact java_code to target_folder/<class_name>.java,
    normalizing line endings and optionally running google-java-format.
    """
    file_path = target_folder / f"{class_name}.java"

    # Normalize line endings to Unix style and strip trailing whitespace on each line
    lines = java_code.splitlines()
    cleaned = [line.rstrip() for line in lines]
    normalized = "\n".join(cleaned).strip() + "\n"

    with open(file_path, "w", encoding="utf-8") as f:
        f.write(normalized)

    if RUN_GOOGLE_JAVA_FORMAT:
        try:
            subprocess.run(
                [GJF_CMD, "--replace", str(file_path)],
                check=True,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
            )
        except FileNotFoundError:
            print(
                f"[WARNING] google-java-format not found at '{GJF_CMD}'.\n"
                "Install it or set RUN_GOOGLE_JAVA_FORMAT = False."
            )
        except subprocess.CalledProcessError as e:
            print(
                f"[ERROR] google-java-format failed on {file_path}.\n"
                f"stdout: {e.stdout.decode()}\nstderr: {e.stderr.decode()}"
            )

    print(f"[INFO] Wrote and formatted: {file_path}")


def main():
    if not CSV_PATH.exists():
        raise FileNotFoundError(f"CSV not found at {CSV_PATH}")

    BASE_TEST_DIR.mkdir(parents=True, exist_ok=True)

    with open(CSV_PATH, newline="", encoding="utf-8") as csvfile:
        reader = csv.DictReader(csvfile)

        if "Generated Code" not in reader.fieldnames:
            raise RuntimeError(
                f"Column 'Generated Code' not found. Got: {reader.fieldnames}"
            )

        for idx, row in enumerate(reader, start=1):
            raw_code = row["Generated Code"]
            if not raw_code or raw_code.isspace():
                print(f"[WARNING] Row {idx} has empty 'Generated Code'. Skipping.")
                continue

            java_code = strip_markdown_fences(raw_code)

            try:
                class_name = extract_class_name(java_code)
            except RuntimeError as e:
                print(f"[ERROR] Row {idx}: {e} Skipping.")
                continue

            write_java_file(BASE_TEST_DIR, class_name, java_code)


if __name__ == "__main__":
    main()
