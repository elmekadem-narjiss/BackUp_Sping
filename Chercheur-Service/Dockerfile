FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY ./target/chercheur-service-0.0.1-SNAPSHOT.jar /app/chercheur-service.jar
ENTRYPOINT ["java", "-jar", "/app/chercheur-service.jar"]
