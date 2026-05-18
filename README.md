<div align="center">

<h1>🚀 UniPDS Spring API</h1>

<p><strong>Uma API RESTful robusta e segura construída com Spring Boot 4 · Java 21 · JWT · WebClient · Flyway</strong></p>

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)](https://spring.io/projects/spring-security)
[![H2](https://img.shields.io/badge/H2_Database-004088?style=for-the-badge&logo=h2&logoColor=white)](https://www.h2database.com/)
[![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white)](https://flywaydb.org/)
[![Swagger](https://img.shields.io/badge/Swagger_UI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://swagger.io/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)

</div>

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Arquitetura](#-arquitetura)
- [Stack Tecnológica](#-stack-tecnológica)
- [Funcionalidades](#-funcionalidades)
- [Endpoints da API](#-endpoints-da-api)
- [Como Executar](#-como-executar)
- [Configuração](#-configuração)
- [Banco de Dados](#-banco-de-dados)
- [Segurança](#-segurança)
- [Documentação Interativa](#-documentação-interativa)
- [Estrutura do Projeto](#-estrutura-do-projeto)

---

## 💡 Sobre o Projeto

O **UniPDS Spring API** é uma aplicação backend desenvolvida como parte do estudo de **Projetos e Desenvolvimento de Sistemas (PDS)**, com foco em boas práticas de engenharia de software.

O sistema demonstra na prática conceitos como:

- ✅ Arquitetura em camadas (Controller → Service → Repository)
- ✅ Comunicação reativa inter-serviços com **WebClient**
- ✅ Autenticação stateless via **JWT (JJWT 0.13)**
- ✅ Migrações de banco de dados versionadas com **Flyway**
- ✅ Documentação automática de API com **SpringDoc OpenAPI (Swagger UI)**
- ✅ Segurança granular por rotas com **Spring Security 6**
- ✅ Separação clara entre DTOs, VOs e Entidades JPA

---

## 🏛️ Arquitetura

```
┌──────────────────────────────────────────────────────────┐
│                        HTTP Client                        │
└───────────────────────────┬──────────────────────────────┘
                            │
┌───────────────────────────▼──────────────────────────────┐
│              Spring Security (AuthFilter + JWT)           │
└───────────────────────────┬──────────────────────────────┘
                            │
┌───────────────────────────▼──────────────────────────────┐
│                     Controllers (REST)                    │
│  UserController │ ProductController │ TransferController  │
│  DocFiscalController │ DocAutorizacaoController           │
└───────────────────────────┬──────────────────────────────┘
                            │
┌───────────────────────────▼──────────────────────────────┐
│                     Services (Business Logic)             │
│  UserServiceImpl │ ProductService │ TransferService        │
│  DocFiscalServiceImpl ──► WebClient ──► lazy-api          │
└───────────────────────────┬──────────────────────────────┘
                            │
┌───────────────────────────▼──────────────────────────────┐
│              Repositories (Spring Data JPA)               │
│  ProductRepository │ DocFiscalRepo │ (+ outros)           │
└───────────────────────────┬──────────────────────────────┘
                            │
┌───────────────────────────▼──────────────────────────────┐
│           H2 In-Memory Database + Flyway Migrations       │
└──────────────────────────────────────────────────────────┘
```

---

## 🛠️ Stack Tecnológica

| Categoria | Tecnologia | Versão |
|-----------|-----------|--------|
| **Linguagem** | Java | 21 (LTS) |
| **Framework** | Spring Boot | 4.0.5 |
| **Web** | Spring MVC (servlet-based) | — |
| **Segurança** | Spring Security + JWT (JJWT) | 0.13.0 |
| **Persistência** | Spring Data JPA + Hibernate | — |
| **Banco de Dados** | H2 (in-memory) | — |
| **Migrações** | Flyway | — |
| **HTTP Client** | Spring WebClient (WebFlux) | — |
| **Documentação** | SpringDoc OpenAPI (Swagger UI) | 3.0.1 |
| **Build** | Apache Maven | — |
| **Dev Tools** | Spring Boot DevTools | — |

---

## ✨ Funcionalidades

### 👤 Gestão de Usuários
- Cadastro e autenticação de usuários
- Login via credenciais e via OAuth (Google)
- Autenticação baseada em tokens JWT stateless

### 📦 Catálogo de Produtos
- CRUD completo de produtos
- Listagem com suporte a ordenação dinâmica (`?order=asc|desc`)
- Validação de existência antes de operações de escrita

### 💸 Transferências Financeiras
- Endpoint para processamento de transferências entre contas
- Controle transacional com tratamento de exceções dedicado (`TransactionExceptionHandler`)

### 📄 Documentos Fiscais & Autorização
- Emissão de documentos de autorização via chave UUID única
- Consulta de documentos por protocolo
- Integração assíncrona com API externa via **WebClient** (padrão reativo `subscribe()`)
- Endpoint interno exposto via `/lazy-api/**` para chamadas entre microsserviços

### 🎫 Conferências & Inscrições
- Gerenciamento de conferências e sessões
- Controle de inscrições por usuário e por sessão (chave composta `SubscriptionID`)

---

## 🌐 Endpoints da API

### Autenticação
| Método | Rota | Acesso | Descrição |
|--------|------|--------|-----------|
| `POST` | `/user` | Público | Cadastrar novo usuário |
| `POST` | `/user/login` | Público | Autenticar (retorna JWT) |
| `POST` | `/user/oauth/google` | Público | Login via Google OAuth |

### Produtos
| Método | Rota | Acesso | Descrição |
|--------|------|--------|-----------|
| `GET` | `/api/products` | 🔒 Auth | Listar todos os produtos |
| `GET` | `/api/products/sort?order=` | 🔒 Auth | Listar produtos ordenados |
| `GET` | `/api/products/{id}` | 🔒 Auth | Buscar produto por ID |
| `POST` | `/api/products` | 🔒 Auth | Criar produto |
| `PUT` | `/api/products/{id}` | 🔒 Auth | Atualizar produto |
| `DELETE` | `/api/products/{id}` | 🔒 Auth | Remover produto |

### Transferências
| Método | Rota | Acesso | Descrição |
|--------|------|--------|-----------|
| `POST` | `/transfer/transfer` | 🔒 Auth | Realizar transferência financeira |

### Documentos Fiscais
| Método | Rota | Acesso | Descrição |
|--------|------|--------|-----------|
| `GET` | `/lazy-api/v1/autorizacao/{idCliente}?servico=` | Público (interno) | Gerar doc de autorização via GET |
| `POST` | `/lazy-api/v1/autorizacao/{idCliente}/solicitar` | Público (interno) | Gerar doc de autorização via POST |

---

## 🚀 Como Executar

### Pré-requisitos

- **JDK 21+** — [Download](https://adoptium.net/)
- **Maven 3.9+** (ou use o wrapper incluso `./mvnw`)
- Nenhum banco de dados externo necessário (**H2 in-memory**)

### Clonar e Executar

```bash
# 1. Clone o repositório
git clone <url-do-repositorio>
cd unipds-spring-v2

# 2. Compile e inicie a aplicação
./mvnw spring-boot:run

# Ou com Maven instalado globalmente
mvn spring-boot:run
```

A aplicação estará disponível em: **http://localhost:8080**

---

## ⚙️ Configuração

As configurações principais estão em `src/main/resources/application.properties`:

```properties
# Servidor
server.port=8080

# H2 Database (in-memory)
spring.datasource.url=jdbc:h2:mem:unipdsdb
spring.datasource.username=sa
spring.datasource.password=MyStr0ngPass
spring.h2.console.enabled=true

# JPA / Hibernate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Flyway (migrações automáticas)
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration

# URL base para chamadas WebClient inter-serviço
api.base-url=http://localhost:8080
```

---

## 🗄️ Banco de Dados

O projeto usa **H2 In-Memory** com migrações gerenciadas pelo **Flyway**.

### Console H2

Acesse durante a execução: **http://localhost:8080/h2-console**

| Campo | Valor |
|-------|-------|
| JDBC URL | `jdbc:h2:mem:unipdsdb` |
| User Name | `sa` |
| Password | `MyStr0ngPass` |

> ⚠️ **Atenção:** Certifique-se de usar exatamente `jdbc:h2:mem:unipdsdb` no campo JDBC URL (não o valor default `jdbc:h2:~/test`).

### Migrações Flyway

| Versão | Arquivo | Descrição |
|--------|---------|-----------|
| `V1` | `V1__create_table_product.sql` | Criação da tabela de produtos |
| `V2` | `V2__insert_tbl_account.sql` | Seed inicial de contas |

### Seed de Dados

```sql
INSERT INTO TBL_ACCOUNT VALUES(1, 100, 0);
INSERT INTO TBL_ACCOUNT VALUES(2, 100, 0);
```

---

## 🔐 Segurança

A segurança é implementada com **Spring Security 6** + **JWT (JJWT 0.13)**.

### Fluxo de Autenticação

```
1. Cliente envia POST /user/login  { username, password }
2. Servidor valida as credenciais
3. Servidor gera e retorna um JWT assinado
4. Cliente envia o token no header: Authorization: Bearer <token>
5. AuthFilter valida o token a cada requisição protegida
```

### Rotas Públicas (sem autenticação)

| Padrão | Método |
|--------|--------|
| `/login`, `/login/index.html` | `GET` |
| `/user` | `POST` |
| `/user/login` | `POST` |
| `/user/oauth/google` | `POST` |
| `/lazy-api/**` | `GET`, `POST` |
| `/swagger-ui/**`, `/v3/api-docs/**` | `GET` |
| `/css/**`, `/js/**`, `/images/**` | `GET` |

Todas as demais rotas exigem um **JWT válido**.

---

## 📖 Documentação Interativa

A documentação da API é gerada automaticamente pelo **SpringDoc OpenAPI** e pode ser acessada via Swagger UI:

**🔗 http://localhost:8080/swagger-ui/index.html**

A interface permite:
- Visualizar todos os endpoints disponíveis
- Inspecionar os schemas de request/response
- Executar chamadas diretamente pelo navegador

---

## 📁 Estrutura do Projeto

```
src/
└── main/
    ├── java/com/artantech/unipdsspring/
    │   ├── UnipdsspringApplication.java       # Entry point
    │   ├── config/
    │   │   └── WebClientConfiguration.java    # Bean WebClient (base URL via @Value)
    │   ├── controller/
    │   │   ├── DocAutorizacaoController.java  # Emissão de autorizações fiscais
    │   │   ├── DocFiscalController.java        # Documentos fiscais
    │   │   ├── ProductController.java          # CRUD de produtos
    │   │   ├── SubscriptionController.java     # Inscrições em conferências
    │   │   ├── TransferController.java         # Transferências financeiras
    │   │   ├── UserController.java             # Usuários e autenticação
    │   │   ├── ControllerExceptionHandler.java # Handler global de erros HTTP
    │   │   └── TransactionExceptionHandler.java# Handler de erros transacionais
    │   ├── events/                             # Application Events (Spring)
    │   ├── frontend/                           # Recursos do frontend embutido
    │   ├── model/
    │   │   ├── Account.java
    │   │   ├── Conference.java
    │   │   ├── DocFiscal.java
    │   │   ├── Product.java
    │   │   ├── Session.java
    │   │   ├── Subscription.java
    │   │   ├── Transaction.java
    │   │   ├── User.java
    │   │   ├── dto/                            # Data Transfer Objects
    │   │   └── vo/                             # Value Objects
    │   ├── repository/                         # Interfaces Spring Data JPA
    │   ├── security/
    │   │   ├── AuthFilter.java                 # Filtro JWT (OncePerRequestFilter)
    │   │   ├── TokenUtil.java                  # Geração e validação de tokens
    │   │   ├── WebSecurityConfig.java          # Configuração de rotas e permissões
    │   │   └── MyToken.java                    # Modelo do token
    │   └── service/
    │       ├── I*.java                         # Interfaces de serviço
    │       └── *Impl.java                      # Implementações (lógica de negócio)
    └── resources/
        ├── application.properties
        ├── db/migration/
        │   ├── V1__create_table_product.sql
        │   └── V2__insert_tbl_account.sql
        ├── static/                             # Assets estáticos
        └── templates/                          # Templates (Thymeleaf/HTML)
```

---

<div align="center">

Feito com ☕ e muito Spring Boot por **artantech**

</div>