# 🛍️ Loja

Este é um projeto Java para gerenciar uma loja, com funcionalidades para cadastro de clientes, funcionários, produtos e pedidos. O projeto foi construído com base no modelo de banco de dados relacional, utilizando as classes DAO para interação com o banco de dados.

---

## 🗃️ Estrutura do Banco de Dados

O banco de dados contém as seguintes tabelas:

### **CLIENTE**
Armazena as informações dos clientes.
- **CPF** (Chave Primária)
- **Nome**
- **Endereço**
- **Telefone**

### **FUNCIONARIO**
Armazena as informações dos funcionários.
- **CPF** (Chave Primária)
- **Nome**
- **Endereço**
- **Telefone**

### **PRODUTO**
Armazena as informações dos produtos.
- **ID** (Chave Primária)
- **Nome**
- **Valor unitário**
- **Quantidade**

### **PEDIDO**
Armazena os pedidos feitos pelos clientes, incluindo referências aos clientes e funcionários.
- **ID** (Chave Primária)
- **CPF do cliente** (Chave Estrangeira)
- **CPF do vendedor** (Chave Estrangeira)
- **Valor total**

### **ITEM_PEDIDO**
Armazena os itens de cada pedido, relacionando os produtos com os pedidos.
- **ID do pedido** (Chave Estrangeira)
- **ID do produto** (Chave Estrangeira)
- **Quantidade**
- **Valor Total**

---

## 📂 Estrutura do Projeto

O projeto foi estruturado da seguinte forma:

```
D:.
├───.idea
├───src
│   ├───main
│   │   └───java
│   │       └───br
│   │           └───edu
│   │               └───ifal
│   │                   ├───dao
│   │                   ├───db
│   │                   ├───domain
│   │                   ├───service
│   │                   └───sql
│   └───test
│       └───java
│           └───br
│               └───edu
│                   └───ifal
│                       └───test
│                           ├───dao
│                           └───service
└───target
    ├───classes
    │   └───br
    │       └───edu
    │           └───ifal
    │               ├───dao
    │               ├───db
    │               ├───domain
    │               └───service
    └───test-classes
        └───br
            └───edu
                └───ifal
                    └───test
                        ├───dao
                        └───service
```

---

## 🛠️ Funcionalidades Implementadas em `Main.java`

1. **Cadastrar**
   - Produto.
   - Cliente.

2. **Buscar**
   - Produto (procurar produto pelo ID).

3. **Listar**
   - Todos os produtos disponíveis.
   - Vendas realizadas.

4. **Efetuar**
   - Venda (deve ser informada a lista de produtos e suas quantidades, o vendedor e o cliente que está realizando a compra).

---

## 🎯 Funcionalidades de Cada Entidade

- **Cadastro de Clientes**: Permite registrar novos clientes na loja.
- **Cadastro de Funcionários**: Permite registrar novos funcionários responsáveis pelos pedidos.
- **Cadastro de Produtos**: Permite adicionar novos produtos ao estoque.
- **Gestão de Pedidos**: Permite criar novos pedidos para os clientes, associando produtos e funcionários responsáveis.
- **Consultas**: Permite consultar os clientes, funcionários, produtos e pedidos cadastrados.
- **Atualizações e Exclusões**: Permite atualizar ou excluir registros de clientes, funcionários, produtos e pedidos.

---

## 🗑️ Ordem de Deleção de Registros

Para apagar um cliente ou funcionário do sistema, é necessário seguir uma ordem específica devido às chaves estrangeiras (FK) presentes nas tabelas. A ordem de deleção para evitar violação de integridade referencial é a seguinte:

1. **Apagar registros da tabela `ITEM_PEDIDO`**:
   - Esta tabela possui chaves estrangeiras (FK) para as tabelas `PEDIDO` e `PRODUTO`, sendo necessário removê-los primeiro.

2. **Apagar registros da tabela `PEDIDO`**:
   - Após excluir os itens de pedido, é necessário apagar os pedidos associados, já que a tabela `PEDIDO` possui chaves estrangeiras para `CLIENTE` e `FUNCIONARIO`.

3. **Apagar registros da tabela `FUNCIONARIO` e `CLIENTE`**:
   - Agora que os pedidos foram removidos, é seguro excluir os funcionários e clientes, pois não existem mais dependências diretas entre as tabelas.

4. **Apagar registros da tabela `PRODUTO`**:
   - Por último, os produtos podem ser removidos, uma vez que não há mais registros nas tabelas que referenciem esses produtos.

Seguindo essa ordem, você garante que a exclusão dos dados será realizada de forma segura e sem violação das regras de integridade do banco de dados.

---

## 🚀 Como Rodar

1. Clone o repositório:
   ```bash
   git clone https://github.com/Alljuly/java-poo-bd.git
   ```

2. Compile o projeto com Maven:
   ```bash
   mvn clean install
   ```

3. Execute o projeto:
   ```bash
   javac Main.java
   java Main
   ```

---

## 🧪 Testes

Para executar os testes, você pode usar o comando:

```bash
mvn test
```
---

Feito com ❤️ por [Alice](https://github.com/Alljuly) 🚀

---
