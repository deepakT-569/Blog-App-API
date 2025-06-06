# Blog-App-API
This project is a simple backend web application built using Spring Boot, demonstrating core backend development practices. It exposes RESTful APIs for basic CRUD operations and incorporates essential enterprise-level features such as input validation, centralized exception handling, and authentication & authorization using Spring Security.

# Key Features:
Spring Boot & REST APIs:
Efficient and scalable endpoints for performing CRUD operations following REST principles.

# Validation:
Uses javax.validation (like @NotNull, @Size, etc.) with meaningful error responses to ensure only valid data is processed.

# Global Exception Handling:
Custom exception classes and a global exception handler (@ControllerAdvice) to handle validation, runtime, and custom exceptions gracefully.

# Spring Security:
Basic authentication and authorization with role-based access control to secure endpoints.

# Layered Architecture:
Clean separation of concerns using Controller-Service-Repository layers.

# In-Memory Database (H2/MySQL):
Easy-to-configure database for testing and development.

# Tech Stack: 
Java 17, Spring Boot, Spring Web (RESTful APIs), Spring Security, Spring Validation, Spring Data JPA, MySQL, Maven
