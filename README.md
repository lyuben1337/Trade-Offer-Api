# Trade Offer API

This repository contains the Trade Offer API, which is developed using Java, Maven, and Spring Boot. The API allows users to save Steam apps filters and their parameters and use this data then use .

## Table of Contents
- [Links and Badges](#links-and-badges)
- [Prerequisites](#prerequisites)
- [Installation and Setup](#installation-and-setup)
- [Configuration](#configuration)
- [Using the API](#using-the-api)
- [Contact Information](#contact-information)

## Links and Badges

- Repository: [Trade Offer API](https://github.com/lyuben1337/Trade-Offer-Api/blob/main/src/main)
- API Documentation: [OpenAPI Documentation](https://github.com/lyuben1337/Trade-Offer-Api/blob/main/src/main/resources/openapi.yml)

## Prerequisites:
- Java JDK version 17+ should be installed in the system

Check it here https://www.oracle.com/java/technologies/downloads/#java17
or here https://adoptium.net/temurin/releases/ 

- Gradle should be installed in the system
https://gradle.org/install/

## Installation and Setup

To run this project locally, follow these steps:

1. Clone the repository:
   ```
   git clone https://github.com/lyuben1337/Trade-Offer-Api.git
   ```

2. Navigate to the project directory:
   ```
   cd Trade-Offer-Api
   ```

3. Build the project using Gradle:
   ```
   gradle build
   ```

   After the build is completed, the `/build/libs/` folder will be created with a compiled JAR file ready to be launched.

4. Run the application:
   ```
   java -jar build/libs/<file_name>.jar
   ```

   You can launch the server at your port. If the option `--server.port=<your_port>` is not provided, the default port is 8080.

   For secured endpoints, you can use the admin login and password. If the options `--trade-offer-api.login=<your_login>` and `--trade-offer-api.password=<your_password>` are not provided, the default login is "admin" and the password is "admin".

5. The API will be available at `http://localhost:<your_port>`.

## Configuration

The application requires the following configuration parameters:

- Database connection details (configured in `application.properties` file)
- API authentication settings (configured in `WebSecurityConfig.java`)

Make sure to modify the corresponding configuration files to provide the required parameters.

## Using the API

The API provides the following endpoints and functionalities:

- `GET /api/v1/apps`: Retrieve a list of all available applications.
- `GET /api/v1/apps/{app-id}/filters`: Get the filters for a specific application.
- `POST /api/v1/{app-id}`: Create a new application.
- `POST /api/v1/apps/{app-id}/filters`: Add filters for a specific application.
- `POST /api/v1/apps/{app-id}/filters/{filter-name}`: Add parameters to a specific filter for an application.
- `DELETE /api/v1/apps/{app-id}`: Delete a specific application.
- `DELETE /api/v1/filters/{filter-id}`: Delete a specific filter.
- `DELETE /api/v1/params/{param-id}`: Delete a specific parameter.

For detailed request and response formats, please refer to the [OpenAPI Definition](https://github.com/lyuben1337/Trade-Offer-Api/blob/main/src/main/resources/openapi.yml).

## Contact Information

For any questions or inquiries, please feel free to contact the project maintainer at vladyslav.liubchyk@nure.ua
