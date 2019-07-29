FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/coding-challenge-*.jar coding-challenge.jar
ENV JAVA_OPTS=""
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/coding-challenge.jar"]