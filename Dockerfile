# Use a Maven image to build the project
FROM maven:3.8.5-openjdk-8 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Debugging step: List directory contents
RUN ls -l

# Make the mvnw script executable
RUN chmod +x ./mvnw

# Debugging step: Check the .m2 directory
RUN ls -l /root/.m2

# Run Maven to build the project and generate the JAR file with full debug logging
RUN ./mvnw clean package -DskipTests -X

# Use a smaller base image for the final container
FROM openjdk:8-jdk-alpine

# Set an environment variable to define the location of the JAR file
ENV JAR_FILE=target/*.jar

# Copy the JAR file from the build stage to the final image
COPY --from=build /app/${JAR_FILE} app.jar

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "/app.jar"]
