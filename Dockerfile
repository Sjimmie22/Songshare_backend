# Use an OpenJDK image as the base
FROM maven:3.6.3-openjdk-17-slim

# Set the working directory in the container
WORKDIR /usr/src/app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

# Expose the port the application runs on
EXPOSE 8080

RUN mvn package

# Define the command to run the application
CMD ["java", "-jar", "target/SongShare-Backend-0.0.1-SNAPSHOT.jar"]
