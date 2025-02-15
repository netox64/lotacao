# Lota+

<div align="center">
  <img src="https://github.com/netox64/b_divulgapotiguar/blob/main/docs/api_print1.png" width="250" height="250" />
  <img src="https://github.com/netox64/b_divulgapotiguar/blob/main/docs/api_print2.png" width="250" height="250" />
</div>

<h4 align="center">Este é um projeto com intuito de facilitar a busca de otações para outras cidades, ele tem o intuito de promover, divulgar e organizar caravanas , serve tambem em parte como rede social.</h4>
<p align="center">
    <a href="#Tecnologias_Usadas">Tecnologias usadas</a> •
    <a href="#Api_resources">Recursos da Api</a> •
    <a href="#Arquitetura_de_pastas">Estrutura de pasta do backEnd</a> •
    <a href="#Rodando_plicação">Rodar aplicação</a> •
    <a href="#About_the_Author">Sobre o autor</a> •
    <a href="https://github.com/netox64/b_divulgapotiguar/blob/main/LICENSE">Licença</a>
</p>

## Tecnologias_Usadas

- As seguintes tecnologias foram utilizadas neste projeto:
    - Java como linguagem de programação.
    - aplicativo springboot.
    - E a ferramenta de conteinerização docker.
    - Next.js para construir o frontend.

## Arquitetura_de_pastas
```
api.src
  ├  └─  main.java.ofc.api
  ├                    └──────────  Config : all configs, springSecurity, Swagger ...
  ├                    └──────────  Controllers : all controllers, interfaces, Dtos ...
  ├                    └──────────  Entities  : models for aplication.
  ├                    └──────────  Repositories : Repositories, JPa base , generics ...
  ├                    └──────────  Services : all Services, generics and interfaces ...
  ├  
  └───────  main.resources   
  ├                    └──────────  aplication.properties
  ├  
  └───────  test
  ├──  docher-compose.yml
  └──  pom.xml : all dependecies
```

## Modelo_do_banco

<img src="https://github.com/netox64/b_divulgapotiguar/blob/main/docs/api_potiguar.png" />


## Prerequisitos

- SDKMAN for manager version jdk
- JDK 23
- Docker and Docke-compose plugin

## Rodando_plicação
- create a file called application-dev.properties inside the resources folder
- define a jwt with base 64 characters, and your zoho email and password. And finally a secret
- You can use this example if you want, just need your Zoho email and password.

```
  jwt=aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
  username=example@zohomail.com
  password=Example34#
  token_secret=um_toquen_qualquer
  cpf_secret=cpf_divulga
  
  client_id=Client_Id_...
  client_secret=Client_Secret_...
  certificate=./certs/suachavep12
```

- create the bank:
 ```
    cd api && docker compose up
 ```

- install dependencies:
 ```
    mvn install 

 ```

- to execute:
```
  java -jar target/nome_projeto-1.0-SNAPSHOT.jar 
  or
  mvn spring-boot:run
  
```
- simplify:
```
  docker-compose up -d && mvn spring-boot:run #or
  docker-compose start -d && mvn spring-boot:run
```

- http://localhost:8080/swagger-ui/index.html?urls.primaryName=public

## Test the application using postman

- open your postman desktop
- click import
- import from json Bruno endpoints folder

> [!IMPORTANT]
> When opening the endpoints, check the "base_url" addressing variable if it is correct.

> [!WARNING]
> When making requests, pay attention to the tokens and the app's authorization policies. Errors may also be found as it is still under development


## About_the_Author
- Clodoaldo Neto :call_me_hand:
