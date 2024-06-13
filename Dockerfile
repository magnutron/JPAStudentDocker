# Use the Maven image to build the project
FROM maven:3.8.5-openjdk-8 AS build

# Set the working directory
WORKDIR /app

# Copy the project files to the working directory
COPY . .

# Grant execution permission to the Maven wrapper
RUN chmod +x mvnw

# Debugging step: List directory contents and Maven settings
RUN ls -la
RUN cat .mvn/wrapper/maven-wrapper.properties

# Ensure the Maven wrapper is executable
RUN ./mvnw --version

# Run Maven to build the project and generate the JAR file
RUN ./mvnw clean package -DskipTests

# Use a smaller base image for the final container
FROM openjdk:8-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
