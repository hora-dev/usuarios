FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn dependency:go-offline
RUN mvn clean package

FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /app/target/usuarios-0.0.1-SNAPSHOT.jar /app
EXPOSE 8008
CMD ["java", "-jar", "usuarios-0.0.1-SNAPSHOT.jar"]