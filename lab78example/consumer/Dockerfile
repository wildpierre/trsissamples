# Dockerfile
FROM openjdk:17

RUN mkdir -p /usr/src/myapp
COPY target/spring-boot-kafka-simple-listener-1.0.jar /usr/src/myapp
WORKDIR /usr/src/myapp
ENTRYPOINT ["java", "-jar","spring-boot-kafka-simple-listener-1.0.jar"]

