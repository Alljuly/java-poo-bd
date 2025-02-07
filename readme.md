# Loja 

Este é um projeto Java para gerenciar uma loja, com funcionalidades para cadastro de clientes, funcionários, produtos e pedidos. A API foi construída com base no modelo de banco de dados relacional, utilizando as classes DAO para interação com o banco de dados.

## Estrutura do Banco de Dados

O banco de dados contém as seguintes tabelas:

- **CLIENTE**: Armazena as informações dos clientes (CPF, nome, endereço, telefone).
- **FUNCIONARIO**: Armazena as informações dos funcionários (CPF, nome, endereço, telefone).
- **PRODUTO**: Armazena as informações dos produtos (nome, valor unitário, quantidade).
- **PEDIDO**: Armazena os pedidos feitos pelos clientes, incluindo referências aos clientes e funcionários.
- **ITEM_PEDIDO**: Armazena os itens de cada pedido, relacionando os produtos com os pedidos.

## Estrutura do Projeto

O projeto foi estruturado da seguinte forma:

```git
├───.idea
├───src
    ├───main
    │   └───java
    │       └───br
    │           └───edu
    │               └───ifal
    │                   ├───dao
    │                   ├───db
    │                   ├───domain
    │                   └───sql
    └───test
        └───java
            └───br
                └───edu
                    └───ifal
                       └───test



```

## Funcionalidades

- **Cadastro de Clientes**: Permite registrar novos clientes na loja.
- **Cadastro de Funcionários**: Permite registrar novos funcionários responsáveis pelos pedidos.
- **Cadastro de Produtos**: Permite adicionar novos produtos ao estoque.
- **Gestão de Pedidos**: Permite criar novos pedidos para os clientes, associando produtos e funcionários responsáveis.
- **Consultas**: Permite consultar os clientes, funcionários, produtos e pedidos cadastrados.
- **Atualizações e Exclusões**: Permite atualizar ou excluir registros de clientes, funcionários, produtos e pedidos.

## Como Rodar

1. Clone o repositório:
   ```
    git clone https://github.com/Alljuly/java-poo-bd.git
   ```

3. Compile o projeto com Maven:
   ```
    mvn clean install
   ```
   
## Testes

Para executar os testes, você pode usar o comando:

```
mvn test
```
