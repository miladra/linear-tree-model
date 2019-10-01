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
   * lombok
   * MapStruct

### Build Project

```
mvn clean install -Dmaven.test.skip=true
```

### Run Project

Database url configured to used msql inside docker if you want to run application without docker please config your mysql server application.proerties, so you need change below config

spring.datasource.url=jdbc:mysql://db-mysql:3306/db

spring.datasource.username=user

spring.datasource.password=password

spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect

### Swagger
http://localhost:8080/swagger-ui.html

### Tests
The strategy used for the tests is integration tests, using the [RestTemplate](https://docs.spring.io/spring-android/docs/current/reference/html/rest-template.html)

##### To run tests
Config Database in application.properties and you can run test methods in ApiTreeNodeTest


### Run app with docker-compose

In root directory
```
mvn clean install -Dmaven.test.skip=true

docker-compose up
```

### Used Algorithm
The algorithm used for save tree is The Nested Set. I used it to beter performance in read tree, in this approach dont need recursive loop to fetch data, which it has heavy workload on database engine 

_____

###### By: Milad Ranjbari
