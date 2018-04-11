# webdict

Web application for vocabulary training with dynamic word rankings.

## Technologies used:
* Java 8
* Spring Core
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* MySQL
* Thymeleaf
* Tomcat
* JavaScript
* jQuery
* AJAX
* Maven
* REST
* JSON
* CSS
* Bootstrap
* Git
* HTML

License: Beerware

## Build:
### Dev environment
```
mvn package
```
### Test environment
```
mvn package -P test
```
### Production environment
```
mvn package -P prod
```

## Run with Hibernate DB (MySQL):
```
mvn spring-boot:run
```

## Run with in-memory DB:
```
mvn spring-boot:run -P memdb
```

## Example screen:
![alt tag](https://github.com/igrek51/webdict/blob/master/wiki/webdict-screen-1.png)
