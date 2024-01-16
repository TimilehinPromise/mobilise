# Book Management System

# Overview

This application is a simple RESTful web service developed using Java and Spring Boot. It provides a book management system with functionalities for creating, reading, updating, and deleting books. It also includes a feature to search for books by title or author.

### Key Features

#### CRUD Operations: Manage books in the system with the following operations:

* Create: Add a new book.
* Read: Retrieve details of all books or a specific book by its ID.
* Update: Modify details of an existing book.
* Delete: Remove a book from the system.
* Search Feature: Search for books by title or author.
* Pagination: Retrieve books with pagination support (optional).

### Technologies

* Java 11 or above: For backend development.
* Spring Boot: For creating the web application.
* Spring Data JPA: For database access.
* H2 Database: In-memory database for data storage.
* Docker: For containerizing and managing the application environment.
* JUnit: For unit testing.
* Mockito: For mocking objects in tests.


### Running the Application

#### Prerequisites


### Option 1: Using Maven

#### Prerequisites

* Java 11 or above
* Maven


#### Steps to Run

##### Clone the Repository:
git clone TimilehinPromise/mobilise.git

##### Navigate to Project Directory:

cd mobilise

##### Build the Project:

mvn clean install

##### Run the Application:

java -jar target/Mobilise-0.0.1-SNAPSHOT.jar


Alternatively, you can use Maven to run the application:

##### mvn spring-boot:run


### Option 2: Using Docker

#### Prerequisites

* Docker 
* Docker Compose


#### Start the Application Using Docker Compose File in The Application:

docker-compose up


This command will pull the necessary Docker images, build the application, and start it along with any required services.

The service will be available on http://localhost:8060.

##### Accessing the API

Refer to the provided Postman collection for API endpoints and usage:

https://documenter.getpostman.com/view/15243512/2s9YsQ9A6a

### Stopping the Application

* For Maven: Press Ctrl+C in the terminal.
* For Docker: Run docker-compose down.

#### Technical Specifications

* Ensure the application follows good design principles and clean code practices.

* Include validation checks and proper exception handling.

* Structure the application with separate concerns (controllers, services, repositories, etc.).

* Provide clear and concise comments in the code.

##### Additional Information

* Pagination: To use pagination in the "Retrieve all books" feature, pass page and size parameters in the request.
* Unit Tests: Unit tests are written using JUnit and Mockito. They can be run with the application outside the Docker container.
* Contact
