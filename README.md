# Microservice User Management

## Introduction

This microservice is designed for managing user-related operations, such as user registration and profile management. It communicates with the Microservice Email Sender for sending emails, particularly welcome emails upon user registration.

## Technologies Used

- Java: Version 17
- Spring Boot:
  - spring-boot-starter-amqp
  - spring-boot-starter-data-jpa
  - spring-boot-starter-validation
  - spring-boot-starter-web
  - spring-boot-devtools
- Database: PostgreSQL
- Other Dependencies:
  - Project Lombok (optional, for simplifying code)
  
## How to Run the Project

Follow these steps to run the project locally:

### Prerequisites
- Java 17 installed
- PostgreSQL database running with appropriate configurations
- RabbitMQ server running for messaging communication

### Steps
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Antonio-Jefferson/ms-user.git
  ``
## Configure Database:

Open `application.yml` in `src/main/resources` and update the database connection properties.

## Configure RabbitMQ:

Ensure that the RabbitMQ server specified in the configuration is running and accessible.

## Build and Run:

```bash
./mvnw clean install
./mvnw spring-boot:run
````
## Access the Application:

The microservice will be accessible at [http://localhost:8081](http://localhost:8081).

## Additional Configuration:

If you are using an IDE, ensure it supports Lombok. If not, you may need to install a Lombok plugin.

## Microservice Email Sender Integration:

This microservice communicates with the Microservice Email Sender for sending welcome emails to users upon registration. Ensure that the Microservice Email Sender is running and configured correctly for seamless communication.

For Microservice Email Sender setup and configuration, refer to its README: [Microservice Email Sender README](https://github.com/Antonio-Jefferson/ms-email)

