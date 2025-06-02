from openai import OpenAI
from pathlib import Path
import os
from dotenv import load_dotenv

load_dotenv()


RELATIVE_PATH_TO_PROMPT_TEMPLATE = "../../Data/Prompts/prompt_template.txt"
client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

def generate_test_from_prompt_template(
    fqn: str,
    signature: str,
    jimple_code_representation: str,
    is_constructor: bool,
    method_modifiers: str,
    annotations: str,
    java_doc: str,
    class_context: str,
    class_fields: str,
    loop_count: str,
    branch_count: str,
    external_dependencies: str,
    literal_constants: str,
    constructor_visibility: str
) -> str:
    template = None
    template_file_path = None

    try:
        script_dir = Path(__file__).parent.resolve()
        template_file_path = (script_dir / RELATIVE_PATH_TO_PROMPT_TEMPLATE).resolve()

        with open(template_file_path, "r") as file:
            template = file.read()
    except FileNotFoundError as e:
        raise FileNotFoundError(f"Prompt template file not found at {template_file_path}")
    
    template = template.replace("%%fqn%%", fqn)
    template = template.replace("%%signature%%", signature)
    template = template.replace("%%jimple_code_representation%%", jimple_code_representation)
    template = template.replace("%%is_constructor%%", is_constructor)
    template = template.replace("%%method_modifiers%%", method_modifiers)
    template = template.replace("%%annotations%%", annotations)
    template = template.replace("%%java_doc%%", java_doc)
    template = template.replace("%%class_context%%", class_context)
    template = template.replace("%%class_fields%%", class_fields)
    template = template.replace("%%loop_count%%", loop_count)
    template = template.replace("%%branch_count%%", branch_count)
    template = template.replace("%%external_dependencies%%", external_dependencies)
    template = template.replace("%%literal_constants%%", literal_constants)
    template = template.replace("%%constructor_visibility%%", constructor_visibility)
    response = client.responses.create(
        model="gpt-4o",
        input=template
    )

    return response.output_text
    
    
    
    
    
