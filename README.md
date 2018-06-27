# Spring-Boot-Product-Automation

 Spring Boot Crud Application with Thymeleaf, JPA, Spring Security. 
 
# Product Automation

This project based on the Spring Boot project and uses these packages:

<ul>
<li>Spring Boot</li>
<li>Spring Data</li>
 <li>Spring Security</li>
<li>Thymleaf</li>
<li>Bootstrap</li>
<li>Maven</li>
</ul>

## Installation

### 1. Clone the application

<pre> $ git clone https://github.com/batuhaniskr/spring-boot-product-automation.git </pre>

### 2. Database Configuration

In this project MySQL database is used. db_product.sql file is imported from MySQL.
<ul>
<li> Open src/main/resources/application.properties </li>
<li> Change spring.datasource.username and spring.datasource.password as per your mysql installation</li>
</ul>
The project is created with Maven.

### 3. Launch

To run the application through the docker:

<pre>mvn clean package -DskipTests</pre>

<pre>docker-compose up</pre>

or to run the application locally:

<pre>mvn clean install -DskipTests</pre>

<pre>mvn spring-boot:run</pre>

commands run the application.

There are 2 types of users. 

<pre>1- username: user pasword:user</pre>
<pre>2- username: admin password:admin</pre>

İf you try as user, you will get access denied on delete product page. You have to try as admin.

The application runs from http://localhost:8080/products

## Screenshots

![screen shot 2018-01-17 at 11 04 26](https://user-images.githubusercontent.com/17202632/35032049-7ca094a2-fb76-11e7-9a70-b8c7f959d07c.png)

<br/>

![screen shot 2018-01-17 at 11 04 49](https://user-images.githubusercontent.com/17202632/35032055-8ad72a2c-fb76-11e7-8efd-52910ddf81b0.png)
<br/><br/>

![screen shot 2018-01-17 at 11 05 26](https://user-images.githubusercontent.com/17202632/35032150-ee90fef8-fb76-11e7-8ab1-7d6fa9974b40.png)

