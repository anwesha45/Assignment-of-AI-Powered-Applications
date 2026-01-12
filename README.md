# Java AI Backend

This is a Spring Boot application that replaces the original Node.js microservices. It powers the AI Services Dashboard by providing two main endpoints:

1.  **Text Classification** (`POST /classify`)
2.  **Email Generation** (`POST /generate-email`)

## Prerequisites

-   Java 17 or higher
-   Maven (optional, wrapper recommended/included if set up, but `mvn` command works if installed)

## Setup

1.  **Configuration**: Ensure the `.env` file in this directory contains your valid `OPENROUTER_API_KEY`.
2.  **Build & Run**:
    ```bash
    mvn spring-boot:run
    ```

## Endpoints

### 1. Classify Text
-   **URL**: `http://localhost:3000/classify`
-   **Method**: `POST`
-   **Body**: `{ "text": "Your text here" }`

### 2. Generate Email
-   **URL**: `http://localhost:3001/generate-email`
-   **Method**: `POST`
-   **Body**: `{ "purpose": "...", "recipient_name": "...", "tone": "..." }`
