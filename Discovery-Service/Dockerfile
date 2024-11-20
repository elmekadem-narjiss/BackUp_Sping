FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY ./target/Discovery-Service-0.0.1-SNAPSHOT.jar /app/discovery-service.jar
ENTRYPOINT ["java", "-jar", "/app/discovery-service.jar"]
