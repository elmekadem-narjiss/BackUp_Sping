FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY ./target/Gateway-Service-0.0.1-SNAPSHOT.jar /app/gateway-service.jar
ENTRYPOINT ["java", "-jar", "/app/gateway-service.jar"]
