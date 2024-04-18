# Use official OpenJDK image as base image
FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /api

# Copy the packaged jar file into the container
COPY target/api-0.0.1-SNAPSHOT.jar /api/api.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "api.jar"]
