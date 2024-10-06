from fastapi import FastAPI
from transformers import LlamaTokenizer, LlamaForCausalLM
import torch

app = FastAPI()

# Load the LLaMA tokenizer and model from Hugging Face
tokenizer = LlamaTokenizer.from_pretrained("decapoda-research/llama-7b-hf")
model = LlamaForCausalLM.from_pretrained("decapoda-research/llama-7b-hf")

# Ensure model is in evaluation mode
model.eval()

# If you are using a GPU, move the model to CUDA
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
model = model.to(device)

def get_embedding(text):
    # Tokenize the input text
    inputs = tokenizer(text, return_tensors="pt").to(device)

    # Generate text using the LLaMA model
    with torch.no_grad():
        outputs = model.generate(inputs['input_ids'], max_length=200)
    
    # Decode the output tokens back into text
    generated_text = tokenizer.decode(outputs[0], skip_special_tokens=True)
    return generated_text

@app.post("/search")
def search(query: str):
    # Get the generated text from the model
    response = get_embedding(query)
    return {"response": response}

# To run the FastAPI app, use the following command:
# uvicorn filename:app --reload