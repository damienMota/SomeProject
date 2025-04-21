# Development Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the Gradle files first for better caching
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Make gradlew executable
RUN chmod +x gradlew

# Copy the source code
COPY src src

# Run the application
CMD ["./gradlew", "bootRun"]

EXPOSE 8080
