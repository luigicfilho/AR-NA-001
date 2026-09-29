## Exercício 3: "O dilema do trade-off: Latência vs. Custo"

### Enunciado:
Uma startup de delivery está decidindo como processar os dados de localização dos entregadores para mostrar ao cliente onde está seu pedido. Eles têm três opções:

| Opção | Modelo | Atualização da localização | Custo estimado de infraestrutura | Complexidade técnica |
|-------|--------|---------------------------|----------------------------------|---------------------|
| X | Batch | A cada 30 minutos | Baixo | Baixa |
| Y | Stream | Em tempo real (< 5 segundos) | Alto | Alta |
| Z | Micro-batch | A cada 2 minutos | Médio | Média |

**Perguntas**:
1. Se o **diferencial competitivo** da startup é "transparência total em tempo real", qual opção você recomenda? Por quê?
2. Se a startup está **começando com orçamento limitado** e os clientes aceitam uma pequena defasagem, qual opção faz mais sentido?
3. *(Para os experientes)*: Que impacto cada escolha teria na arquitetura do sistema? Cite 1 vantagem e 1 risco de cada modelo.