FROM maven:3.9.6-amazoncorretto-21-debian as build

WORKDIR /app
RUN ls
COPY ./pom.xml .
RUN ls

COPY ./src ./src

RUN mvn clean package -e -DskipTests
RUN ls
RUN mv target/*.jar app.jar
RUN ls
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/app.jar .

CMD ["java", "-jar", "app.jar"]
