
# Projeto Microserviços com Spring Boot e Docker Compose

## Visão Geral

Este projeto implementa um sistema baseado em microserviços utilizando Spring Boot, onde cada serviço possui seu próprio banco de dados PostgreSQL dedicado. A comunicação assíncrona entre os serviços é realizada via RabbitMQ, e as requisições externas são roteadas por um API Gateway.

## Serviços

| Serviço            | Porta Exposta | Função                                 |
|--------------------|---------------|----------------------------------------|   
| UserMicroservice   | 8081          | Gerencia dados de usuários             |
| SalaMicroservice   | 8082          | Gerencia dados de salas                |
| ReservaMicroservice| 8083          | Gerencia reservas                      |
| Gateway            | 8080          | Roteador central das requisições       |
| RabbitMQ           | 5672 / 15672  | Mensageria e painel de gerenciamento   |
| Adminer            | 8085          | Interface web para administração do DB |

## Tecnologias Utilizadas

- Spring Boot  
- PostgreSQL 15  
- RabbitMQ 3 (com interface de gerenciamento)  
- Adminer (gerenciamento de banco via web)  
- Docker e Docker Compose  

## Como Executar

1. Clone este repositório:

```bash
git clone https://github.com/MatheusMorilha/Reserva_Sala_Microsservices.git
cd Reserva_Sala_Microsservices
```

2. Inicie os containers:

```bash
docker-compose up --build
```

3. Acesse os serviços:

- API Gateway: [http://localhost:8080](http://localhost:8080)  
- RabbitMQ Management: [http://localhost:15672](http://localhost:15672)  
  Usuário/senha: guest/guest  
- Adminer: [http://localhost:8085](http://localhost:8085)  
  Conecte-se ao PostgreSQL usando o host do container, usuário e senha conforme o serviço

## Configuração dos Bancos de Dados

| Serviço            | Banco       | Usuário  | Senha | Porta Externa |
|--------------------|-------------|----------|-------|---------------|
| usermicroservice   | usersdb     | postgres | admin | 5433          |
| salamicroservice   | salasdb     | postgres | admin | 5434          |
| reservamicroservice| reservasdb  | postgres | admin | 5435          |

## Persistência

Os dados são armazenados em volumes Docker para garantir persistência após reiniciar os containers:

- userdbdata  
- saladbdata  
- reservadbdata  

## Redes

O sistema utiliza redes Docker customizadas para isolar os serviços e garantir a comunicação adequada:

- usernet  
- salanet  
- reservenet  
- rabbitnet  
- gatewaynet  

---


