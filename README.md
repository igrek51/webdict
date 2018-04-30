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
* AssertJ
* Lombok
* REST
* Bootstrap
* TypeScript
* npm
* jQuery
* CSS
* Git
* Travis CI

## Run
### Run with In-Memory Database (H2):
```bash
mvn spring-boot:run
```
### Run with sample test data (H2):
```bash
mvn spring-boot:run -P data
```
### Run with local MySQL Database:
```bash
mvn spring-boot:run -P test
```
### Re-run Java back-end only (don't rebuild front-end):
```bash
mvn spring-boot:run -DskipFrontendBuild
```
### Re-run Angular front-end only (don't rebuild back-end):
```bash
cd src/frontend
npm start
```

## Build
### Dev environment (In-Memory H2 Database)
```bash
mvn package
```
### Test environment (MySQL Database)
```bash
mvn package -P test
```
### Production environment (MySQL Database)
```bash
mvn package -P prod
```
### Rebuild Angular front-end only (don't rebuild back-end):
```bash
cd src/frontend
ng build --prod --output-path=../main/resources/static
```

## Run tests
```bash
mvn test
```

## Example screens:
![alt tag](https://github.com/igrek51/webdict/blob/master/wiki/img/webdict-screen-1.png)
![alt tag](https://github.com/igrek51/webdict/blob/master/wiki/img/webdict-screen-2.png)
