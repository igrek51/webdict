### webdict

Web application for vocabulary training with dynamic word rankings.

### Technologies used

* Java 8
* Spring Core
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* MySQL
* JSP
* Tomcat / Wildfly
* JavaScript
* jQuery
* Maven
* CSS
* JSON
* Git
* HTML

Application is compatible with Wildfly application server but also can be run under Tomcat.

License: Beerware

### Build:
```
mvn package
```

### Run:
```
mvn spring-boot:run
```

### Run with in-memory DB:
```
mvn spring-boot:run -Dspring.profiles.active=memdb
mvn spring-boot:run -P memdb
```
