# ===== Stage 1: build =====
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copying pom.xml and dependencies separately (cached)
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copying source files
COPY src ./src

# Building the jar
RUN mvn -B clean package -DskipTests

# ===== Stage 2: runtime =====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copying jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Application port
EXPOSE 8087  # 8080

# Healthcheck (Spring Boot Actuator)
HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 \
  CMD wget -qO- http://localhost:8087/actuator/health || exit 1

# Application startup
ENTRYPOINT ["java", "-jar", "app.jar"]

