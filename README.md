#simple web app with spring boot

This project contains a simple web application demo, which is build with spring boot and some common spring components.

The project is based on:
 * spring-boot
 * spring-webmvc
 * spring-security
 * spring-data-jpa
 * tomcat
 * thymeleaf
 * hibernate-validator

## Building Spring Boot Application

The spring demo application can be build with maven or gradle. To build the application as executable JAR use the maven goal:

	mvn package

The application can be started by:
	
	java -jar target/simpleweb-springboot-*.jar 
	
With Gradle the application can be build by the following Gradle command:

	gradlew build

Now the application can be started by the following command

	java -jar build/libs/simpleweb-springboot.jar
 
## Hot Deployment with Spring loaded and JHipster

Since Spring Boot version 1.3.x spring loaded is not needed anymore. 
To hot reload the application [Spring DevTools](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#using-boot-devtools) now can be used.

Spring Boot 1.3 DevTools includes an embedded LiveReload server, to get a browser plugin see [here](http://livereload.com/extensions/).

## Run Spring Boot Sample

To start the web application invoke:

	./gradlew bootRun

Open the URL http://localhost:8080 in a web browser. Login with the username "spring" and password "boot".

## Import into Eclipse 

The eclipse project configurations files can be build with gradle, invoke gradle:

	./gradlew eclipse

Or invoke the maven goal:

	mvn eclipse:eclipse
	
## Build Docker Image

To build the docker image with gradle invoke

	./gradlew build buildDocker
	
With maven the docker image can be build by invoke the maven goal

	mvn clean package docker:build

Tutorial how to crate a docker image for a spring boot application see [here](https://spring.io/guides/gs/spring-boot-docker).
