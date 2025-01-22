# Use uma imagem base com o JDK
FROM openjdk:17-jdk-slim

# Adicione o arquivo JAR da aplicação
ARG JAR_FILE=target/custommer-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Exponha a porta que sua aplicação usa
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]
