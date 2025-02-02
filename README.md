# Clean Architecture Project

This project follows the principles of **Clean Architecture**, ensuring high modularity, separation of responsibilities, and ease of maintenance.

## Domains

The project covers the following main domains:

1. **Customer**: Management of customers, including registration, updates, and queries.
2. **Card**: Management of cards, such as registration, validation, and association with customers.
3. **Delivery**: Management of cards, such as registration, validation, and association with customers.
4. **Charge**: Management of cards, such as registration, validation, and association with customers.

## Project Structure

The project is organized into layers, following Clean Architecture principles:

- **Domain**: Contains the core entities, repository interfaces, and use cases. This layer is independent of frameworks and libraries.
- **Application**: Implements use cases and business rules, coordinating interactions between the domain layer and external layers.
- **Infrastructure**: Implements repositories, external services, and integrations with APIs or databases.
- **Presentation**: REST APIs.

## Requirements

- **Language**: Java 17
- **Framework**: Spring Boot
- **Docker**
- **Other Dependencies**:
  - Spring Data JPA
  - Lombok
  - Migration
  - Spring Security
  - Junit

## Setup

1. Compile and run the project:
   ```bash
   ./mvnw spring-boot:run
