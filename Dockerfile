# Use a imagem base do OpenJDK 11
FROM openjdk:11

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR do seu aplicativo
COPY target/carbigdata-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta 8080
EXPOSE 8080

# Comando para iniciar o aplicativo Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]