FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy Maven wrapper and source code
COPY . .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/personal-expenses-tracker-1.0.0.jar"]