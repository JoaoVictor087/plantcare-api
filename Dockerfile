# --- Estágio 1: Build (Construção com JDK 25) ---
FROM maven:3.9-eclipse-temurin-25 AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia primeiro o pom.xml para aproveitar o cache do Docker
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o resto do código-fonte e constrói o projeto
COPY src ./src
RUN mvn package -DskipTests

# --- Estágio 2: Run (Execução com JRE 25) ---
# Usamos uma imagem Alpine com JRE 25, que é leve e segura
FROM eclipse-temurin:25-jre-alpine

# O desafio pede um usuário sem privilégios de administrador
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar construído no estágio anterior
# IMPORTANTE: Troque pelo nome exato do seu .jar!
COPY --from=builder /app/target/plantcare-api-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que o Spring Boot usa (padrão 8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]