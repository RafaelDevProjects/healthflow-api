# HealthFlow API

**HealthFlow API** é uma aplicação backend desenvolvida em **Spring Boot** que fornece uma API REST para gerenciamento de atividades de saúde e usuários. O projeto foi estruturado seguindo boas práticas de arquitetura em camadas, com separação clara entre controladores, serviços, repositórios e modelos de dados. Ele é ideal para aplicações mobile ou web que necessitam armazenar, consultar e gerenciar dados relacionados ao bem-estar e hábitos saudáveis.

---

## 🩺 Visão Geral

A API tem como principal objetivo permitir o registro e acompanhamento de atividades de saúde, como exercícios, monitoramento de hábitos e informações pessoais de usuários.  
Ela inclui endpoints para operações CRUD (criação, leitura, atualização e exclusão) e utiliza **DTOs (Data Transfer Objects)** para garantir segurança e clareza na troca de informações entre cliente e servidor.

---

## 🗂️ Estrutura do Projeto

O projeto segue a convenção padrão do Spring Boot, com os seguintes principais pacotes:

- **controller**: contém os controladores REST responsáveis por receber e responder às requisições HTTP.  
- **service**: implementa a lógica de negócios, intermediando a comunicação entre controladores e repositórios.  
- **repository**: define interfaces que estendem o `JpaRepository`, permitindo operações no banco de dados sem necessidade de SQL explícito.  
- **model**: define as entidades JPA que representam as tabelas do banco de dados.  
- **DTO**: classes de transferência de dados usadas para entrada e saída de informações na API.  
- **config**: contém as configurações da aplicação, incluindo o suporte ao Swagger/OpenAPI para documentação automática.

---

## ⚙️ Principais Funcionalidades

- Cadastro, listagem, atualização e exclusão de **usuários**  
- Registro e consulta de **atividades de saúde**  
- Associação entre atividades e usuários  
- Documentação automática da API com **Swagger (OpenAPI)**  
- Camadas de abstração bem definidas para facilitar manutenção e expansão  
- Utilização de **Spring Data JPA** para persistência de dados  
- Suporte a diferentes perfis de ambiente configuráveis no arquivo `application.properties`  

---

## 🧰 Tecnologias Utilizadas

- **Java 17 ou superior**  
- **Spring Boot**  
- **Spring Data JPA**  
- **Hibernate**  
- **Maven** (para gerenciamento de dependências)  
- **Swagger / OpenAPI** (para documentação da API)  
- **H2 / PostgreSQL / MySQL** (dependendo da configuração do ambiente)  

---

## 📁 Estrutura de Diretórios

- `src/main/java/com/healthflow/healthflow_api/`: código-fonte principal da aplicação  
- `src/main/resources/`: arquivos de configuração, como `application.properties`  
- `src/test/java/`: testes automatizados da aplicação  

---

## 📖 Documentação da API

A documentação interativa dos endpoints é gerada automaticamente com o Swagger.  
Ao iniciar o projeto, ela pode ser acessada via navegador no endereço padrão:

**http://localhost:8080/swagger-ui/index.html**

Nela, é possível visualizar todos os endpoints disponíveis, testar requisições e ver os modelos de entrada e saída.

---

## 🌐 Endpoints Principais

A aplicação fornece endpoints RESTful para os seguintes recursos:

- **Usuário**: gerenciamento de contas e informações pessoais  
- **Atividade de Saúde**: criação e monitoramento de registros de atividades associadas a usuários  

Cada endpoint segue convenções REST, utilizando métodos HTTP adequados (GET, POST, PUT, DELETE).

---

## 🧩 Configuração do Ambiente

O arquivo `application.properties` contém as configurações básicas do projeto, incluindo porta da aplicação, banco de dados e integração com o Swagger.  
O projeto pode ser executado localmente com o Maven, utilizando os comandos padrões de build e execução, e pode ser facilmente implantado em servidores ou containers.

---

# 🌐 Endpoints da HealthFlow API

A **HealthFlow API** disponibiliza endpoints REST para gerenciamento de **usuários** e **atividades de saúde**.  
Abaixo estão listados todos os endpoints disponíveis e como testá-los via **Swagger**, **Postman** ou **cURL**.

---

## 🧍‍♂️ Endpoints de Usuários (`/users`)

### **GET /users**
Retorna a lista de todos os usuários cadastrados.

**Como testar:**
- Método: GET  
- URL: http://localhost:8080/users  
- No Postman: selecione GET → insira a URL → clique em “Send”.

---

### **GET /users/{id}**
Busca um usuário específico pelo ID.

**Como testar:**
- Método: GET  
- URL: http://localhost:8080/users/1  
(Substitua `1` pelo ID desejado.)

---

### **POST /users**
Cria um novo usuário no sistema.

**Como testar:**
- Método: POST  
- URL: http://localhost:8080/users  
- Cabeçalho: Content-Type: application/json  
- Corpo da requisição (JSON):
```
}
"name": "João Silva",
"email": "joao@example.com"
}
```

---

### **PUT /users/{id}**
Atualiza as informações de um usuário existente.

**Como testar:**
- Método: PUT  
- URL: http://localhost:8080/users/1  
- Corpo (JSON):
```
}
"name": "João Silva",
"email": "joao@example.com"
}
```

---

### **DELETE /users/{id}**
Remove um usuário do sistema.

**Como testar:**
- Método: DELETE  
- URL: http://localhost:8080/users/1

---

## 🏃‍♀️ Endpoints de Atividades de Saúde (`/activities`)

### **GET /activities**
Lista todas as atividades registradas.

**Como testar:**
- Método: GET  
- URL: http://localhost:8080/activities

---

### **GET /activities/{id}**
Obtém uma atividade específica pelo ID.

**Como testar:**
- Método: GET  
- URL: http://localhost:8080/activities/5

---

### **POST /activities**
Cria uma nova atividade associada a um usuário.

**Como testar:**
- Método: POST  
- URL: http://localhost:8080/activities  
- Cabeçalho: Content-Type: application/json  
- Corpo (JSON):
```
{
"userId": 1,
"activityType": "Corrida",
"duration": 45,
"caloriesBurned": 300
}
```

---

### **PUT /activities/{id}**
Atualiza os dados de uma atividade existente.

**Como testar:**
- Método: PUT  
- URL: http://localhost:8080/activities/5  
- Corpo (JSON):
```
{
"activityType": "Caminhada",
"duration": 30,
"caloriesBurned": 150
}
```

---

### **DELETE /activities/{id}**
Remove uma atividade registrada.

**Como testar:**
- Método: DELETE  
- URL: http://localhost:8080/activities/5

---

## 📘 Testando com Swagger e documentação

A API possui configuração integrada de **Swagger/OpenAPI**.  
Após iniciar a aplicação, acesse pelo navegador:

Interface interativa (Swagger UI):
👉 http://localhost:8080/swagger-ui/index.html

Especificação OpenAPI (JSON):
👉 http://localhost:8080/v3/api-docs

Especificação OpenAPI (YAML):
👉 http://localhost:8080/v3/api-docs.yaml

Lá é possível visualizar e testar todos os endpoints interativamente.

---

## 🚀 Possíveis Extensões Futuras

- Autenticação e autorização com **Spring Security**  
- Integração com **serviços de monitoramento de saúde** externos (ex: Google Fit, Apple Health)  
- Suporte a **notificações** e **relatórios de desempenho**  
- Implementação de **validações avançadas** para os dados de entrada  

---

## 🔁 Códigos de Resposta

| Código | Significado |
|--------|--------------|
| 200 | Requisição bem-sucedida |
| 201 | Recurso criado com sucesso |
| 400 | Erro de validação ou corpo inválido |
| 404 | Recurso não encontrado |
| 500 | Erro interno no servidor |

