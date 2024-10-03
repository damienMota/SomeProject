# Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build context
COPY build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

# Define environment variables for database connectivity
ENV SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/my_project_db
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=Oimadi*1

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
