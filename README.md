# API de Observações Marinhas

Esta é uma API RESTfull para gerenciar observações marinhas associadas a clientes.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven

## Funcionalidades

A API permite realizar as seguintes operações:

- Cadastrar um novo cliente
- Listar todos os clientes
- Buscar um cliente por ID
- Cadastrar uma nova observação associada a um cliente
- Listar todas as observações associadas a um cliente
- Calcular a qualidade da água com base nos parâmetros de salinidade, pH e temperatura

## Endpoints

### Clientes

- `GET /clientes`: Lista todos os clientes cadastrados.
- `GET /clientes/{id}`: Retorna os detalhes de um cliente específico.
- `POST /clientes`: Cadastra um novo cliente.
- `GET /clientes/{clienteId}/observacoes`: Lista todas as observações associadas a um cliente.

### Observações

- `POST /observacoes/{clienteId}`: Cadastra uma nova observação associada a um cliente.

## Instalação e Configuração

1. Clone este repositório: `git clone https://github.com/0Romero/seasavior.git`
2. Navegue até o diretório do projeto: `cd seasavior`
3. Execute a aplicação: `mvn spring-boot:run`

A API estará disponível em `http://localhost:8080`.

## Uso

Você pode utilizar qualquer cliente HTTP, como cURL, Postman ou Insomnia, para interagir com a API.

### Exemplo de Requisição

```http
POST /observations/{idCliente} 
Content-Type: application/json

{
    "location": "Oceano Atlântico",
    "ph": 7.5,
    "salinity": 35.0,
    "temperature": 25.0,
    "speciesPresent": ["Tubarão", "Arraia", "Peixe-palhaço"]
}


### Exemplo de Resposta

{
    "id": 1,
    "location": "Oceano Atlântico",
    "ph": 7.5,
    "salinity": 35.0,
    "temperature": 25.0,
    "speciesPresent": ["Tubarão", "Arraia", "Peixe-palhaço"],
    "waterQuality": "Aceitável"
}


