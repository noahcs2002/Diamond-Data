FROM openjdk:17-oracle
WORKDIR /api
COPY target/api-0.0.1-SNAPSHOT.jar /api/api.jar
EXPOSE 8080
CMD ["java", "-jar", "api.jar"]