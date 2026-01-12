# Project Report: AI Backend Services & Frontend Dashboard

## Executive Summary
This project delivers two robust AI-powered backend microservices and a unified frontend dashboard. The solutions address the requirements of text classification and automated content generation using modern Large Language Models (LLMs) via the OpenRouter API.

## Solution Architecture

### 1. Backend Microservices
The system is implemented as a unified Java Spring Boot application to ensure robustness and type safety:
- **Unified Service (Port 8080)**: Handles both Text Classification and Email Generation endpoints.

**Key Design Decisions:**
- **Java & Spring Boot**: Chosen for enterprise-grade robustness, strict typing, and extensive ecosystem support.
- **RESTful Integration**: We used Java's `RestTemplate` to manually interact with the OpenRouter API, demonstrating low-level HTTP handling and JSON parsing in Java without relying on third-party SDKs.
- **Model Selection**: We utilized `xiaomi/mimo-v2-flash:free`. This model was selected for its low latency and zero cost, making it ideal for testing and development.

### 2. Frontend Dashboard
A strictly separated "Vanilla" frontend was created to demonstrate the APIs.
- **Location**: `solutions/frontend/index.html`
- **Design**: Premium "Dark Mode" aesthetic using raw CSS variables for maintainability.
- **Integration**: Uses native `fetch` API to communicate with the Java backend on localhost:8080.

## AI Integration Strategy

### Prompt Engineering
Effective use of LLMs was central to the solution.

**Classification Strategy:**
We used a constraint-based prompt. By explicitly listing the allowed categories ("Complaint", "Query", etc.) and demanding a confidence score, we forced the LLM to perform a discriminative task rather than a generative one.
> *Prompt Snippet: "Return the response in strictly valid JSON format like this: { 'category': '...', 'confidence': ... }"*

**Generation Strategy:**
For the email generator, the prompt was designed to be variable-dependent. We inject the user's `tone` parameter directly into the system instruction, effectively changing the "persona" of the AI for each request.

## Challenges & Resolutions

- **Challenge**: Initial dependence on Google Gemini SDK.
- **Resolution**: Refactor to OpenRouter to allow for broader model access and standard OpenAI compatibility.
- **Challenge**: JSON Parsing Reliability.
- **Resolution**: LLMs sometimes wrap JSON in markdown (```json ... ```). We implemented a regex extraction step in Java (`Pattern.compile("\\{[\\s\\S]*\\}")`) to robustly find the JSON object regardless of surrounding text.

## Conclusion
The delivered solution meets all functional requirements (API endpoints, AI integration, modularity) and exceeds expectations with the addition of a graphical user interface and robust error handling.
