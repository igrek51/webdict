# webdict
[![Build Status](https://travis-ci.org/igrek51/webdict.svg?branch=master)](https://travis-ci.org/igrek51/webdict)

Sample Web application for vocabulary training with dynamic word rankings.

## Technologies used:
* Java 8
* Spring Framework (Core)
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* MySQL
* H2
* Angular 5
* RxJS
* Tomcat
* Maven
* jUnit
* Lombok
* REST
* Bootstrap
* TypeScript
* jQuery
* CSS
* Git
* Travis CI

## Run
### Run with In-Memory Database (H2):
```
mvn spring-boot:run
```
### Run with sample test data (H2):
```
mvn spring-boot:run -P data
```
### Run with local MySQL Database:
```
mvn spring-boot:run -P test
```

## Build
### Dev environment (In-Memory H2 Database)
```
mvn package
```
### Test environment (MySQL Database)
```
mvn package -P test
```
### Production environment (MySQL Database)
```
mvn package -P prod
```

## Run tests
```
mvn test
```

## Example screens:
![alt tag](https://github.com/igrek51/webdict/blob/master/wiki/img/webdict-screen-1.png)
![alt tag](https://github.com/igrek51/webdict/blob/master/wiki/img/webdict-screen-2.png)
