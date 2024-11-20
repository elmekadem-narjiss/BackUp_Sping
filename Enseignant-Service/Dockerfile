FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY ./target/Enseignant-Service-0.0.1-SNAPSHOT.jar /app/enseignant-service.jar
ENTRYPOINT ["java", "-jar", "/app/enseignant-service.jar"]
