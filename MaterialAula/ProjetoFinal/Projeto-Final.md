# Projeto Final: Arquitetura para a "FastFintech" - Lançamento de um Novo Cartão de Crédito

## Tema
Projetar a arquitetura de software para o sistema backend que dará suporte ao lançamento e operação do novo cartão de crédito "FastCard".

## Objetivo
Os alunos devem criar uma proposta arquitetural completa, justificando cada decisão com base nos princípios e padrões estudados no curso. O foco está na clareza, escalabilidade, segurança e resiliência do sistema, não na implementação funcional.

## Escopo do Projeto 
O sistema da FastFintech precisa gerenciar:

- Onboarding de Clientes: Cadastro, análise de crédito e aprovação.
- Gestão da Conta e do Cartão: Emissão do cartão físico, gestão de limite, bloqueio, etc.
- Processamento de Transações: Autorização, captura e liquidação de compras.
- Fatura e Pagamentos: Geração de fatura, processamento de pagamentos e juros.
- Autenticação e Segurança: Acesso seguro dos clientes (via app/website) e das APIs internas.

## Diretrizes e Requisitos Não-Funcionais

### Requisitos Não-Funcionais
- **Segurança:** Conformidade PCI-DSS Nível 1
- **Latência:** < 300ms para autorizações
- **Volume:** 5.000 transações/segundo
- **Disponibilidade:** 99.99% (SLA)
- **Resiliência:** Tolerância a falhas em clearing bancário

### Diretrizes
- **Arquitetura**: Definir como será a arquitetura do sistema. (Monolito, SOA, Microserviços)
- **Comunicação**: Definir como os serviços se comunicarão (síncrona REST/GraphQL ou assíncrona com mensageria).
- **Padrões de Resiliência**: O sistema deve ser tolerante a falhas. Onde se aplicam Circuit Breaker e Retry?
- **Consistência de Dados**: Operações que envolvam múltiplos serviços (ex: uma transação de compra) devem usar o padrão SAGA para garantir consistência eventual.
- **Segurança "Secure by Design"**: Aplicar princípios de Zero Trust e OWASP Top 10. Como a autenticação (OAuth 2.0 / OpenID Connect) será implementada para os clientes e para comunicação entre serviços?
- **Descoberta de Serviços**: Como os microsserviços se encontrarão dinamicamente? Usar Service Discovery.
- **Gateway de API**: Um API Gateway é obrigatório para ser o ponto único de entrada para clientes externos (app mobile, web).
- **Processamento de Dados**: Identificar onde o processamento é Batch (geração de faturas no fim do mês, cálculo de scores de crédito) e onde é Stream (autorização de transações em tempo real).

## Visão Geral do Sistema
**Produto:** Novo cartão de crédito (ou carteira digital) com:  
- Emissão instantânea de cartões virtuais  
- Processamento de transações em tempo real  
- Sistema antifraude integrado  
- Conformidade com PCI-DSS e LGPD  

## Entregáveis

### Diagrama de Arquitetura (C4 Model - Nível 2 e 3):

#### Diagrama de Contêineres (Nível 2)
Mostrar os principais serviços ou microsserviços, o banco de dados de cada um, e mais o que for aplicável

#### Diagrama de Componentes (Nível 3 - para um serviço chave)
Detalhar os componentes internos de um serviço, aplicando SOLID e Padrões de Projeto.

### Documento de Decisões Arquiteturais (ADRs):

Pequenos textos justificando as escolhas mais importantes.

#### ADR-001: Arquitetura Híbrida (Microsserviços + Core Monolítico)

Exemplo de ADR 1: "Título: Uso de Microsserviços sobre Monolito. Contexto: Precisamos de independência de deploy e escalabilidade seletiva. Decisão: Adotar arquitetura de microsserviços. Consequências: Maior complexidade operacional, mas benefícios em agilidade e resiliência."

#### ADR-002: Segurança por Design com Zero Trust

Exemplo de ADR 2: "Título: Segurança por Design com Zero Trust. Contexto: Dados financeiros exigem segurança máxima em arquitetura distribuída, onde redes internas não são confiáveis. Decisão: Implementar princípios Zero Trust com autenticação obrigatória para todos os serviços via mTLS e OAuth 2.0, centralizando gestão de acesso no API Gateway.
Consequências: Maior segurança e compliance porém complexidade adicional na gerência de certificados e autenticação.

#### ADR-003: Processamento de Transações com SAGA Distribuído

Exemplo de ADR 3: "Título: Padrão SAGA para Transações Distribuídas. Contexto: Uma compra envolve o serviço de transações (autorizar) e o serviço de faturas (reservar o valor). Decisão: Usar SAGA Orquestrada. Consequências: Garantimos consistência eventual sem travas distribuídas."

### Especificação de um Fluxo Crítico:

- Escolher um fluxo de negócio (ex: "Processar uma Compra" ou "Onboarding de um Novo Cliente").
- Criar um Diagrama de Sequência mostrando a interação entre todos os microsserviços, o API Gateway, e o message broker.
- Neste diagrama, destacar onde os padrões Circuit Breaker, Service Discovery e SAGA são acionados.

### Modelo de Dados

Para cada fluxo essencial da arquitetura proposta, defina:  

#### **Descrição do Modelo de Processamento**
- Explicar por que o modelo (Stream, Batch ou Micro-batch) foi escolhido
- Justificar com base nos requisitos de latência e volume

Exemplo:

| Serviço         | Modelo Processamento | Exemplo Uso                          |
|-----------------|----------------------|--------------------------------------|
| Transações      | Stream               | Autorização em tempo real (<300ms)   |
| Antifraude      | Stream               | Análise de risco em tempo real       |
| Faturamento     | Batch                | Geração de faturas diárias           |
| Relatórios      | Batch                | Análise de transações para auditoria |
| Notificações    | Micro-batch          | Envio agrupado de alertas            |


### Estratégia de Testes (Pirâmide de Testes):

- Definir qual seria a estratégia de testes para a arquitetura.

    - Exemplo:
        **Pirâmide de Testes:**
        - 70% Unitários (lógica de negócio)
        - 20% Integração (comunicação entre serviços)
        - 10% E2E (fluxos críticos: autorização → antifraude → liquidação)

    - Exemplo cenário TDD:
        1. Teste para autorização com sucesso
        2. Implementação mínima
        3. Refatoração com pattern Strategy

    - Exemplo de cenário BDD:

    ```
    Cenário: Solicitação de cartão aprovada
    (GIVEN) Dado que o cliente tem score 750
    (WHEN) Quando solicita cartão
    (THEN) Então deve receber status "APROVADO"
        E deve receber notificação
    ```

### (Opcional) Exemplos de endpoints

Caso deseje ilustrar a interface dos serviços, liste exemplos de endpoints críticos no formato abaixo:

**Exemplo de formato:**  
| Serviço       | Endpoint                     | Método | Propósito                          |
|---------------|------------------------------|--------|-----------------------------------|
| Pagamentos    | `/transactions`               | POST   | Autorização de transação          |
|               | `/transactions/{id}/status`  | GET    | Consulta status de transação      |
| Cartões       | `/cards`                     | POST   | Emissão de cartão virtual         |
|               | `/cards/{id}/block`          | PUT    | Bloqueio temporário               |