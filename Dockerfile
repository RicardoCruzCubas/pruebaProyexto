# Etapa 1: Build con Maven
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

WORKDIR /build

# Copiar archivos de configuración de Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Descargar dependencias
RUN mvn dependency:go-offline -B

# Copiar código fuente y recursos
COPY src ./src

# Compilar sin tests
RUN mvn clean package -DskipTests -q

# Etapa 2: Runtime optimizado
FROM eclipse-temurin:17-jre-alpine

# Metadatos
LABEL maintainer="avance2proyecto"
LABEL description="Spring Boot Application with PostgreSQL/Yugabyte"

WORKDIR /app

# Instalar ca-certificates para SSL
RUN apk add --no-cache ca-certificates

# Crear usuario no-root para seguridad
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# Copiar el JAR compilado desde la etapa build
COPY --from=builder /build/target/*.jar app.jar

# Copiar el certificado SSL para Yugabyte
COPY --from=builder /build/root.crt /app/root.crt

# Cambiar permisos
RUN chown -R appuser:appgroup /app && \
    chmod 600 /app/root.crt

# Cambiar a usuario no-root
USER appuser

# Exponer el puerto
EXPOSE 8080

# Variables de entorno
ENV JAVA_OPTS="-XX:+UseG1GC -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=25.0 -Djava.security.egd=file:/dev/./urandom"
ENV SPRING_PROFILES_ACTIVE=prod

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD java -cp /app/app.jar org.springframework.boot.loader.launch.PropertiesLauncher --spring.boot.loader.path=/app

# Comando de inicio
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar --server.port=${PORT:-8080}"]
