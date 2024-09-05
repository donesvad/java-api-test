# Java API E2E Test Framework

## Overview

This project is an end-to-end (E2E) test framework for API testing using Java. The framework leverages several powerful libraries and tools to provide
comprehensive testing capabilities for RESTful APIs and gRPC services.

## Key Features

- **Spring Boot Integration**: Utilizes Spring Boot for setting up test configurations and running tests.
- **JUnit 5**: Provides a robust testing environment using the JUnit 5 framework.
- **REST Assured**: Facilitates easy and powerful REST API testing.
- **gRPC Support**: Supports gRPC services testing with the `grpc-spring-boot-starter`.
- **JWT Handling**: Enables JWT token generation and validation for secured endpoints.
- **Logback for Logging**: Leverages Spring's default Logback for flexible and performant logging capabilities.
- **Lombok**: Simplifies Java code by reducing boilerplate.
- **Protobuf Support**: Enables Protocol Buffers (protobuf) serialization for gRPC testing.
- **Allure Reporting**: Integrates with Allure for generating detailed test reports.
- **GitHub Actions for CI**: Implements Continuous Integration (CI) using GitHub Actions to automatically build, test, and publish test reports. The CI workflow
  triggers on push, pull request, or manually, ensuring that the code is always in a deployable state.
- **WireMock for Mocking**: Utilizes WireMock for mocking external HTTP dependencies, allowing for isolated and repeatable tests by simulating the behavior of
  an external API without relying on its availability.

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven** (version 3.6.0 or higher)
- **Allure CLI** (for generating and viewing reports)

### Build and Run Tests

To build the project and run all tests, use the following Maven commands:

```bash
mvn clean install -DskipTests
mvn test
```

### Generating and Viewing Allure Reports

This project uses **Allure** to generate comprehensive and user-friendly test reports. Allure reports provide detailed insights into test execution, including
test results, logs, and visualizations.

#### Viewing the Allure Report

The latest Allure report for this project can be viewed online at the following link:

[View Allure Report Results](https://donesvad.github.io/java-api-test/)

This report includes:

- Test Results: Summary and details of passed, failed, and skipped tests.
- Test Suites: Breakdown of test suites and their execution results.
- Test History: Insights into test execution history and trends over time.
- Logs and Attachments: Detailed logs and any additional attachments captured during test execution.

#### How to Generate Allure Report Locally

To generate and view Allure reports locally, follow these steps:

1. **Navigate to the `target` directory:**
   ```bash
   cd target
   ```
2. Serve the Allure report:
   ```bash
   allure serve
   ```

This will start a local server and display the Allure report in your default web browser.

This command will start a local server and open the Allure report in your default web browser, allowing you to visualize the test results with detailed
insights.

Ensure that the Allure CLI is installed on your machine. If not, you can install it using the following commands:

```bash
brew install allure    # For macOS users using Homebrew
scoop install allure    # For Windows users using Scoop
```

Or follow the [installation instructions](https://allurereport.org/docs/install/) from the Allure documentation for other operating systems.

### Protobuf and gRPC Plugin

The protobuf-maven-plugin is used for generating Java sources from `.proto` files. Make sure to configure your `.proto` files in the `src/main/proto` directory
and follow the plugin's guidelines for compatibility.

### Logging

This application uses Logback, the default logging framework provided by Spring Boot. Logback offers a robust and flexible logging configuration.
You can customize log levels for specific packages or classes by modifying the `logback-test.xml` file located in the `src/test/resources` directory.

### Continuous Integration (CI) Pipeline

This project uses GitHub Actions for Continuous Integration (CI) to automatically run end-to-end (E2E) API tests on each push or pull request to the `main`
branch. The CI pipeline is defined in the `.github/workflows/maven-test.yml` file and consists of the following steps:

1. Triggering the Workflow:
    - The workflow is triggered on any push or pull request to the `main` branch or manually via the "workflow_dispatch" event.
2. Setting Up the Environment:
    - The CI pipeline runs on the ubuntu-latest virtual machine.
    - It checks out the code from the repository and sets up JDK 17 using the `actions/setup-java@v4` action.
3. Building the Project:
    - The project is first built using Maven with the command mvn clean install -DskipTests. This command compiles the project and skips the tests to quickly
      validate the build.
4. Running the Tests:
    - After the build, the E2E API tests are executed using the mvn test command. This step ensures that all tests are run and their results are generated.
5. Generating and Publishing the Allure Test Report:
    - The CI pipeline loads the test report history from the gh-pages branch.
    - The Allure report is generated using the `simple-elf/allure-report-action@v1.9` action.
    - The generated report is then published to the gh-pages branch using the `peaceiris/actions-gh-pages@v3` action.
6. Accessing the Allure Report:
    - After the workflow runs, you can view the Allure report. The Allure report is hosted on GitHub Pages and can be accessed using the following
      link: [View Allure Report Results](https://donesvad.github.io/java-api-test/)

This CI pipeline ensures that all changes to the main branch are thoroughly tested and that test results are easily accessible through the Allure report.

### WireMock

WireMock is integrated into the project to mock external API dependencies, enabling more controlled and predictable testing environments. This is particularly
useful for scenarios where the external service is unreliable, expensive to use, or simply unavailable during testing.

WireMock allows you to:

- Define stubs for external service endpoints, returning pre-defined responses for various HTTP methods.
- Simulate different response statuses and delays to test how your application handles various scenarios.
- Ensure that your tests are fast, reliable, and do not depend on external network conditions.

### Test Parallelization

To improve the efficiency and speed of the test execution, especially when dealing with a large number of test scenarios, this framework supports parallel
execution using both **thread-based** (JUnit 5) and **fork-based** (Maven Surefire plugin) parallelization strategies.

#### 1. Thread-Based Parallelization with JUnit 5

JUnit 5 natively supports parallel execution of tests using its configuration settings. You can run test methods or test classes concurrently, which helps speed
up the test suite execution.

**Configuration**
To enable thread-based parallelization with JUnit 5, you need to add the following configuration to your junit-platform.properties file, located in the
`src/test/resources` directory:

```properties
# junit-platform.properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.config.strategy=dynamic
junit.jupiter.execution.parallel.config.dynamic.factor=2
```

In this configuration:

- **junit.jupiter.execution.parallel.enabled**: Enables parallel execution.
- **junit.jupiter.execution.parallel.mode.default**: Sets the default parallel execution mode. Use concurrent to run test classes and methods in parallel.
- **junit.jupiter.execution.parallel.config.strategy**: Defines the parallel execution strategy. Options include fixed or dynamic.
- **junit.jupiter.execution.parallel.config.dynamic.factor**: For dynamic strategy, this factor is multiplied by the number of available processors to determine
  the maximum number of threads to use. For example, if you have 4 CPUs and a factor of 2, JUnit will use up to 8 threads.

#### 2. Fork-Based Parallelization with Maven Surefire Plugin

Fork-based parallelization runs multiple instances of the JVM, each executing a portion of the test suite. This method is more resource-intensive but provides a
higher degree of isolation between test cases, making it suitable for tests that have significant memory or CPU demands.

**Configuration**
To enable fork-based parallelization in Maven, update the surefire plugin configuration in your `pom.xml` file:

```xml
<configuration>
  <forkCount>2</forkCount>
  <reuseForks>true</reuseForks>
</configuration>
```

In this configuration:

- **forkCount**: Specifies the number of JVM instances to run in parallel. You can use a fixed number (e.g., 2) or a dynamic value based on available CPUs.
- **reuseForks**: When set to true, Maven reuses the JVM instances for subsequent tests, reducing the overhead of JVM startup time.

By combining both thread-based and fork-based parallelization strategies, you can optimize test execution time and resource utilization for your test suite.

### Automatic Retry of Failing Tests

To handle flaky tests or tests that intermittently fail due to non-deterministic issues (such as network timeouts or temporary service unavailability), this
framework supports automatic retries of failing tests using the `rerunFailingTestsCount` feature of the Maven Surefire and Failsafe plugins.
