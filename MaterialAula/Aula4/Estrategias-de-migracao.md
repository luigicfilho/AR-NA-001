# Estratégias de Migração para Microsserviços

1. Strangler Pattern
   - Descrição: Substitui gradualmente partes de um sistema monolítico por novos microsserviços, mantendo o monólito funcionando durante toda a transição.
   - Como funciona: Um "estrangulador" (proxy ou gateway) intercepta requisições e redireciona funcionalidades específicas para os novos serviços, enquanto o resto continua no monólito.
   - Vantagens:
     * Baixo risco: sistema sempre está operacional.
     * Permite testar em produção com tráfego real.
     * Evolução incremental e controlada.
   - Ideal para: Sistemas críticos onde downtime não é aceitável (ex: e-commerce, bancos).

2. Feature Toggle + Gradual Refactoring
   - Descrição: Usa "bandeiras" (feature flags) para habilitar/desabilitar funcionalidades dentro do próprio monólito, permitindo refatorar trechos de código em pequenos passos.
   - Como funciona: Novas funcionalidades são implementadas dentro do monólito, mas só ativadas para alguns usuários ou ambientes via configuração.
   - Vantagens:
     * Não exige nova infraestrutura imediata.
     * Facilita A/B testing e rollback rápido.
   - Limitações:
     * Não resolve o acoplamento arquitetural — o monólito ainda existe.
     * Pode gerar código complexo e difícil de manter se as bandeiras acumularem.
   - Ideal para: Equipes que precisam evoluir lentamente sem separar serviços fisicamente.

3. Big Bang Migration
   - Descrição: Desliga completamente o sistema monolítico, reescreve tudo de uma vez e lança a nova arquitetura de microsserviços em uma única liberação.
   - Por que evitar:
     * Alto risco de falha catastrófica.
     * Tempo de inatividade prolongado — impacto direto em negócios.
     * Dificuldade de teste completo antes do lançamento.
     * Nenhuma margem para rollback.
   - Quando pode ser considerado: Apenas em sistemas obsoletos, sem valor comercial, ou em startups com zero clientes ativos.

4. Parallel Run + Shadow Traffic
   - Descrição: Executa o novo sistema de microsserviços em paralelo com o monólito, copiando (shadowing) todo o tráfego real para os novos serviços, mas ignorando suas respostas.
   - Como funciona: O monólito continua respondendo às requisições, enquanto os microsserviços recebem cópias das mesmas requisições para validação (comparação de resultados).
   - Vantagens:
     * Permite validar desempenho, precisão e comportamento em produção sem afetar usuários.
     * Excelente para detectar divergências de lógica de negócio.
   - Desvantagens:
     * Custo alto de infraestrutura (duplicação de serviços).
     * Complexidade de comparação de respostas e tratamento de estados assíncronos.
   - Ideal para: Sistemas onde a consistência de dados é crítica (ex: contabilidade, reservas) e há recursos para duplicar carga.

Observação final:
A escolha da estratégia depende do risco tolerado, do valor do negócio e da maturidade técnica da equipe. Strangler Pattern é geralmente a melhor opção para empresas estabelecidas.