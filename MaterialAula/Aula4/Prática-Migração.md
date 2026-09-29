# Planejando a Migração de uma Aplicação Monolítica para Microsserviços

## Cenário:

Você é o arquiteto de software da empresa E-ShopBR, que opera uma plataforma de e-commerce com um sistema monolítico desenvolvido em Java (Spring Boot). A aplicação atual tem as seguintes funcionalidades integradas:

- Gerenciamento de usuários (cadastro, login, perfis)
- Catálogo de produtos (listagem, busca, detalhes)
- Carrinho de compras
- Processamento de pedidos
- Pagamentos (integração com gateways externos)
- Notificações (e-mail e SMS)

A empresa está enfrentando problemas:

- Lançamentos lentos (toda alteração exige reteste completo)
- Dificuldade em escalar componentes críticos (ex: pagamento sofre picos de carga)
- Equipes grandes trabalhando no mesmo código, causando conflitos
- Alta taxa de falhas em produção devido à acoplamento rígido

A diretoria decidiu migrar para uma arquitetura de microsserviços. Sua missão: propor uma estratégia de migração passo a passo.

## Instruções:

1. **Identifique os 4 principais microsserviços** que devem ser criados, justificando por que cada um deve ser separado.
2. **Escolha uma estratégia de migração** entre as abaixo e explique sua escolha:
    - Strangler Pattern
    - Feature Toggle + Gradual Refactoring
    - Big Bang Migration 
    - Parallel Run + Shadow Traffic
3. **Descreva o primeiro passo prático** da migração — qual serviço você migraria primeiro? Por quê? Quais métricas você monitoraria para validar o sucesso?
4. **Proposta de integração**: Como os novos microsserviços se comunicariam com o monólito remanescente? Sugira um protocolo e mecanismo de autenticação/autorização.
5. **Riscos e mitigação**: Liste 2 riscos principais da migração e como você os mitigaria.

(Bônus): Como você garantiria que o time de desenvolvimento não fique "preso" no monólito durante a transição? Que práticas de DevOps e cultura você implementaria?

## Formato de entrega:

- Relatório individual ou em duplas (máximo 1 página).
- Use tópicos claros, linguagem técnica adequada, mas acessível.
- Pode incluir diagrama simples (se desejar) mostrando a arquitetura proposta após a primeira fase.