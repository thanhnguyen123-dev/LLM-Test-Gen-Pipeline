import csv
import sys
import time
import os
from pathlib import Path
from openai_service import generate_test_from_prompt_template

script_dir = Path(__file__).parent.resolve() # Directory containing this script
service_parent_dir = script_dir.parent # Go up to Python_Scripts
sys.path.insert(0, str(service_parent_dir)) # Add Python_Scripts to the path


INPUT_CSV_RELATIVE_PATH = "../../Data/Test_Data.csv"
OUTPUT_CSV_RELATIVE_PATH = "../../Data/Generated_Test_Data.csv"

TARGET_CLASS_PREFIXES = [
    "org.apache.commons.lang3.CharRange.",
    "org.apache.commons.lang3.CharSetUtils.",
    "org.apache.commons.lang3.text.WordUtils.",
    "org.apache.commons.lang3.text.translate.CodePointTranslator.",
    "org.apache.commons.lang3.concurrent.AtomicInitializer"
]

# CSV Column Headers
INPUT_HEADER = ["FQN", "Signature", "Jimple Code Representation", "Is Constructor", "Method Modifiers", "Annotations", "Java Doc", "Class Context", "Class Fields", "Loop Count", "Branch Count", "External Dependencies", "Literal Constants", "Constructor Visibility", "Class Factory Methods"]
OUTPUT_HEADER = ["FQN", "Signature", "Jimple Code Representation", "Is Constructor", "Method Modifiers", "Annotations", "Java Doc", "Class Context", "Class Fields", "Loop Count", "Branch Count", "External Dependencies", "Literal Constants", "Constructor Visibility", "Class Factory Methods", "Generated Code"]

# Delay between API calls (in seconds) to avoid rate limits
API_CALL_DELAY = 1

if __name__ == "__main__":
    print("Start Generating Tests from CSV")

    try:
        input_csv_path = (script_dir / INPUT_CSV_RELATIVE_PATH).resolve()
        output_csv_path = (script_dir / OUTPUT_CSV_RELATIVE_PATH).resolve()
        print(f"Script Directory: {script_dir}")
        print(f"Input CSV (Absolute): {input_csv_path}")
        print(f"Output CSV (Absolute): {output_csv_path}")
    except Exception as e:
        print(f"FATAL ERROR: Could not calculate file paths: {e}")
        print(f"           Script directory might not be determined correctly.")
        exit(1)

    # Verify input CSV exists using the calculated absolute path
    if not input_csv_path.is_file():
        print(f"FATAL ERROR: Input CSV file not found at calculated path: {input_csv_path}")
        print(f"           Current Working Directory: {os.getcwd()}") # Still useful info
        exit(1)

    # Ensure parent directory for output CSV exists
    try:
        output_csv_path.parent.mkdir(parents=True, exist_ok=True)
        print(f"Ensured output directory exists: {output_csv_path.parent}")
    except Exception as e:
        print(f"FATAL ERROR: Could not create parent directory for output CSV: {output_csv_path.parent}")
        print(f"             Error: {e}")
        exit(1)


    methods_to_process = []
    header_indices = {}

    try:
        print("Reading input CSV...")
        with open(input_csv_path, mode='r', newline='', encoding='utf-8') as infile:
            reader = csv.reader(infile)
            header = next(reader)

            try:
                # Get the indices of the expected columns
                header_indices['fqn'] = header.index(INPUT_HEADER[0])
                header_indices['signature'] = header.index(INPUT_HEADER[1])
                header_indices['jimple'] = header.index(INPUT_HEADER[2])
                header_indices['is_constructor'] = header.index(INPUT_HEADER[3])
                header_indices['method_modifiers'] = header.index(INPUT_HEADER[4])
                header_indices['annotations'] = header.index(INPUT_HEADER[5])
                header_indices['java_doc'] = header.index(INPUT_HEADER[6])
                header_indices['class_context'] = header.index(INPUT_HEADER[7])
                header_indices['class_fields'] = header.index(INPUT_HEADER[8])
                header_indices['loop_count'] = header.index(INPUT_HEADER[9])
                header_indices['branch_count'] = header.index(INPUT_HEADER[10])
                header_indices['external_dependencies'] = header.index(INPUT_HEADER[11])
                header_indices['literal_constants'] = header.index(INPUT_HEADER[12])
                header_indices['constructor_visibility'] = header.index(INPUT_HEADER[13])
                header_indices['class_factory_methods'] = header.index(INPUT_HEADER[14])
            except ValueError as e:
                print(f"FATAL ERROR: Missing expected column in input CSV: {e}. Expected: {INPUT_HEADER}")
                exit(1)

            # Check if the header is malformed
            for row in reader:
                if len(row) < max(header_indices.values()) + 1:
                    print(f"WARNING: Skipping malformed row: {row}")    
                    continue

                fqn = row[header_indices['fqn']]
                process_this_row = any(fqn.startswith(prefix) for prefix in TARGET_CLASS_PREFIXES)

                if process_this_row:
                    methods_to_process.append({
                        'fqn': fqn,
                        'signature': row[header_indices['signature']],
                        'jimple': row[header_indices['jimple']],
                        'is_constructor': row[header_indices['is_constructor']],
                        'method_modifiers': row[header_indices['method_modifiers']],
                        'annotations': row[header_indices['annotations']],
                        'java_doc': row[header_indices['java_doc']],
                        'class_context': row[header_indices['class_context']],
                        'class_fields': row[header_indices['class_fields']],
                        'loop_count': row[header_indices['loop_count']],
                        'branch_count': row[header_indices['branch_count']],
                        'external_dependencies': row[header_indices['external_dependencies']],
                        'literal_constants': row[header_indices['literal_constants']],
                        'constructor_visibility': row[header_indices['constructor_visibility']],
                        'class_factory_methods': row[header_indices['class_factory_methods']]
                    })

        print(f"Found {len(methods_to_process)} methods matching target classes.")

    except Exception as e:
        print(f"FATAL ERROR: Failed to read input CSV file '{input_csv_path}': {e}")
        exit(1)

    if not methods_to_process:
        print("No methods found to process. Exiting.")
        exit(0)

    print(f"\nGenerating tests for {len(methods_to_process)} methods...")
    processed_count = 0
    error_count = 0

    try:
        # Open the output CSV file for writing
        with open(output_csv_path, mode='w', newline='', encoding='utf-8') as outfile:
            writer = csv.writer(outfile, quoting=csv.QUOTE_ALL)
            writer.writerow(OUTPUT_HEADER)

            for method_data in methods_to_process:
                # Extract method code knowledge
                fqn = method_data['fqn']
                signature = method_data['signature']
                jimple = method_data['jimple']
                is_constructor = method_data['is_constructor']
                method_modifiers = method_data['method_modifiers']
                annotations = method_data['annotations']
                java_doc = method_data['java_doc']
                class_context = method_data['class_context']
                class_fields = method_data['class_fields']
                loop_count = method_data['loop_count']
                branch_count = method_data['branch_count']
                external_dependencies = method_data['external_dependencies']
                literal_constants = method_data['literal_constants']
                constructor_visibility = method_data['constructor_visibility']
                class_factory_methods = method_data['class_factory_methods']
                print(f"\nProcessing method {processed_count + 1}/{len(methods_to_process)}: {fqn}")

                # call OpenAI service to generate test code
                generated_code = generate_test_from_prompt_template(
                    fqn,
                    signature,
                    jimple,
                    is_constructor,
                    method_modifiers,
                    annotations,
                    java_doc,
                    class_context,
                    class_fields,
                    loop_count,
                    branch_count,
                    external_dependencies,
                    literal_constants,
                    constructor_visibility,
                    class_factory_methods
                )

                # Check for errors in the generated code
                if generated_code.strip().startswith("// ERROR:"):
                    error_count += 1
                    print(f"  -> Service returned an error.")
                else:
                     print(f"  -> Test code generated successfully.")

                # Write the results to the output CSV
                writer.writerow([fqn, signature, jimple, is_constructor, method_modifiers, annotations, java_doc, class_context, class_fields, loop_count, branch_count, external_dependencies, literal_constants, constructor_visibility, class_factory_methods, generated_code])
                processed_count += 1

                # Add delay between API calls to avoid rate limits
                if processed_count < len(methods_to_process):
                    print(f"  -> Waiting for {API_CALL_DELAY} second(s)...")
                    time.sleep(API_CALL_DELAY)

    except Exception as e:
        print(f"\nFATAL ERROR: An error occurred during test generation or writing to output CSV '{output_csv_path}': {e}")
        import traceback
        traceback.print_exc()
        exit(1)

    # Summary
    print("\n--- Processing Complete ---")
    print(f"Total methods processed: {processed_count}")
    print(f"Methods resulting in errors from OpenAI service: {error_count}")
    print(f"Output written to: {output_csv_path}")
    print("----------------------------")