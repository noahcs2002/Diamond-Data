# Use maven image to build the Spring Boot app
FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17-oracle
WORKDIR /api
COPY --from=builder /app/target/api-0.0.1-SNAPSHOT.jar /api/api.jar
EXPOSE 8080
CMD ["java", "-jar", "api.jar"]
