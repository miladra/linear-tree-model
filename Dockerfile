FROM openjdk:8-jdk-alpine
ADD target/coding-challenge-*.jar coding-challenge.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/coding-challenge.jar"]