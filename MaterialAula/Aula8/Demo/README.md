# Atividade 8: Testes Unitários e de Integração em Java

Este projeto foi criado com foco em:

- Testes unitários
- Testes de integração
- TDD / BDD
- Pirâmide de testes

## Como Usar

### 1. Baixe as dependências

Coloque na pasta `lib/`:

- [junit-platform-console-standalone-1.10.0.jar](https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/junit-platform-console-standalone-1.10.0.jar)
- [h2-2.2.224.jar](https://repo1.maven.org/maven2/com/h2database/h2/2.2.224/h2-2.2.224.jar)

### 2. Compile

```bash
javac -cp "lib/*" -d . src/*.java
```

### 3. Execute os testes

```bash
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path . --scan-class-path
```
## Estrutura

Calculadora.java + CalculadoraTest.java → Teste unitário puro
RepositorioUsuario.java + RepositorioUsuarioIT.java → Teste de integração com banco H2 em memória

## Dicas para a Aula
- Peça aos alunos para modificar os testes (ex: adicionar multiplicar na Calculadora).
- Discuta: por que o teste de integração é mais lento? O que acontece se o H2 não estiver no classpath?
    ```
    Exemplo:
    (Given) Dado que eu tenho uma calculadora
    (When) Quando eu multiplico 4 por 5
    (Then) Então o resultado deve ser 20
    ```
- Use linguagem BDD: “deveSomar...”, “deveLancar...” — ajuda a pensar no comportamento primeiro.
