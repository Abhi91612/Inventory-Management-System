🏪 Inventory Management System
1. 📘 Project Description

The Inventory Management System is a Spring Boot–based web application designed to manage product stock, sales, and related operations efficiently.
It provides RESTful APIs to perform CRUD (Create, Read, Update, Delete) operations on products and manage inventory in real time.

Key Features:

  Add, update, delete, and view product details
  
  Automatically handle stock quantity changes
  
  Custom exception handling and validation
  
  API endpoints for low-stock alerts and quantity adjustments
  
  Modular, layered architecture (Controller → Service → Repository → Entity)

Tech Stack:

  Backend: Java 17+, Spring Boot 3+, Spring Data JPA
  
  Database: MySQL (or H2 for testing)
  
  Build Tool: Maven
  
  IDE: Spring Tool Suite (STS)

  Testing: JUnit, Mockito


2. ⚙️ Setup & Run Instructions (Local Environment)

    Step 1: Clone the Repository

          git clone https://github.com/Abhi91612/Inventory-Management-System.git

          cd Inventory-Management-System

    Step 2: Open in STS or IntelliJ
    

    Open Spring Tool Suite (STS) or IntelliJ IDEA.

      Click File → Import → Existing Maven Project.

      Select the cloned project directory.

      Wait for Maven dependencies to resolve.

Step 3: Configure Database (MySQL)

 
  Edit the application.properties file located in
  
  src/main/resources/application.properties:
  
  
    spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
    
    spring.datasource.username=root
    
    spring.datasource.password=yourpassword
    
    spring.jpa.hibernate.ddl-auto=update
    
    spring.jpa.show-sql=true
    
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    
    server.port=8080


💡 If you don’t want to set up MySQL, uncomment the H2 configuration in application.properties to use the in-memory database.

Step 4: Run the Application

  You can run the project in one of the following ways:
  
  Method 1: From STS
 
  Right-click on the project → Run As → Spring Boot App
  
  Method 2: From Command Line
  
  mvn spring-boot:run




3. 🧪 API Testing via Swagger UI

After the project runs successfully, open your browser and go to:

👉 http://localhost:8080/swagger-ui/index.html#/

This page provides an interactive Swagger interface where you can test all API endpoints — including adding, updating, deleting, 

and viewing products — directly from the browser.


4. Design Choices:

  Layered Architecture: Divided into Controller, Service, and Repository layers for maintainability and testing.
  
  Exception Handling: Centralized error handling via @ControllerAdvice.
