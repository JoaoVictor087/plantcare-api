# --- Estágio 1: Build (Construção) ---
# Usamos uma imagem completa do Maven e JDK para compilar o código
FROM maven:3.8-openjdk-17-slim AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia primeiro o pom.xml para aproveitar o cache do Docker
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o resto do código-fonte e constrói o projeto
COPY src ./src
RUN mvn package -DskipTests

# --- Estágio 2: Run (Execução) ---
# Usamos uma imagem Alpine com JRE, que é super leve e segura
FROM eclipse-temurin:17-jre-alpine

# O desafio pede um usuário sem privilégios de administrador
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar construído no estágio anterior
# IMPORTANTE: Troque 'seu-app-0.0.1-SNAPSHOT.jar' pelo nome exato do .jar que o seu projeto gera!
COPY --from=builder /app/target/seu-app-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que o Spring Boot usa (padrão 8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]