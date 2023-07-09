# TietoEvry homework assignment

## Run Spring Boot application
```
mvn spring-boot:run
```

The application will start running on port 8080.

Implemented API Endpoint: _GET /trip/items_.<br>
Endpoint calculates the types and quantities of items required for a trip based on the specified parameters.<br>
The request accepts two required query parameters:
* kilometers: The distance of the trip in kilometers.
* startDate: The start date of the trip in the ISO standard format (YYYY-MM-DD).

Example API call: _localhost:8080/trip/items?kilometers=100&startDate=2023-09-15_