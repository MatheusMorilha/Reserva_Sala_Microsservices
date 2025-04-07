###Para Produção
# Etapa 1: Construção do JAR
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final com a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/reservaMicroService*.jar /app/reservaMicroService.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "reservaMicroService.jar"]