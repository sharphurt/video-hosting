FROM openjdk:21 as builder
WORKDIR /app
COPY . /app/.
RUN ./mvnw -f /app/pom.xml clean package -D maven.test.skip=true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]