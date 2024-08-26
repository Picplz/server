# Step 1: Build the application
FROM openjdk:17-jdk-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and related files
COPY gradlew .
COPY gradle gradle

# Copy project dependency files
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src src

# Ensure the Gradle wrapper has execution rights
RUN chmod +x ./gradlew

# Build the Spring Boot application using Gradle
RUN ./gradlew bootJar

# Step 2: Create a minimal image with only the JAR file
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /picplz

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Set the active Spring profile to 'local'
#ENV SPRING_PROFILES_ACTIVE=dev

# Expose port 8000 to the outside world
EXPOSE 8000

# Run the application
ENTRYPOINT ["java", "-jar", "/picplz/app.jar"]
