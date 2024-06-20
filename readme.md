Claro! Aqui está o texto em formato Markdown:

```markdown
# Medical Application API

This project is a medical application API developed using Java, Spring Boot, and MySQL. It manages doctors, patients, and appointments within a medical practice.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
  - [Doctors](#doctors)
  - [Patients](#patients)
  - [Appointments](#appointments)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Features

- Manage doctors, patients, and appointments
- RESTful API endpoints for CRUD operations
- Data validation using class-validator
- Integration tests with JUnit
- Flyway for database migrations
- Random data generation for testing using DataFaker

## Architecture

The application follows a modular architecture with the following structure:

- **Controller**: Handles HTTP requests and responses
- **Service**: Contains business logic
- **Repository**: Manages data persistence using Spring Data JPA
- **Model**: Defines the data structure

## Prerequisites

- Java 11 or higher
- Maven
- MySQL

## Installation

1. Clone the repository:
    ```bash
    git clone <repository-url>
    cd medical-application
    ```

2. Configure the MySQL database:
    - Create a database named `medical_db`.
    - Update the `application.properties` file with your database credentials.

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Usage

The application exposes several RESTful API endpoints for managing doctors, patients, and appointments. You can use tools like Postman or curl to interact with the API.

## API Endpoints

### Doctors

- **GET /doctors**: Get all doctors
- **GET /doctors/{id}**: Get a doctor by ID
- **POST /doctors**: Create a new doctor
- **PUT /doctors/{id}**: Update a doctor by ID
- **DELETE /doctors/{id}**: Delete a doctor by ID

### Patients

- **GET /patients**: Get all patients
- **GET /patients/{id}**: Get a patient by ID
- **POST /patients**: Create a new patient
- **PUT /patients/{id}**: Update a patient by ID
- **DELETE /patients/{id}**: Delete a patient by ID

### Appointments

- **GET /appointments**: Get all appointments
- **GET /appointments/{id}**: Get an appointment by ID
- **POST /appointments**: Create a new appointment
- **PUT /appointments/{id}**: Update an appointment by ID
- **DELETE /appointments/{id}**: Delete an appointment by ID

## Testing

The project includes unit and integration tests. You can run the tests using Maven:

```bash
mvn test
```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Create a new Pull Request

## License

This project is licensed under the MIT License.
```
