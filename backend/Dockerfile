# =========================
# Build stage
# =========================
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app

# Copy the parent pom and the backend pom
COPY pom.xml .
COPY backend/pom.xml backend/

# Cache dependencies
RUN mvn -B -f backend/pom.xml dependency:go-offline

# Copy source and build
COPY backend/src backend/src
RUN mvn -B -f backend/pom.xml package -DskipTests

# =========================
# Production stage (distroless)
# =========================
FROM gcr.io/distroless/java25-debian13 AS prod
WORKDIR /app

USER 1000

# The SPRING_PROFILES_ACTIVE will be set by the deployment environment (e.g., Render)
# ENV SPRING_PROFILES_ACTIVE=prod

COPY --from=build /app/backend/target/pas-backend.jar pas-backend.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/pas-backend.jar"]