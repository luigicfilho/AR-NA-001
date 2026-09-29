# Projeto Final: “Do Monolito aos Microsserviços”: Plano de Migração Arquitetural

---

## Objetivo do Projeto

Desenvolver um **plano estratégico de migração arquitetural** de um sistema monolítico para uma arquitetura baseada em microsserviços, aplicando conceitos de **Domain-Driven Design (DDD)**, padrões de arquitetura de microsserviços e análise de riscos. O foco é **tomar decisões arquiteturais fundamentadas**, não implementar código.

## Contexto e Justificativa

Muitas empresas começam com sistemas monolíticos por sua simplicidade, mas com o crescimento do negócio, enfrentam gargalos em:

- Escalabilidade
- Velocidade de entrega
- Manutenção de código
- Resiliência e disponibilidade

A migração para microsserviços é uma solução comum, mas **não é trivial**. Exige planejamento cuidadoso, identificação de domínios, escolha de padrões e gestão de riscos.

Este projeto simula o papel de um **arquiteto de software** responsável por guiar essa transição com segurança e eficácia.

## Sistema Base para Análise 

> **Sistema Escolhido: “BookStore Manager” — Sistema de Gestão de Livraria Online**

**Funcionalidades atuais (monolito):**
- Cadastro e gerenciamento de livros
- Cadastro de clientes
- Carrinho de compras
- Processamento de pedidos e pagamentos
- Geração de relatórios de vendas
- Autenticação de usuários (clientes e admin)

**Tecnologias atuais:**
- Backend: Java Spring Boot monolítico
- Frontend: React.js
- Banco de dados: PostgreSQL (tudo em um schema)
- Deploy: Servidor único na nuvem

## Etapas do Projeto

### 1. Análise do Sistema Monolítico Atual

- Mapeie os principais módulos e fluxos de negócio.
- Identifique pontos de dor: onde o sistema trava? Onde há acoplamento excessivo?
- Documente dependências entre componentes.
- Faça um **diagrama de blocos da arquitetura atual** (use C4 Model - nível de contêiner ou componentes).

> *Exemplo: “O módulo de pagamento depende diretamente da tabela de pedidos e clientes, o que impede deploy independente.”*

### 2. Identificação de Bounded Contexts com DDD

Aplique **Domain-Driven Design** para identificar limites de domínio. Pergunte-se:

- Quais são as áreas de negócio com linguagem ubíqua distinta?
- Quais entidades e regras pertencem a cada contexto?

Exemplo: 

**Bounded Contexts para o BookStore Manager:**

| Bounded Context      | Responsabilidades Principais                     | Entidades Principais            |
|----------------------|--------------------------------------------------|----------------------------------|
| Catálogo de Produtos | Gerenciar livros, autores, categorias, estoque   | Livro, Autor, Categoria, Estoque |
| Gestão de Clientes   | Cadastro, perfil, endereços, histórico           | Cliente, Endereço               |
| Pedidos              | Criar, rastrear, cancelar pedidos                | Pedido, ItemPedido              |
| Pagamentos           | Processar pagamentos, estornos, status           | Pagamento, Gateway, Transação   |
| Notificações         | Enviar e-mails/SMS de confirmação, lembretes     | Notificação, Template           |
| Relatórios           | Gerar KPIs, vendas por período, livros populares | Relatório, Métrica              |

> *Dica: Use diagramas de contexto (Context Map) para mostrar relações entre bounded contexts (ex: Parceria, Cliente-Fornecedor, ACL).*

### 3. Estratégia de Decomposição (Como quebrar o monolito?)

Escolha e justifique uma **estratégia de migração incremental**. Sugestão:

> **Padrão Strangler Fig**  
> - Novas funcionalidades são criadas como microsserviços.  
> - Funcionalidades antigas são reescritas gradualmente, “estrangulando” o monolito.  
> - API Gateway roteia requisições para o monolito ou para os novos serviços.

Exemplo: 

**Etapas propostas:**

1. **Etapa 0:** Criar API Gateway e Service Discovery.
2. **Etapa 1:** Migrar “Catálogo de Produtos” (menos acoplado, baixo risco).
3. **Etapa 2:** Migrar “Gestão de Clientes”.
4. **Etapa 3:** Migrar “Pedidos” e “Pagamentos” juntos (usando SAGA para transações).
5. **Etapa 4:** Migrar “Notificações” e “Relatórios”.
6. **Etapa 5:** Desligar o monolito.

> *Inclua um cronograma estimado (ex: 6 meses) e critérios de sucesso para cada etapa.*

### 4. Padrões Arquiteturais Necessários

Para cada desafio da nova arquitetura, proponha soluções com padrões

Exemplo:


| Desafio                     | Padrão Sugerido       | Justificativa                                                                 |
|----------------------------|------------------------|-------------------------------------------------------------------------------|
| Comunicação entre serviços | API Gateway            | Centraliza autenticação, roteamento, rate limiting e composição de respostas. |
| Localização de serviços    | Service Discovery      | Permite que serviços se encontrem dinamicamente em ambiente distribuído.      |
| Tolerância a falhas        | Circuit Breaker        | Evita cascata de falhas; fallback para respostas amigáveis.                   |
| Transações distribuídas    | Padrão SAGA            | Garante consistência eventual entre Pedidos e Pagamentos.                     |
| Comunicação assíncrona     | Eventos (Message Broker) | Para notificações e relatórios — desacoplamento e escalabilidade.             |

> *Inclua diagramas simples mostrando onde cada padrão se aplica.*

### 5. Riscos e Desafios da Migração

Identifique e proponha mitigação para os principais riscos

Exemplo:

| Risco                                      | Impacto                     | Estratégia de Mitigação                                     |
|-------------------------------------------|-----------------------------|-------------------------------------------------------------|
| Quebra de funcionalidades durante migração | Insatisfação do cliente     | Testes de contrato, feature flags, ambiente de staging      |
| Dificuldade de debugging distribuído       | Aumento do MTTR             | Centralização de logs (ELK), tracing distribuído (Jaeger)   |
| Duplicação de dados entre serviços         | Inconsistência              | Event sourcing, SAGA, CQRS com eventual consistency         |
| Aumento da complexidade operacional        | Necessidade de DevOps       | Automatizar deploy (CI/CD), monitoramento (Prometheus/Grafana) |
| Resistência da equipe                      | Atraso no projeto           | Treinamentos, pair arquitetural, MVP rápido para validação  |

## Entregáveis Esperados

1. **Documento de Plano de Migração (em Markdown)**
   - Introdução e contexto
   - Análise do monolito atual
   - Bounded Contexts identificados (com diagrama)
   - Estratégia de decomposição (com etapas e cronograma)
   - Padrões arquiteturais aplicados (com justificativas)
   - Análise de riscos e mitigação
   - Conclusão e lições aprendidas

2. **Diagramas Arquiteturais**
   - **Antes:** Arquitetura monolítica (C4 Model - nível de contêiner)
   - **Depois:** Arquitetura de microsserviços (C4 Model - nível de contêiner + padrões aplicados)
   - **Durante:** Fluxo de migração com Strangler Fig (opcional)

3. **Matriz de Trade-offs**

Crie uma tabela comparativa entre os trade-off realizados no sistema.

Exemplo:

   | Critério               | Monolito          | Microsserviços       | Justificativa da Escolha |
   |------------------------|-------------------|-----------------------|--------------------------|
   | Tempo de desenvolvimento inicial | 5 | 2                 | Monolito é mais rápido para MVP |
   | Escalabilidade         | 2               | 5             | Microsserviços escalam por serviço |
   | Complexidade operacional |5         | 2                 | Monolito é mais simples de operar |
   | Resiliência            | 2               | 5             | Falha em um serviço não derruba tudo |
   | Manutenção de código   | 1                | 5             | Código pequeno e focado por serviço |


