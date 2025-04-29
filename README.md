# LibraryHub-API

A RESTful Library Management System (LMS) built using Spring Boot and MySQL. This API allows users to manage books, patrons, borrowing, and returning books with persistent data storage.

## Features

- Add books and patrons
- Borrow and return books
- Track book availability 
- Track all borrowed books
- Track all borrowed books by specific patron
- Delete Books and Patrons
- Persistent data storage using MySQL

## Technologies

- Java JDK 17+
- Spring Boot
- MySQL
- Spring Data JPA

## Getting Started

### Prerequisites

- **Java JDK 17** or higher
- **MySQL Server**
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Setup

1. **Create Database**:

    ```sql
    CREATE DATABASE library-management-system;
    ```

2. **Configure application.properties**:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/library-management-system
    spring.datasource.username=root
    spring.datasource.password=root_password
    ```

### Running the Application

1. Clone the repository:

    ```bash
    git clone https://github.com/ch-anuradha578/LibraryHub.git
    cd LibraryHub
    ```

2. Build and run the app:

    ```bash
    ./mvnw spring-boot:run
    ```

### API Endpoints

- **POST /api/books/add**: Add a new book
- **POST /api/patrons/add**: Add a new patron
- **PUT /api/borrowed-books/borrow**: Borrow a book
- **PUT /api/borrowed-books/return**: Return a book
- **GET /api/books**: List all books
- **GET /api/patrons**: List all patrons
- **GET /api/borrowed-books/borrowed**: List all borrowed books
- **GET /api/borrowed-books/borrowed/{patronId}**: List all borrowed books by specific patron
- **DELETE /api/books/delete/{bookId}**: Delete Book
- **DELETE /api/patrons/delete/{patronId}**: Delete Patron

## License

MIT License