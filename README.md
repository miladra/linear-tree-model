# Tradeshift coding challeng

## Used Stack 

   * Spring Boot 2.1.6.RELEASE
   * Java 8
   * Spring Framework
   * Liquibase
   * Maven
   * RestTemplate
   * H2
   * swagger
   * Docker

### Build Project

```
mvn clean install
```

### Tests
The strategy used for the tests was that of integration tests, using the [RestTemplate](https://docs.spring.io/spring-android/docs/current/reference/html/rest-template.html)

### Swagger

http://localhost:8080/swagger-ui.html


##### To run the tests
```
mvn test
```

### Run app with docker-compose
```
docker-compose up
```

_____

###### By: Milad Ranjbari