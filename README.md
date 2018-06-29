# Spring-Boot-Product-System

 Spring Boot Crud Application with Thymeleaf, JPA, Spring Security. 
 
# Product System

This project based on the Spring Boot project and uses these packages:

<ul>
<li>Spring Boot</li>
<li>Spring Data</li>
 <li>Spring Security</li>
<li>Thymleaf</li>
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

![1](https://user-images.githubusercontent.com/17202632/42084113-79fb9f5e-7b95-11e8-962c-c3c65e3889be.png)

<br/>

![2](https://user-images.githubusercontent.com/17202632/42084230-c2318c5c-7b95-11e8-8a74-e56fc85ef527.png)

<br/><br/>

![5](https://user-images.githubusercontent.com/17202632/42084438-502579ec-7b96-11e8-80ae-117366ee570b.png)

<br/>

![4](https://user-images.githubusercontent.com/17202632/42084457-614bbca4-7b96-11e8-9303-f5f12572e68f.png)


