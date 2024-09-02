# Java API E2E Test Framework

## Overview

This project is an end-to-end (E2E) test framework for API testing using Java. The framework leverages several powerful libraries and tools to provide comprehensive testing capabilities for RESTful APIs and gRPC services.

## Key Features

- **Spring Boot Integration**: Utilizes Spring Boot for setting up test configurations and running tests.
- **JUnit 5**: Provides a robust testing environment using the JUnit 5 framework.
- **REST Assured**: Facilitates easy and powerful REST API testing.
- **gRPC Support**: Supports gRPC services testing with the `grpc-spring-boot-starter`.
- **JWT Handling**: Enables JWT token generation and validation for secured endpoints.
- **Log4j for Logging**: Provides extensive logging capabilities using Log4j.
- **Lombok**: Simplifies Java code by reducing boilerplate.
- **Protobuf Support**: Enables Protocol Buffers (protobuf) serialization for gRPC testing.
- **Allure Reporting**: Integrates with Allure for generating detailed test reports.

## Getting Started

### Prerequisites

- **Java 11** or higher
- **Maven** (version 3.6.0 or higher)
- **Allure CLI** (for generating and viewing reports)

### Build and Run Tests

To build the project and run all tests, use the following Maven commands:

```bash
mvn clean install
mvn test
```

### Generating and Viewing Allure Reports
To generate and view Allure reports, follow these steps:

1. **Navigate to the `target` directory:** 
   ```bash
   cd target
   ```
2. Serve the Allure report:
   ```bash
   allure serve
   ```
This will start a local server and display the Allure report in your default web browser.

This command will start a local server and open the Allure report in your default web browser, allowing you to visualize the test results with detailed insights.

Ensure that the Allure CLI is installed on your machine. If not, you can install it using the following commands:

```bash
brew install allure    # For macOS users using Homebrew
scoop install allure    # For Windows users using Scoop
```
Or follow the [installation instructions](https://allurereport.org/docs/install/) from the Allure documentation for other operating systems.

### Protobuf and gRPC Plugin
The protobuf-maven-plugin is used for generating Java sources from .proto files. Make sure to configure your .proto files in the src/main/proto directory and follow the plugin's guidelines for compatibility.

### Logging
This application uses Logback, the default logging framework provided by Spring Boot. Logback offers a robust and flexible logging configuration.
You can customize log levels for specific packages or classes by modifying the logback-test.xml file located in the src/test/resources directory.
