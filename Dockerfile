# Etapa de build com Maven
FROM maven:3.9.6-amazoncorretto-21-debian as build

WORKDIR /app

# Copia apenas o pom.xml para baixar as dependências
COPY ./pom.xml ./
RUN mvn dependency:go-offline -B

# Copia o restante do código fonte
COPY ./src ./src

# Executa o build do Maven com logs de debug
RUN mvn clean package -e -X -DskipTests

# Renomeia o JAR gerado para app.jar
RUN mv target/*.jar app.jar

# Etapa de execução com uma imagem mais leve
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o JAR da etapa de build
COPY --from=build /app/app.jar .

# Executa o JAR
CMD ["java", "-jar", "app.jar"]
