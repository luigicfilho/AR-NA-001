# Demonstração de Batch vs. Stream em Fraud Detection

## Objetivo: 
Mostrar como o processamento em stream pode detectar fraudes instantaneamente, enquanto o batch tem latência.

## Contexto:
Imagine um cenário de uma instituição financeira, que recebe inúmeras transações finaceiras, e que quer identificar uma transação fraudulenta.

No contexto simples, vamos simular a regra para transação fraudulenta como qualquer transação maior que 1000.

## Passos a seguir:

1. Crie um conjunto de transações, incluindo algumas fraudulentas (ex: transações muito grandes).

2. Demonstre um processamento em batch que identifica fraudes apenas ao final do dia.

3. Em seguida, use stream processing para detectar e alertar sobre fraudes em tempo real.

4. Destaque como a velocidade impacta a eficácia do sistema.

## Como rodar:

1. Compile tudo:

```cli
javac *.java
```

2. Rode o Batch:

```cli
java RunBatch
```

3. Rode o Stream:

```cli
java RunStream
```