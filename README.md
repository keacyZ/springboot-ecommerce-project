# Spring Boot E-Commerce / Ordering System

A full-stack e-commerce web application built with Spring Boot.

## Features
- User registration & login (Spring Security)
- Role-based authorization (Admin / User)
- Product management (CRUD)
- Shopping cart functionality
- Order placement & order history
- Admin dashboard for managing products & orders
- Thymeleaf-based UI

## Roles
Admin: Manage products and orders
User: Browse products, manage cart, place orders

## Notes
Uses in-memory H2 database for development
Sample data can be initialized on application startup

## Tech Stack
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- Thymeleaf
- H2 Database
- Maven

## Project Structure
- MVC architecture
- Controller / Service / Repository layers
- Secure authentication & authorization
- Clean code & separation of concerns

## How to Run

```bash
mvn clean install
mvn spring-boot:run
```
Then Open:
http://localhost:8080



