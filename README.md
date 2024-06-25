# graphql_spring_boot
In this tutorial, we’ll demonstrate how to create a GraphQL project in Spring Boot using Java as the main programming language and Gradle as the build tool.

## Introduction

GraphQL is a query language for APIs and a runtime for executing those queries by using a type system you define for
your data. It was developed by Facebook in 2012 and released as an open-source project in 2015. GraphQL provides a
complete and understandable description of the data in your API, gives clients the power to ask for exactly what they
need, and nothing more, makes it easier to evolve APIs over time, and enables powerful developer tools.

## Prerequisites

- JDK 21 or later
- IntelliJ IDEA (optional, but highly recommended)
- Gradle 8.8 or later but it is recommended to use the Gradle wrapper

## Project Structure

The project is a Spring Boot application, and it has the following structure:

```
src/main/java
src/main/resources
src/test/java
src/test/resources
```

## Project Dependencies

See the `build.gradle` file for the project dependencies.

## Running the Project

To run the project, execute the following command:

```shell
./gradlew bootRun
```

## IntelliJ IDEA

To import the project into IntelliJ IDEA, follow these steps:

- Open IntelliJ IDEA
- Click on `Open or Import`
- Select the project directory
- Click on `Open`