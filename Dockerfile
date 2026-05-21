# =========================
# Build stage
# =========================
FROM maven:3.9-eclipse-temurin-26 AS build
WORKDIR /app

# Abhaengigkeiten cachen
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Source kopieren & bauen
COPY src ./src
RUN mvn -B package -DskipTests

# =========================
# Debug stage (mit Shell)
# =========================
FROM eclipse-temurin:26-jdk AS debug
WORKDIR /app

RUN useradd -m appuser
USER appuser

ENV SPRING_PROFILES_ACTIVE=docker

COPY --from=build /app/target/pas.jar pas.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","pas.jar"]

# =========================
# Production stage (distroless)
# =========================
FROM gcr.io/distroless/java25-debian13 AS prod
WORKDIR /app

USER 1000

ENV SPRING_PROFILES_ACTIVE=prod

COPY --from=build /app/target/pas.jar pas.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/pas.jar"]
