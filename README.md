# App Extractor Service

A Spring Boot REST service that extracts environment and version information from a list of application names.

## Features

- Extracts environment (dev, uat, prod) and version information from app names
- Returns JSON response with grouped environment and version data
- Handles single and multiple versions per environment
- RESTful API with proper error handling

## Project Structure

```
src/
├── main/
│   ├── java/com/example/
│   │   ├── AppExtractorApplication.java
│   │   ├── controller/
│   │   │   └── AppExtractorController.java
│   │   └── service/
│   │       └── AppExtractorService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/
        └── AppExtractorApplicationTests.java
```

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### 1. Extract Environment Versions
**POST** `/api/extract`

**Request Body:**
```json
[
    "app_aim_ecs_gcg_dev_158298",
    "app_aim_ecs_gcg_prod_158298",
    "app_aim_ecs_gcg_uat_169284",
    "app_aim_ecs_gcg_uat_168988",
    "app_aim_ecs_gcg_dev_169284",
    "app_aim_ecs_gcg_dev_168988"
]
```

**Response:**
```json
{
    "dev": ["158298", "169284", "168988"],
    "uat": ["169284", "168988"],
    "prod": "158298"
}
```

### 2. Health Check
**GET** `/api/health`

Returns service status.

## Example Usage

### Using curl
```bash
curl -X POST http://localhost:8080/api/extract \
  -H "Content-Type: application/json" \
  -d '[
    "app_aim_ecs_gcg_dev_158298",
    "app_aim_ecs_gcg_prod_158298",
    "app_aim_ecs_gcg_uat_169284",
    "app_aim_ecs_gcg_uat_168988",
    "app_aim_ecs_gcg_dev_169284",
    "app_aim_ecs_gcg_dev_168988"
  ]'
```

### Using Postman
1. Create a new POST request
2. Set URL to `http://localhost:8080/api/extract`
3. Set Content-Type header to `application/json`
4. Add the request body as shown above

## Logic Explanation

The service uses a regex pattern `app_aim_ecs_gcg_(\\w+)_(\\d+)` to extract:
- Environment name (dev, uat, prod)
- Version number

For each environment:
- If there's only one version, it returns the version as a string
- If there are multiple versions, it returns them as a sorted array

## Testing

Run the tests using:
```bash
mvn test
```

The test suite includes:
- Context loading test
- Environment version extraction test with sample data 