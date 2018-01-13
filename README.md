# Spring-Boot-Product-Automation

 Spring Boot Crud Application with Thymeleaf, JPA. 
 
# Product Automation

This project based on the Spring Boot project and uses these packages:

<ul>
<li>Spring Boot</li>
<li>Spring Data</li>
<li>Thymleaf</li>
<li>Bootstrap</li>
<li>Maven</li>
</ul>

## Installation

### 1. Clone the application

### 2. Database Configuration

In this project MySQL database is used. db_product.sql file is imported from MySQL.

<li> Open src/main/resources/application.properties <li>
<li> Change spring.datasource.username and spring.datasource.password as per your mysql installation
</li>

The project is created with Maven.

### 3. Build and run the app using maven

<pre>mvn clean install</pre>

<pre>mvn spring-boot:run</pre>

commands run the application.

The application will be start running at http://localhost:8080/products
