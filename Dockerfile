# Imagem base
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR gerado pela aplicação para o container
COPY target/sprindock-0.0.1-SNAPSHOT.jar /app/sprindock-0.0.1-SNAPSHOT.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "sprindock-0.0.1-SNAPSHOT.jar"]
