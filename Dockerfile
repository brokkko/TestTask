FROM maven:3.8.1-openjdk-17 AS builder
WORKDIR /app
COPY ./cellphone-validator ./cellphone-validator
COPY ./pom.xml ./pom.xml

RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:17
COPY --from=builder ./app/cellphone-validator/target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]