## Idéias de Atividade

###  1. “Caça às Violações SOLID” — Análise em Duplas (20-30 min)
**Objetivo:** Identificar violações de SOLID em código real.
**Material:** Trecho de código legado 
**Instruções:**
- Alunos em duplas analisam o código.
- Devem marcar com cores ou comentários:  
  - SRP violado  
  - OCP violado  
  - LSP violado  
  - ISP violado  
  - DIP violado  
- Apresentam 1 violação e como corrigiriam (2 min por dupla).

###  2. “Refatoração Relâmpago” — Competição de Melhorias (15 min)
**Objetivo:** Aplicar uma refatoração simples com impacto imediato.
**Material:** Mesmo código da prática 1, mas agora com foco em UMA melhoria.
**Instruções:**
- Cada grupo escolhe UMA violação (ex: SRP) e refatora APENAS isso.
- Tempo: 10 minutos.
- Critérios de vitória:  
  - Clareza  
  - Testabilidade  
  - Simplicidade  
- Votação rápida da turma ou professor escolhe o “melhor refactor”.

### 3. “SOLID no Mundo Real” — Pesquisa Relâmpago + Compartilhamento (15 min)
**Objetivo:** Conectar princípios a empresas reais.
**Instruções:**
- Cada aluno (ou trio) pesquisa em 5 min:  
  “Como [Netflix/Spotify/Uber] usa [Strategy/Factory/DIP]?”  
- Compartilham 1 descoberta em 1 frase no mural virtual (Miro, Jamboard) ou no quadro.
- Professor sintetiza os pontos em comum.

**Exemplo de frase:**  
“Netflix usa Strategy pra trocar algoritmos de recomendação sem deploy.”


### 4. “Teatro da God Class” — Roleplay Interativo (10-15 min)
**Objetivo:** Entender o caos de classes que fazem tudo.
**Instruções:**
- 5 alunos representam “métodos” de uma God Class:  
  -  “Salvar no banco”  
  -  “Enviar email”  
  -  “Calcular imposto”  
  -  “Gerar PDF”  
  -  “Validar dados”  
- Professor “pede mudança”: “Mudar regra de imposto!”  
- Todos os “métodos” precisam “se mexer” — causando confusão cênica.
- Depois, refatoram “em cena”: cada um vira uma classe separada.


### 5. “Troca de Padrões” — Jogo de Cartas de Padrões de Projeto (20 min)
**Objetivo:** Associar problemas a padrões corretos.
**Material:** Cartas com:  
- Problema (ex: “Preciso criar objetos sem saber a classe exata”)  
- Padrão (ex: “Factory Method”)  
- Anti-pattern (ex: “new direto no código”)
**Instruções:**
- Em grupos, recebem 3 problemas e 5 cartas de padrões.
- Devem associar o padrão correto a cada problema.
- Debate rápido: por que escolheram? O que descartaram?


### 6. “Teste Antes, Refatore Depois” — TDD Light (25 min)
**Objetivo:** Mostrar como testes permitem refatorar com segurança.
**Material:** Código pequeno sem testes + lista de comportamentos esperados.
**Instruções:**
- Primeiro: alunos escrevem testes unitários para o comportamento ATUAL (mesmo que feio).
- Depois: refatoram aplicando 1 princípio SOLID.
- Rode os testes — se passarem, a refatoração foi segura!
- Reflexão: “Sem testes, você teria coragem de mexer?”


### 7. “Mapa da Qualidade” — Diagrama Coletivo (15 min)
**Objetivo:** Visualizar quem é responsável por quê na qualidade.
**Instruções:**
- Desenhar círculos: “Código”, “Testes”, “Deploy”, “Requisitos”, “Monitoria”.
- Alunos colam post-its com papéis:  
  “Dev”, “Tester”, “PO”, “Arquiteto”, “DevOps”, “Stakeholder”.
- Discutem: “Quem é DONO de cada parte? Quem é COLABORADOR?”
- Conclusão: “Qualidade é rede — não pirâmide.”


### 8. “Padrão no Cotidiano” — Analogias Criativas (10 min)
**Objetivo:** Entender padrões por analogia.
**Instruções:**
- Professor dá 1 padrão (ex: Strategy).
- Grupos de 3 têm 3 min para criar uma analogia do mundo real.  
  Ex: “Strategy é como escolher tipo de frete no e-commerce — o carrinho não sabe qual é, só aplica.”
- Melhor analogia (votação) ganha “selo de mestre da abstração”.
