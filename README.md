WeatherHub API
Sobre o projeto

Este repositório contém uma aplicação Spring Boot que expõe uma API REST simples para cadastrar usuários, registrar cidades favoritas e manter um histórico de buscas. A ideia deste desafio é exercitar a construção de uma API do zero, utilizando Java 17, Spring Data JPA com banco H2 em memória, e boas práticas de desenvolvimento (camadas, validações, tratamento de erros e documentação).
Você encontrará classes separadas em pacotes de modelo, repositório, serviço e controlador, cada um com sua responsabilidade bem definida.

Pré‑requisitos

Antes de rodar o projeto, certifique‑se de ter instalado:

Java 17 (ou superior). Você pode verificar com java -version.

Maven 3.8+ caso não queira usar o wrapper.
O projeto inclui um wrapper mvnw.cmd (Windows) / mvnw (Linux/Mac) que baixa automaticamente a versão correta do Maven, então você não precisa instalar manualmente.

Opcionalmente, ter uma IDE como IntelliJ IDEA ajuda no desenvolvimento e nos testes, mas não é obrigatório para rodar a aplicação.

Como executar a aplicação
1. Clonar o repositório

Abra um terminal e clone o projeto (ou baixe o zip pelo GitHub):

git clone https://github.com/joaopfigo/weatherhub-api-mobile.git
cd weatherhub-api-mobile

2. Rodar localmente

Existem duas formas principais de executar a aplicação localmente:

Usando o Maven Wrapper (recomendado)

O wrapper (mvnw/mvnw.cmd) já está no repositório e garante que todo mundo use a mesma versão do Maven. Para subir a aplicação, rode no terminal a partir da raiz do projeto:

./mvnw spring-boot:run      # Linux/Mac


Ou, no PowerShell do Windows:

./mvnw.cmd spring-boot:run

Usando Maven instalado

Se preferir usar seu Maven global, execute:

mvn spring-boot:run

Usando a IDE (IntelliJ IDEA)

Importe o projeto como Projeto Maven. Em seguida, localize a classe WeatherhubApplication e clique em Run. A aplicação iniciará em http://localhost:8080.

Ao inicializar, você verá logs como Tomcat started on port(s): 8080 e Started WeatherhubApplication indicando que tudo deu certo.

Acessando o banco H2

A aplicação utiliza um banco H2 em memória. Isso significa que os dados são descartados quando a aplicação é finalizada. Durante a execução, você pode inspecionar as tabelas via H2 Console:

Abra o navegador e acesse http://localhost:8080/h2-console/ (não esqueça da barra no final).

Use as credenciais padrão:

JDBC URL: jdbc:h2:mem:weatherhub

User Name: sa

Password: em branco

Clique em Connect. Você verá as tabelas USERS, FAVORITE_CITY e SEARCH_HISTORY geradas automaticamente.

Se preferir persistir dados mesmo após encerrar a aplicação, você pode alterar a configuração do datasource no application.properties para usar um arquivo no disco. Por padrão, o projeto mantém tudo em memória para facilitar os testes.

Endpoints da API

Os endpoints seguem a estrutura solicitada no enunciado. Para testar, você pode usar curl, Insomnia/Postman ou até mesmo o PowerShell (Invoke‑RestMethod no Windows). Em todos os exemplos JSON abaixo, assume‑se que a aplicação está rodando em localhost:8080.

1. Usuários
Método	URL	Descrição
POST	/api/users	Cria um novo usuário. Recebe JSON com name e email.
GET	/api/users/{id}	Busca um usuário pelo identificador. Retorna JSON do usuário ou 404 se não existir.

Exemplo de criação de usuário (PowerShell):

Invoke-RestMethod -Method Post `
  -Uri "http://localhost:8080/api/users" `
  -ContentType "application/json; charset=utf-8" `
  -Body '{"name":"João","email":"joao@email.com"}'


Resposta (201 Created):

{
  "id": 1,
  "name": "João",
  "email": "joao@email.com",
  "createdAt": "2026-02-13T13:43:03.1117541"
}


Buscar o usuário 1:

Invoke-RestMethod -Method Get -Uri "http://localhost:8080/api/users/1"

2. Cidades favoritas

Todos os endpoints de favoritos pertencem a um usuário ({userId}).

Método	URL	Descrição
GET	/api/users/{userId}/favorites	Lista todas as cidades favoritas do usuário.
POST	/api/users/{userId}/favorites	Adiciona uma nova cidade favorita. Recebe JSON com cityName, country, latitude e longitude.
DELETE	/api/users/{userId}/favorites/{cityId}	Remove uma cidade favorita específica do usuário, usando o ID retornado ao criar.

Exemplo de adicionar favorita (PowerShell):

Invoke-RestMethod -Method Post `
  -Uri "http://localhost:8080/api/users/1/favorites" `
  -ContentType "application/json; charset=utf-8" `
  -Body '{"cityName":"Belo Horizonte","country":"BR","latitude":-19.92,"longitude":-43.94}'


Resposta:

{
  "id": 1,
  "userId": 1,
  "cityName": "Belo Horizonte",
  "country": "BR",
  "latitude": -19.92,
  "longitude": -43.94,
  "addedAt": "2026-02-13T13:43:50.8117612"
}


Para remover a cidade favorita:

Invoke-RestMethod -Method Delete -Uri "http://localhost:8080/api/users/1/favorites/1"


Esse comando retorna status 204 No Content, indicando que a exclusão ocorreu sem erros.

3. Histórico de buscas

O histórico também é vinculado a um usuário. Cada registro armazena a cidade pesquisada, a temperatura observada, a condição do clima e a data/hora.

Método	URL	Descrição
GET	/api/users/{userId}/history	Lista todo o histórico de buscas do usuário. Retorna lista de objetos ou vazia.
POST	/api/users/{userId}/history	Registra uma nova busca. Recebe JSON com cityName, temperature (número) e condition (texto).
DELETE	/api/users/{userId}/history	Limpa todo o histórico de buscas do usuário. Retorna 204 se bem‑sucedido.

Exemplo de registrar uma busca:

Invoke-RestMethod -Method Post `
  -Uri "http://localhost:8080/api/users/1/history" `
  -ContentType "application/json; charset=utf-8" `
  -Body '{"cityName":"Belo Horizonte","temperature":25.3,"condition":"Ensolarado"}'


Resposta:

{
  "id": 1,
  "userId": 1,
  "cityName": "Belo Horizonte",
  "temperature": 25.3,
  "condition": "Ensolarado",
  "searchedAt": "2026-02-13T13:45:00.5138491"
}


Para limpar o histórico:

Invoke-RestMethod -Method Delete -Uri "http://localhost:8080/api/users/1/history"
