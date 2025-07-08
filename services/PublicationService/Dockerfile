#build stage
FROM maven:3.9.5-eclipse-temurin-21 as build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

#Runtime stage
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /build/target/Project-*.jar project.jar

# Run the JAR file
CMD ["java", "-jar", "project.jar"]

EXPOSE 8080