
# LEI Validator - Restful Service

## Overview
The LEI Validator Restful Service is a component of the LEI Validator. It provides a RESTful API for validating JSON data against the Livestock Event Information schema (LEI), ensuring their accuracy and compliance with relevant standards.

## Features
- **LEI Validation**: Robust validation of LEI JSON data.
- **Spring Boot Framework**: Built using the Spring Boot framework for efficient and scalable development.
- **JSON Schema Validation**: Incorporates JSON Schema Validator for enhanced validation processes.

## Getting Started

### Prerequisites
- JDK 17
- Maven

### Installation and Setup
1. Clone the LEI Validator repository:
```bash
   git clone https://github.com/mahirgamal/LEI-Validator.git
2. Navigate to the restful-service directory:
 ```bash
     cd LEI-Validator/restful-service

3. Build the project using Maven:
```bash
      mvn clean install

### Running the Service

Execute the following command to run the service:
```bash
java -jar target/restful-service-0.0.1-SNAPSHOT.war

The service will start and listen for requests.

### API Usage
Once the service is running, it will be accessible at http://localhost:8080. Refer to the API documentation for details on available endpoints and their usage.

### Contributing
Contributions to the LEI Validator Restful Service are welcome. Please refer to the project's contribution guidelines for more information.

### License
This project is licensed under the MIT License - see the LICENSE file for details.
