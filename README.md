# The Tree coding challenge

## Used Stack 

   * Spring Boot 2.1.6.RELEASE
   * Java 8
   * Spring Framework
   * Maven
   * RestTemplate
   * MySQL 5.7
   * swagger
   * Docker compose
   * Liquibase (default is disable)

### Build Project

```
Disable maven test and install or package
```
### Swagger
http://localhost:8080/swagger-ui.html

### Tests
The strategy used for the tests is integration tests, using the [RestTemplate](https://docs.spring.io/spring-android/docs/current/reference/html/rest-template.html)

##### To run tests
Config Database in application.properties and you can run test methods in ApiTreeNodeTest


### Run app with docker-compose
After install or package run :
```
docker-compose up
```

### Used Algorithm
The algorithm used for save tree is The Nested Set. I used it to beter performance in read tree, in this approach dont need recursive loop to fetch data, which it has heavy workload on database engine 

_____

###### By: Milad Ranjbari