# Use official Node.js image as base for the React app
FROM node:14 AS react-build

# Set working directory for the React app
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY app/package*.json ./

# Install dependencies for the React app
RUN npm install

# Copy the rest of the React app source code to the working directory
COPY app/ .

# Build the React app
RUN npm run build

# Use official OpenJDK image as base for the Spring Boot app
FROM openjdk:14 AS spring-boot-build

# Set working directory for the Spring Boot app
WORKDIR /api

# Copy the Spring Boot app source code to the working directory
COPY api/ .

# Build the Spring Boot app
RUN ./mvnw package -DskipTests

# Use AdoptOpenJDK image as the final base image
FROM adoptopenjdk/openjdk14:alpine-slim

# Set working directory
WORKDIR /usr/src/app

# Copy built React app from the previous stage
COPY --from=react-build /app/build ./src/main/resources/static

# Copy built Spring Boot JAR from the previous stage
COPY --from=spring-boot-build /api/target/*.jar ./app.jar

# Expose ports
EXPOSE 8080

# Command to run the Spring Boot app when the container starts
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]

