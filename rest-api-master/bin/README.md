[![Build Status](https://travis-ci.org/batuhaniskr/spring-boot-product-system.svg?branch=master)](https://travis-ci.org/batuhaniskr/spring-boot-product-system)
# Spring-Boot-Product-System

 Spring Boot Crud Application with Thymeleaf, JPA, Spring Security. 
 
# Product System

This project based on the Spring Boot project and used packages:

<ul>
<li>Spring Boot</li>
<li>Spring Data</li>
 <li>Spring Security</li>
<li>Thymeleaf</li>
<li>JavaScript</li>
<li>Maven</li>
</ul>

## Installation

### 1. Clone the application

<pre> $ git clone https://github.com/batuhaniskr/spring-boot-product-automation.git </pre>

### 2. Database Configuration

MySQL is used as the database.
<ul>
<li> Open src/main/resources/application.properties </li>
<li> If you run the application locally, change spring.datasource.username and spring.datasource.password as per your mysql installation</li>
</ul>
The project is created with Maven.

### 3. Launch

To run the application through the docker:

<pre>mvn clean package</pre>

<pre>docker-compose up</pre>

or to run the application locally:

<pre>mvn clean install</pre>

<pre>mvn spring-boot:run</pre>

commands run the application.

Application runs from localhost:8080/products

There are users of type user and admin.

Admin Credential: 
<pre>email: admin@gmail.com  password: admin</pre>

İf you try as user, you will get access denied on delete product page. You have to try as admin.

The application runs from http://localhost:8080/products

## Screenshots

![screen shot 2018-07-18 at 17 41 58](https://user-images.githubusercontent.com/17202632/42925021-56c95f9e-8b35-11e8-8c83-c15dfeb79e62.png)
<br>

![screen shot 2018-07-11 at 09 19 15](https://user-images.githubusercontent.com/17202632/42553996-cba19d54-84eb-11e8-8538-249de1b23cf0.png)
<br/>

![screen shot 2018-07-11 at 09 18 59](https://user-images.githubusercontent.com/17202632/42553933-9f32c144-84eb-11e8-8cd3-3cec10c94354.png)
<br/><br/>

![4](https://user-images.githubusercontent.com/17202632/42084457-614bbca4-7b96-11e8-9303-f5f12572e68f.png)

![6](https://user-images.githubusercontent.com/17202632/42167116-b127082e-7e15-11e8-8cb2-a8b3ddedde89.png)
