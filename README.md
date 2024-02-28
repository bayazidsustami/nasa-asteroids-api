# NASA API 

This Spring Boot application, is a RESTful service designed to utilize NASA's API (https://api.nasa.gov) to fetch astronomical data.

## Requirements

- JDK 17 or higher
- Maven 3.9.5 or higher
- Lombok
- Spring-Boot 3.2.3

## Setup

1. **Clone the repository:**

    ```bash
    git https://github.com/bayazidsustami/nasa-asteroids-api.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd nasa-asteroids-api
    ```

3. **Build the project:**

    ```bash
    ./mvnw clean package
    ```

4. **Run the application:**

    ```bash
    java -jar target/nasa-asteroids-0.0.1-SNAPSHOT.jar
    ```

   Alternatively, you can run it directly from Maven:

    ```bash
    ./mvnw spring-boot:run
    ```

## Configuration

### Api Key
Ensure you have obtained an API key from NASA (https://api.nasa.gov) and configure it in the `application.properties` file:

```properties
nasa.api-key={YOUR-API-KEY}
```

**or** 

set the api key on your environment variable :

```bash
export NASA_API_KEY=your-api-key
```

### Server Host Port
You can also can change server port(default : 8001) by configure it in `application.properties` file :

```properties
server.port=8000
```

## Usage
Once the application is running, you can access the following endpoints:

- `GET /api/asteroids`: Retrieves all near-Earth objects within a specified date range.
   
   **Query Param**

   - `start_date` : The start date of the date range (format: YYYY-MM-DD).
   - `end_date` : The end date of the date range (format: YYYY-MM-DD).

- `GET /api/asteroids/{asteroidId}`: Retrieves data about specified asteroid by id.
  
   **Query Param**

   You can also filter the near-earth object by range date. this query param is optional
   
   - `start_date` : The start date of the date range (format: YYYY-MM-DD)-**Required**.
   - `end_date` : The end date of the date range (format: YYYY-MM-DD)-**Required**.

Example usage:
```bash
curl http://localhost:8080/api/asteroids?start_date=2024-02-28&end_date=2024-03-05
```
## Contributing
Contributions are welcome! If you have any suggestions, improvements, or feature requests, please open an issue or create a pull request.


