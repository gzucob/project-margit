# 🗡️ project-margit

API REST para gerenciamento de produtos, com arquitetura orientada a domínio e foco em segurança e persistência.

O objetivo é servir como base para estudos avançados e implementação de funcionalidades comuns em aplicações modernas, incluindo segurança, persistência e versionamento de banco de dados.
> Projeto Spring Boot que consolida e evolui implementações anteriores em um único sistema mais coeso.


---

## Tecnologias utilizadas (até o momento)

* Java 25
* Spring Boot 4
* Spring Data JPA
* Spring Security
* Flyway (migração de banco de dados)
* Spring HATEOAS
* Bean Validation
* PostgreSQL
* JWT (Nimbus JOSE + JWT)
* Lombok

---

## Planejamento de funcionalidades

* API REST com Spring Web MVC
* Persistência com JPA
* Controle de migrações com Flyway
* Segurança com Spring Security e JWT
* Validação de dados
* Suporte a HATEOAS

---

## Estrutura do projeto

O projeto segue uma organização orientada a domínio (DDD-style),
estruturada como um monólito modular.

Cada contexto de negócio possui seu próprio pacote contendo:

- Entidades
- Serviços
- Repositórios
- DTOs
- Regras de negócio

Exemplo:

```text
src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── gzucob/
    │   │           └── projectmargit/
    │   │               ├── domain/
    │   │               │   └── product/
    │   │               │       ├── Product.java
    │   │               │       ├── ProductRequest.java
    │   │               │       ├── ProductService.java
    │   │               │       ├── ProductController.java
    │   │               │       └── ProductRepository.java
    │   │               ├── security/
    │   │               │   └── SecurityConfig.java
    │   │               └── ProjectMargitApplication.java
    │   └── resources/
    │       ├── db/
    │       │   └── migration/
    │       │       ├── V1__create_table_products.sql
    │       │       └── V2__add_column_quantity_in_table_products.sql
    │       ├── static
    │       ├── templates
    │       └── application.properties
    └── test/
```

Novos contextos (ex.: users) seguem o mesmo padrão,
mantendo baixo acoplamento e alta coesão.

---

## 🚀 Funcionalidades

### 🧾 Criar Produto

**Endpoint:** `POST /products`

Recebe um JSON representando a criação de um produto:

```json
{
    "name": "SAMSUNG GALAXY S23",
    "quantity": 10,
    "price": 123.45
}
```

### Regras de validação

- Campos obrigatórios: `name`, `quantity`, `price`
- `Quantity` e `price` não podem ser zero ou negativos
- `Produto` não pode ser duplicado

Retorno quando produto criado com sucesso:

```json
{
    "id": "60069518-9b5e-473b-8fbe-fadf2433be05",
    "name": "SAMSUNG GALAXY S23",
    "price": 123.45,
    "productAddAt": "2026-02-13T02:13:47.642028Z",
    "quantity": 10
}
```
---

## Status do projeto

Atualmente o projeto possui:

✔ Endpoint POST de criação de produtos
🚧 Demais operações (GET, PUT, DELETE) em desenvolvimento
🚧 Módulo de usuários ainda não implementado
🚧 Módulo de autenticação ainda não implementado

Este projeto substitui repositórios anteriores e continuará evoluindo como base principal para experimentação e aprimoramento técnico.
