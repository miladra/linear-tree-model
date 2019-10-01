FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/coding-challenge-0.0.1-SNAPSHOT.jar coding-challenge.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "coding-challenge.jar"]