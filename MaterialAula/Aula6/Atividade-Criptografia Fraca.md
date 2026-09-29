# Exercício: Quebrando Criptografia XOR

## Cenário

Você invadiu um sistema e encontrou isso no banco:

| Usuário | Senha "criptografada" |
|---------|----------------------|
| admin | `2'-)#rsq` |

**O sistema usa XOR com uma chave curta (menos de 5 caracteres).**

**Missão:** Descobrir a senha original.

## Ferramentas Online

| Ferramenta | Link |
|------------|------|
| Calculadora XOR | https://xor.pw/ |
| Tabela ASCII | https://www.ascii-code.com/ |
| Conversor ASCII | https://www.rapidtables.com/convert/number/ascii-to-hex.html |

## Como XOR Funciona

```
Letra XOR Chave = Criptografado
Criptografado XOR Chave = Letra Original
```

**Truque:** Se você chutar a senha certa, descobre a chave!

## Passo 1: Converter Para Decimal

Acesse o conversor ASCII e digite `2'-)#rsq`:

| Caractere | 2 | ' | - | ) | # | r | s | q |
|-----------|---|---|---|---|---|---|---|---|
| Decimal | 50 | 39 | 45 | 41 | 35 | 114 | 115 | 113 |

## Passo 2: Testar Senhas Comuns

Chute uma senha e faça XOR com os valores criptografados.

**Senhas para testar:** `password`, `admin123`, `minha123`, `senha123`, `12345678`

### Exemplo com "senha123":

Primeiro, converta "senha123" para decimal: `115, 101, 110, 104, 97, 49, 50, 51`

Agora faça XOR posição por posição:

| Pos | Cripto | Senha | XOR | Resultado |
|-----|--------|-------|-----|-----------|
| 0 | 50 | 115 | 50 ⊕ 115 | ? |
| 1 | 39 | 101 | 39 ⊕ 101 | ? |
| 2 | 45 | 110 | 45 ⊕ 110 | ? |
| 3 | 41 | 104 | 41 ⊕ 104 | ? |
| 4 | 35 | 97 | 35 ⊕ 97 | ? |
| 5 | 114 | 49 | 114 ⊕ 49 | ? |
| 6 | 115 | 50 | 115 ⊕ 50 | ? |
| 7 | 113 | 51 | 113 ⊕ 51 | ? |

## Passo 3: Identificar o Padrão

Se a senha estiver correta, os resultados do XOR formarão uma **chave legível que se repete**.

Dica: A chave tem **3 caracteres** e são **letras maiúsculas em sequência**.

## Passo 4: Confirmar

A chave é: `___ ___ ___`

A senha é: `________`
