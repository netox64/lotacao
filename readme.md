# Lota+

<div align="center">
  <img src="https://github.com/netox64/lotacao/blob/main/docs/api.png" width="250" height="250" />
  <img src="https://github.com/netox64/lotacao/blob/main/docs/api2.png" width="250" height="250" />
</div>

<h4 align="center">Este é um projeto serve apenas para treinamento de stratégia de herança no mapeamento de entidades no banco de dados, fora isso pode ser que ele adquira  a capacidade de facilitar a busca de lotações para outras cidades, ele tem o proposito de promover, divulgar e organizar caravanas , serve tambḿm em parte como rede social.</h4>
<p align="center">
    <a href="#Tecnologias_Usadas">Tecnologias usadas</a> •
    <a href="#Api_resources">Recursos da Api</a> •
    <a href="#Arquitetura_de_pastas">Estrutura de pasta do backEnd</a> •
    <a href="#Rodando_plicação">Rodar aplicação</a> •
    <a href="#About_the_Author">Sobre o autor</a> •
    <a href="https://github.com/netox64/lotacao/blob/main/LICENSE">Licença</a>
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

<img src="https://github.com/netox64/lotacao/blob/main/docs/conceitual.png" />


## Prerequisitos

- SDKMAN for manager version jdk
- JDK 23
- Docker and Docke-compose plugin

## Rodando_plicação
- crie um arquivo chamado application-dev.properties dentro da pasta de recursos
- defina um jwt com base de 64 caracteres, e seu e-mail e senha zoho. E finalmente um segredo
- Você pode usar este exemplo se quiser, só precisa do seu e-mail e senha do Zoho.

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

- crie o banco::
 ```
    cd api && docker compose up
 ```

- instalar dependências:
 ```
    mvn install 

 ```

- para executar:
```
  java -jar target/nome_projeto-1.0-SNAPSHOT.jar 
  or
  mvn spring-boot:run
  
```
- simplificando:
```
  docker-compose up -d && mvn spring-boot:run #or
  docker-compose start -d && mvn spring-boot:run
```

- http://localhost:8080/swagger-ui/index.html?urls.primaryName=public


> [!IMPORTANT]
> Não há nada importante.

> [!WARNING]
> Ao fazer solicitações, preste atenção aos tokens e às políticas de autorização do app. Erros também podem ser encontrados, pois ainda está em desenvolvimento


## About_the_Author
- Clodoaldo Neto :call_me_hand:
