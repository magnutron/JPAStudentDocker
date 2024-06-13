# Use an official Maven image to build the project
FROM maven:3.8.5-openjdk-8 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Ensure the Maven wrapper has execute permissions
RUN chmod +x mvnw

# Run Maven to build the project and generate the JAR file
RUN ./mvnw clean package -DskipTests -e -X

# Use a smaller base image for the final container
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the final container
COPY --from=build /app/target/*.jar app.jar

# Specify the command to run the application
CMD ["java", "-jar", "app.jar"]
