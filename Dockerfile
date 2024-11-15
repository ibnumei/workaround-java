# Tahap builder: Gunakan base image Java 21 dengan Maven
FROM maven:3.9.4-amazoncorretto-21 AS builder
# Set direktori kerja untuk tahap builder
WORKDIR /app
# Salin file pom.xml dan unduh dependensi Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B
# Salin seluruh kode aplikasi
COPY src /app/src
# Build aplikasi
RUN mvn package -DskipTests

# Tahap final: Gunakan base image Java 21 yang lebih ringan
FROM amazoncorretto:21-alpine
# Set direktori kerja untuk tahap final
WORKDIR /app
# Salin file JAR dari tahap builder
COPY --from=builder /app/target/*.jar app.jar
# Expose port aplikasi
EXPOSE 8082
# Jalankan aplikasi Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
