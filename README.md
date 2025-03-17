# Carbigdata - API

Este é um projeto de API desenvolvido com **Spring Boot** para gerenciar **Customers** (clientes) e **Occurrences** (ocorrências), incluindo funcionalidades de autenticação, criação, leitura, atualização e exclusão (CRUD). A aplicação foi projetada para ser escalável e pode ser facilmente configurada com Docker para execução local.

## Funcionalidades

- **Gerenciamento de Clientes**:
    - Criação, consulta e remoção de clientes.

- **Gerenciamento de Ocorrências**:
    - Criação, consulta, e remoção de ocorrências.

- **Autenticação e Autorização**:
    - Geração de tokens JWT para autenticação de usuários.
    - Expiração do token em 30 minutos.

- **Paginação**:
    - Implementação de paginação nas consultas de clientes e ocorrências.

- **Armazenamento de Arquivos**:
    - Integração com **Min.io** para armazenamento de arquivos.

- **Banco de Dados**:
    - Uso de **PostgreSQL** como banco de dados.

- **Docker Compose**:
    - Orquestração de todos os serviços utilizando Docker Compose (Min.io, PostgreSQL e a aplicação Spring Boot).

- **Migrations**:
    - Uso do **Flyway** para migração do banco de dados.

## Requisitos

Para rodar a aplicação, você precisa ter o **Docker** instalado em sua máquina. Além disso, a aplicação usa o **Spring Boot 3.x**, **JUnit 5**, **Mockito** para testes unitários e **JWT** para autenticação.

## Como Executar a Aplicação

1. Clone o repositório:

    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd carbigdata
    ```

2. Certifique-se de ter o **Docker** instalado.

3. Execute o comando para subir os containers utilizando o Docker Compose:

    ```bash
    docker-compose up --build
    ```

4. Acesse a API na URL `http://localhost:8080`.

5. A aplicação usa o banco de dados PostgreSQL na porta `5432` e o Min.io na porta `9000`.

## Estrutura de Endpoints

### 1. **Autenticação**

#### Login
- **Endpoint**: `/login`
- **Método**: `POST`
- **Requisição**:
    ```json
    {
      "username": "testuser",
      "password": "password123"
    }
    ```
- **Resposta**:
    ```json
    {
      "token": "your_jwt_token_here"
    }
    ```

### 2. **Clientes**

#### Criar Cliente
- **Endpoint**: `/api/customers`
- **Método**: `POST`
- **Requisição**:
    ```json
    {
      "name": "John Doe",
      "cpf": "12345678901"
    }
    ```

#### Obter Cliente
- **Endpoint**: `/api/customers/{id}`
- **Método**: `GET`

#### Obter Todos os Clientes com Paginação
- **Endpoint**: `/api/customers`
- **Método**: `GET`
- **Parâmetros**:
    - `page`: número da página.
    - `size`: número de itens por página.

#### Deletar Cliente
- **Endpoint**: `/api/customers/{id}`
- **Método**: `DELETE`

### 3. **Ocorrências**

#### Criar Ocorrência
- **Endpoint**: `/api/occurrences`
- **Método**: `POST`

#### Obter Ocorrência
- **Endpoint**: `/api/occurrences/{id}`
- **Método**: `GET`

#### Obter Ocorrências por Cliente
- **Endpoint**: `/api/occurrences/customer/{customerId}`
- **Método**: `GET`

#### Obter Ocorrências por Status
- **Endpoint**: `/api/occurrences/status/{status}`
- **Método**: `GET`

#### Deletar Ocorrência
- **Endpoint**: `/api/occurrences/{id}`
- **Método**: `DELETE`

### 4. **Fotos de Ocorrências**

#### Criar Foto de Ocorrência
- **Endpoint**: `/api/photo-occurrences`
- **Método**: `POST`

#### Obter Todas as Fotos
- **Endpoint**: `/api/photo-occurrences`
- **Método**: `GET`

#### Obter Fotos por Ocorrência
- **Endpoint**: `/api/photo-occurrences/occurrence/{cod_ocorrencia}`
- **Método**: `GET`

#### Deletar Foto de Ocorrência
- **Endpoint**: `/api/photo-occurrences/{id}`
- **Método**: `DELETE`

## Testes

Os testes unitários para a aplicação foram implementados utilizando **JUnit** e **Mockito**, com a seguinte estrutura `when-given-then`:

1. **Testes para o Serviço de Ocorrências**:
    - Verifica a criação, obtenção e exclusão de ocorrências.

2. **Testes para o Serviço de Clientes**:
    - Verifica a criação, obtenção e exclusão de clientes.

3. **Testes para o Serviço de Fotos de Ocorrências**:
    - Verifica a criação, obtenção e exclusão de fotos associadas às ocorrências.

4. **Testes de Autenticação**:
    - Verifica a geração do token JWT com credenciais válidas.

## Considerações Finais

Essa API foi construída com o objetivo de ser simples de configurar e escalar, utilizando ferramentas modernas de microserviços, como o **Docker**, **PostgreSQL**, **Min.io** e **Flyway** para migrações de banco de dados. A solução é fácil de integrar com outros sistemas, oferecendo funcionalidades de autenticação, CRUD e armazenamento de arquivos.


