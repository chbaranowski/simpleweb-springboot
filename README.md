simple web with spring boot
===========================

This project contains a simple web application, which is build with spring boot and some common spring components.
The project is based on:
 * spring-boot
 * spring-webmvc
 * spring-security
 * spring-data-jpa
 * thymeleaf
 * hibernate-validator

## Run Spring Boot Sample

To start the web application invoke:

	./gradlew bootRun

Open the URL http://localhost:8080 in a web browser. Login with the username "spring" and password "boot".

## Import into Eclipse 

The eclipse project configurations files can be build with gradle, invoke gradle:

	./gradlew eclipse