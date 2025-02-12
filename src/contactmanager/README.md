# ContactManager - Gerenciador de Contatos

## Descrição do Projeto

Este é o backend do Gerenciador de Contatos, desenvolvido utilizando Spring Boot e PostgreSQL. A API RESTful permite operações de criação, leitura, atualização e exclusão (CRUD) de contatos, integrando-se ao frontend para fornecer uma experiência completa ao usuário.

## Funcionalidades

- Cadastro de novos contatos.
- Atualização de contatos existentes.
- Exclusão de contatos.
- Listagem de contatos.
- Integração com banco de dados PostgreSQL.

## Tecnologias Utilizadas

- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Lombok](https://projectlombok.org/)

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- PostgreSQL instalado e em execução

## Configuração do Banco de Dados

Certifique-se de que o PostgreSQL esteja em execução e crie um banco de dados chamado `contactmanager`. Atualize as credenciais de acesso no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/contactmanager
spring.datasource.username=
spring.datasource.password=
```

## Como Executar o Projeto

1. **Instalação das dependências:**

   ```bash
   mvn clean install
   ```

2. **Execução da aplicação:**

   ```bash
   mvn spring-boot:run
   ```

A API estará disponível em [http://localhost:8080](http://localhost:8080).

## Endpoints da API

A seguir, são listados os endpoints disponíveis na API, juntamente com os dados que eles retornam:

### 1. Criar um novo contato

- **Endpoint:** `POST /contacts`
- **Descrição:** Cria um novo contato.
- **Corpo da Requisição (JSON):**
  ```json
  {
    "firstName": "João",
    "lastName": "Silva",
    "email": "joao.silva@example.com",
    "phoneNumbers": [
      {
        "number": "123456789"
      },
      {
        "number": "987654321"
      }
    ]
  }
  ```
- **Resposta de Sucesso (201 Created):**
  ```json
  {
    "id": 1,
    "firstName": "João",
    "lastName": "Silva",
    "email": "joao.silva@example.com",
    "phoneNumbers": [
      {
        "id": 1,
        "number": "123456789"
      },
      {
        "id": 2,
        "number": "987654321"
      }
    ]
  }
  ```

### 2. Listar todos os contatos

- **Endpoint:** `GET /contacts`
- **Descrição:** Retorna uma lista de todos os contatos.
- **Resposta de Sucesso (200 OK):**
  ```json
  [
    {
      "id": 1,
      "firstName": "João",
      "lastName": "Silva",
      "email": "joao.silva@example.com",
      "phoneNumbers": [
        {
          "id": 1,
          "number": "123456789"
        },
        {
          "id": 2,
          "number": "987654321"
        }
      ]
    },
    {
      "id": 2,
      "firstName": "Maria",
      "lastName": "Oliveira",
      "email": "maria.oliveira@example.com",
      "phoneNumbers": [
        {
          "id": 3,
          "number": "555123456"
        }
      ]
    }
  ]
  ```

### 3. Obter um contato por ID

- **Endpoint:** `GET /contacts/{id}`
- **Descrição:** Retorna os detalhes de um contato específico.
- **Parâmetros de Caminho:**
  - `id` (Long): ID do contato.
- **Resposta de Sucesso (200 OK):**
  ```json
  {
    "id": 1,
    "firstName": "João",
    "lastName": "Silva",
    "email": "joao.silva@example.com",
    "phoneNumbers": [
      {
        "id": 1,
        "number": "123456789"
      },
      {
        "id": 2,
        "number": "987654321"
      }
    ]
  }
  ```
- **Resposta de Erro (404 Not Found):**
  ```json
  {
    "error": "Contato não encontrado"
  }
  ```

### 4. Atualizar um contato

- **Endpoint:** `PUT /contacts/{id}`
- **Descrição:** Atualiza as informações de um contato existente.
- **Parâmetros de Caminho:**
  - `id` (Long): ID do contato.
- **Corpo da Requisição (JSON):**
  ```json
  {
    "firstName": "João",
    "lastName": "Santos",
    "email": "joao.santos@example.com",
    "phoneNumbers": [
      {
        "number": "123456789"
      },
      {
        "number": "111222333"
      }
    ]
  }
  ``` 