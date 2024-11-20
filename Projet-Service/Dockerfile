FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY ./target/projet-service-0.0.1-SNAPSHOT.jar /app/projet-service.jar
ENTRYPOINT ["java", "-jar", "/app/projet-service.jar"]
