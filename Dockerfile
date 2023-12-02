FROM eclipse-temurin:17-jdk-jammy
LABEL authors="squad_3"

COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
