# Dockerfile
FROM openjdk:17

RUN mkdir -p /usr/src/myapp
COPY target/lab78.jar /usr/src/myapp
RUN mkdir -p /usr/src/myapp/target
COPY target/keystore.p12 /usr/src/myapp/target
WORKDIR /usr/src/myapp
ENTRYPOINT ["java", "-jar","lab78.jar"]

#RUN java -jar ../spring-boot-kafka-simple-listener-1.0.jar 
