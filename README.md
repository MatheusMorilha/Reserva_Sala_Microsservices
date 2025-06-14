# Projeto Microsserviços: Gerenciamento de Reservas de Salas

## Visão Geral

Este projeto demonstra uma **arquitetura de microsserviços** completa utilizando **Spring Boot** para gerenciar usuários, salas e reservas. Cada serviço é independente, possui seu próprio banco de dados PostgreSQL e se comunica de forma assíncrona via RabbitMQ. Todas as requisições externas são centralizadas e roteadas através de um API Gateway.

---

## Estrutura do Projeto

O projeto é modularizado, com cada diretório raiz representando um microsserviço ou componente principal:

* **`user/`**: Contém o microsserviço de gerenciamento de **Usuários**.
* **`sala/`**: Contém o microsserviço de gerenciamento de **Salas**.
* **`reserva/`**: Contém o microsserviço de gerenciamento de **Reservas**.
* **`gateway/`**: Contém o **API Gateway**, responsável pelo roteamento das requisições.

---

## Serviços e Portas

| Serviço             | Porta Exposta | Função                                    |
| :------------------ | :------------ | :---------------------------------------- |
| `UserMicroservice`  | `8081`        | Gerencia o cadastro e dados de **usuários**. |
| `SalaMicroservice`  | `8082`        | Gerencia o cadastro e disponibilidade de **salas**. |
| `ReservaMicroservice` | `8083`        | Gerencia a lógica de **reservas** de salas. |
| `Gateway`           | `8080`        | Ponto de entrada central, **roteia** requisições para os microsserviços. |
| `RabbitMQ`          | `5672`        | **Broker de mensagens** para comunicação assíncrona entre serviços. |
| `RabbitMQ Management` | `15672`       | Interface web para **gerenciar filas e trocas** do RabbitMQ. |
| `Adminer`           | `8085`        | Interface web para **administração fácil** dos bancos de dados PostgreSQL. |

---

## Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias principais:

* **Spring Boot**: Framework Java para desenvolvimento rápido de microsserviços.
* **PostgreSQL 15**: Sistema de gerenciamento de banco de dados relacional.
* **RabbitMQ 3**: Broker de mensagens para comunicação assíncrona.
* **Adminer**: Ferramenta leve de gerenciamento de banco de dados via web.
* **Docker e Docker Compose**: Para containerização e orquestração dos serviços.

---

## Como Executar o Projeto

Certifique-se de ter o **Docker** e o **Docker Compose** instalados em sua máquina.

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/MatheusMorilha/Reserva_Sala_Microsservices.git](https://github.com/MatheusMorilha/Reserva_Sala_Microsservices.git)
    ```
2.  **Navegue até o diretório do projeto:**
    ```bash
    cd Reserva_Sala_Microsservices
    ```
3.  **Inicie os containers:**
    Este comando irá construir as imagens Docker dos microsserviços (se ainda não existirem) e iniciar todos os serviços definidos no `docker-compose.yml`. A primeira inicialização pode levar alguns minutos.
    ```bash
    docker-compose up --build -d
    ```

---

## Acessando os Serviços

Após a inicialização dos containers, você pode acessar os seguintes serviços:

* **API Gateway**: `http://localhost:8080`
    * Exemplo de rota (se configurado): `http://localhost:8080/users/1`
* **RabbitMQ Management**: `http://localhost:15672`
    * Credenciais padrão: `guest`/`guest`
* **Adminer**: `http://localhost:8085`
    * Para conectar-se a um banco de dados PostgreSQL, utilize as configurações abaixo. O **host** deve ser o nome do serviço Docker (ex: `dbuser`, `dbsala`, `dbreserva`).

---

## Configuração dos Bancos de Dados

Cada microsserviço possui seu próprio banco de dados PostgreSQL. As credenciais e portas para acesso via Adminer ou ferramentas externas são:

| Serviço Relacionado | Nome do Banco | Usuário    | Senha    | Porta Externa (para conexão direta) |
| :------------------ | :------------ | :--------- | :------- | :---------------------------------- |
| `usermicroservice`  | `usersdb`     | `postgres` | `admin`  | `5433`                              |
| `salamicroservice`  | `salasdb`     | `postgres` | `admin`  | `5434`                              |
| `reservamicroservice` | `reservasdb`  | `postgres` | `admin`  | `5435`                              |

---

## Persistência de Dados

Para garantir que os dados dos bancos de dados sejam persistidos mesmo após o reinício dos containers, são utilizados **volumes Docker** dedicados:

* `userdbdata`
* `saladbdata`
* `reservadbdata`

---

## Redes Docker

O sistema utiliza redes Docker customizadas para otimizar a comunicação interna e o isolamento dos serviços:

* **`usernet`**: Rede dedicada para o `usermicroservice` e seu banco de dados `dbuser`.
* **`salanet`**: Rede dedicada para o `salamicroservice` e seu banco de dados `dbsala`.
* **`reservanet`**: Rede dedicada para o `reservamicroservice` e seu banco de dados `dbreserva`.
* **`gatewaynet`**: Rede principal que conecta o `Gateway` a todos os microsserviços, permitindo o roteamento de requisições externas.
* **`rabbitnet`**: Rede para a comunicação exclusiva entre os microsserviços e o `RabbitMQ`.

---

## Configuração do API Gateway

O API Gateway (`gateway/`) utiliza o Spring Cloud Gateway para roteamento. As rotas são configuradas para direcionar requisições para os microsserviços corretos, incluindo regras de reescrita de URL para compatibilidade:

```properties
# Exemplo de configuração de rota para o UserMicroservice
spring.cloud.gateway.server.webflux.routes[0].id=user-service
spring.cloud.gateway.server.webflux.routes[0].uri=http://usermicroservice:8081
spring.cloud.gateway.server.webflux.routes[0].predicates[0]=Path=/users/**
spring.cloud.gateway.server.webflux.routes[0].filters[0]=RewritePath=/users/(?<segment>.*), /users/${segment}

# Configurações semelhantes para SalaMicroservice e ReservaMicroservice
# ...
