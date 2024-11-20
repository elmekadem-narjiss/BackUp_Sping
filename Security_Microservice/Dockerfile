FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY ./target/Security_Microservice-0.0.1-SNAPSHOT.jar /app/security-service.jar
ENTRYPOINT ["java", "-jar", "/app/security-service.jar"]
