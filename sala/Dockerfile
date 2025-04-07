###Para Produção
# Etapa 1: Construção do JAR
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final com a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/salaMicroService*.jar /app/salaMicroService.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "salaMicroService.jar"]