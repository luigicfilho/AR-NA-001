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
| Calculadora XOR | https://www.rapidtables.com/calc/math/xor-calculator.html |
| Tabela ASCII | https://www.ascii-code.com/ |
| Conversor ASCII | https://www.rapidtables.com/convert/number/ascii-to-decimal.html |

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

## Resposta (Para o Instrutor)

| Senha Original | Chave XOR | Resultado Criptografado |
|----------------|-----------|-------------------------|
| senha123 | ABC | 2'-)#rsq |

**Cálculo completo:**

| Senha | ASCII | Chave | ASCII | XOR | Cripto |
|-------|-------|-------|-------|-----|--------|
| s | 115 | A | 65 | 82 | R |
| e | 101 | B | 66 | 71 | G |
| n | 110 | C | 67 | 45 | - |
| h | 104 | A | 65 | 41 | ) |
| a | 97 | B | 66 | 99 | c |
| 1 | 49 | C | 67 | 114 | r |
| 2 | 50 | A | 65 | 115 | s |
| 3 | 51 | B | 66 | 113 | q |

## Lição Principal

> **Criptografia caseira é perigosa.** XOR simples com chave curta é trivial de quebrar.

**Use algoritmos testados:** AES, bcrypt, Argon2, PBKDF2

## Dicas Progressivas (Se Travar)

| Nível | Dica |
|-------|------|
| 1 | A senha tem 8 caracteres |
| 2 | A senha são letras + números |
| 3 | A chave tem 3 caracteres |
| 4 | A chave são letras maiúsculas em sequência alfabética |
| 5 | A senha começa com 's' |

## Comandos

### simples
1) XOR de números (inteiros)

```bat
set /a resultado=5 ^^ 3
echo %resultado%
```
Saída:
6


2) Decimal → Texto (ASCII)

```bat
powershell -command "[char]65"
```
Saída:
A

3) Texto → Decimal (ASCII)
```powershell
powershell -command "[int][char]'A'"
```
Saída:
65

### completos

```powershell
# Texto para decimal
[encoding]::ASCII.GetBytes("2'-)#rsq") -join ","

# Decimal para texto
[char[]](82,71,45,41,99,114,115,113) -join ""

# XOR entre dois números
50 -bxor 115

# Descobrir chave (cripto XOR senha)
 $cripto="2'-)#rsq"; $senha="senha123"; for($i=0;$i-lt$cripto.Length;$i++){[char]([int]$cripto[$i]-bxor[int]$senha[$i])}
```

```bash
# Texto para decimal
echo -n "2'-)#rsq" | od -An -td1 | tr -s ' ' '\n' | grep -v '^$'

# Decimal para texto
printf '\x52\x47\x2d\x29\x63\x72\x73\x71'

# OU usando awk:
awk 'BEGIN{printf "%c%c%c%c%c%c%c%c",82,71,45,41,99,114,115,113}'

# XOR entre dois números
echo $((50 ^ 115))

# Descobrir chave (Python one-liner)
python3 -c "c=\"2'-)#rsq\";s=\"senha123\";print(''.join(chr(ord(c[i])^ord(s[i]))for i in range(min(len(c),len(s)))))"
```

```py
# Texto para decimal
python3 -c "print([ord(c)for c in'2\'-)#rsq'])"

# Decimal para texto
python3 -c "print(''.join(chr(n)for n in[82,71,45,41,99,114,115,113]))"

# XOR entre dois números
python3 -c "print(50^115)"

# Descobrir chave
python3 -c "c=\"2'-)#rsq\";s=\"senha123\";print(''.join(chr(ord(c[i])^ord(s[i]))for i in range(min(len(c),len(s)))))"
```