# Projeto Spring Docker com Ngrok

Este projeto é uma aplicação backend desenvolvida com o framework Spring Boot, empacotada em um arquivo JAR, que é executada dentro de um container Docker. A aplicação conecta-se a um banco de dados PostgreSQL e é exposta à internet utilizando o serviço Ngrok.

## Requisitos

Antes de começar, você precisa ter os seguintes pré-requisitos instalados:

- **Docker**: Para rodar os containers.
- **Docker Compose**: Para orquestrar os containers.
- **Maven**: Para compilar a aplicação Spring Boot.
- **Conta Ngrok**: Para gerar um token de autenticação do Ngrok.

## Estrutura do Projeto

- **Docker Compose**: Define os serviços de PostgreSQL, aplicação Spring Boot e Ngrok.
- **Dockerfile**: Define a construção da imagem Docker para a aplicação Spring.
- **application.properties**: Configuração de conexão com o banco de dados PostgreSQL.

## Como Rodar a Aplicação

### Passo 1: Preparar a Aplicação Spring

Certifique-se de ter a aplicação Spring corretamente configurada e o arquivo JAR gerado.

Navegue até o diretório do projeto e execute o comando para gerar o arquivo JAR:

```bash
mvn clean package -DskipTests
```
Isso gerará o arquivo sprindock-0.0.1-SNAPSHOT.jar na pasta target/.

### Passo 2: Construir a Imagem Docker

Em seguida, crie a imagem Docker para a aplicação:

```bash
docker build -t sprindock .
```

### Passo 3: Iniciar os Containers com Docker Compose

Para rodar os containers, use o Docker Compose:

```bash
docker-compose up --build
```
Esse comando irá:

- Iniciar o container do PostgreSQL.
- Construir e iniciar o container da aplicação Spring Boot.
- Iniciar o container Ngrok para expor a aplicação na internet.

O Docker Compose vai criar uma rede local para os containers se comunicarem entre si.

### Passo 4: Configurar o Ngrok

Para usar o Ngrok, você precisa de um token de autenticação. Caso ainda não tenha, crie uma conta no Ngrok e copie o seu authtoken.

Substitua o valor do `NGROK_AUTHTOKEN` no arquivo `docker-compose.yml`:

```yaml
ngrok:
  image: ngrok/ngrok:latest
  container_name: ngrok
  environment:
    - NGROK_AUTHTOKEN=seu_token_aqui
  command: "http app:8080"
  ports:
    - "4040:4040"
  networks:
    - local-network
```
Esse token é necessário para que o Ngrok possa expor a sua aplicação de forma segura.

### Passo 5: Acessar a Aplicação

Após rodar o `docker-compose up`, você pode acessar a aplicação via Ngrok.

O Ngrok irá gerar um URL público que pode ser acessado fora da sua rede local. O link de acesso estará disponível no painel web do Ngrok, que pode ser acessado em [http://localhost:4040](http://localhost:4040).

No terminal, ao rodar o comando acima, você verá uma saída parecida com:

```bash
ngrok by @inconshreveable (Ctrl+C to quit)

Session Status                online
Account                       YourAccountName (Plan: Free)
Version                       2.x.x
Region                        United States (us)
Web Interface                 http://localhost:4040
Forwarding                    http://xyz12345.ngrok.io -> http://app:8080
```
O link http://xyz12345.ngrok.io é a URL pública que você pode usar para acessar a aplicação Spring Boot, que estará rodando na porta 8080 dentro do container.

O link `http://xyz12345.ngrok.io` é a URL pública que você pode usar para acessar a aplicação Spring Boot, que estará rodando na porta 8080 dentro do container.

### Passo 6: Desligar a Swap (Opcional)

Em sistemas Linux, para evitar problemas de desempenho com o Docker, você pode desligar o swap com o comando:

```bash
sudo swapoff -a
```

### Passo 7: Encerrar os Containers

Quando terminar de usar a aplicação, você pode parar todos os containers com o comando:

```bash
docker-compose down
```

### Detalhes de Configuração

#### Arquivo `docker-compose.yml`

Este arquivo define três serviços:

- **postgres**: Serviço que roda o banco de dados PostgreSQL.
- **app**: Serviço que roda a aplicação Spring Boot.
- **ngrok**: Serviço que expõe a aplicação na internet.

#### Arquivo `Dockerfile`

O `Dockerfile` define o ambiente necessário para rodar a aplicação Spring Boot dentro de um container Docker. Ele copia o arquivo JAR da aplicação para o container e executa a aplicação com o comando `java -jar`.

#### Arquivo `application.properties`

Este arquivo contém as configurações de conexão com o banco de dados PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://postgres:5432/meubanco
spring.datasource.username=usuario
spring.datasource.password=senha
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=create
```

