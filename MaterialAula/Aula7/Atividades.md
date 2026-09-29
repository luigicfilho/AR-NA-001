# Exercícios de Segurança – Autenticação e Autorização

# Exercício 1 – Análise de Fluxos de Autorização

## Objetivo
Identificar falhas de autorização em diferentes fluxos de sistema.

## Fluxo A

```mermaid
sequenceDiagram
    participant User
    participant Frontend
    participant Backend

    User->>Frontend: Login
    Frontend->>Backend: POST /login
    Backend-->>Frontend: Sessão válida

    User->>Frontend: Acessar dados de outro usuário
    Frontend->>Frontend: Verifica se é gestor

    Frontend->>Backend: GET /users/123/salary
    Backend->>Backend: Verifica apenas se autenticado
    Backend-->>Frontend: Retorna dados
```

## Fluxo B

```mermaid
sequenceDiagram
    participant User
    participant Frontend
    participant Backend
    participant Authz

    User->>Frontend: Login
    Frontend->>Backend: POST /login
    Backend-->>Frontend: Sessão válida

    User->>Frontend: Acessar dados de outro usuário
    Frontend->>Backend: GET /users/123/salary

    Backend->>Authz: Verifica permissão (usuário vs recurso)
    Authz-->>Backend: Permitido / Negado

    Backend-->>Frontend: Resposta (200 ou 403)
```

## Tarefas

1. Identifique onde ocorre autenticação em ambos os fluxos  
2. Identifique onde ocorre autorização em ambos os fluxos  
3. Explique por que o Fluxo A é inseguro  
4. Descreva um cenário de exploração possível  
5. Proponha correções arquiteturais  

# Exercício 2 – Análise de Arquitetura

## Objetivo
Identificar problemas de autenticação e autorização em arquitetura distribuída.

## Arquitetura Atual

```mermaid
flowchart LR
    FE[Frontend]
    GW[API Gateway]
    AUTH[Auth Service]
    PAY[Payroll Service]
    HR[HR Service]

    FE -->|Login| AUTH
    AUTH -->|Token| FE

    FE -->|Bearer Token| GW

    GW --> PAY
    GW --> HR

    PAY -->|usa userId do token sem validar| PAY
    HR -->|usa userId do token sem validar| HR
```

## Arquitetura Proposta (referência para análise)

```mermaid
flowchart LR
    FE[Frontend]
    AUTH[Auth Service]
    GW[API Gateway]
    PAY[Payroll Service]
    HR[HR Service]

    FE -->|Login| AUTH
    AUTH -->|Token| FE

    FE -->|Bearer Token| GW
    GW -->|Valida Token| AUTH

    GW --> PAY
    GW --> HR

    PAY -->|Autorização| PAY
    HR -->|Autorização| HR
```

## Tarefas

1. Identifique onde ocorre autenticação na arquitetura atual  
2. Identifique onde a autenticação deveria ocorrer  
3. Identifique falhas de autorização  
4. Explique o risco de confiar em `userId` vindo do cliente  
5. Liste possíveis ataques  
6. Proponha melhorias  

---

# Soluções

## Solução – Exercício 1

### Problemas identificados

- Autorização realizada no frontend  
- Backend não valida acesso ao recurso  
- Usuário autenticado pode acessar qualquer recurso  

### Fluxo corrigido

```mermaid
sequenceDiagram
    participant User
    participant Frontend
    participant Backend
    participant Authz

    User->>Frontend: Login
    Frontend->>Backend: POST /login
    Backend-->>Frontend: Sessão válida

    User->>Frontend: Acessar recurso
    Frontend->>Backend: GET /users/{id}/salary

    Backend->>Authz: Validar acesso (user vs recurso)
    Authz-->>Backend: Permitido / Negado

    Backend-->>Frontend: 200 ou 403
```

### Correções

- Mover autorização para o backend  
- Validar relação usuário → recurso  
- Não confiar em validação do frontend  

## Solução – Exercício 2

### Problemas identificados

- Gateway não valida autenticação  
- Serviços confiam em parâmetros do cliente  
- Ausência de controle de autorização  
- Falha de definição de trust boundary  

### Arquitetura corrigida

```mermaid
flowchart LR
    FE[Frontend]
    AUTH[Auth Service]
    GW[API Gateway]
    PAY[Payroll Service]
    HR[HR Service]

    FE -->|Login| AUTH
    AUTH -->|Token| FE

    FE -->|Bearer Token| GW
    GW -->|Valida Token| AUTH

    GW --> PAY
    GW --> HR

    PAY -->|Valida autorização| PAY
    HR -->|Valida autorização| HR
```

### Correções

- Centralizar autenticação no gateway  
- Validar token antes de acessar serviços  
- Implementar autorização nos serviços  
- Remover confiança em dados enviados pelo cliente  
