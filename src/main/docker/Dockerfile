FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/coding-challenge-*.jar coding-challenge.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "coding-challenge.jar"]