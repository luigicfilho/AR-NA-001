# CTF:  Explorando Falha de Validação de JWT

## OBJETIVO:
Acesse `/admin/flag` e roube a flag, mesmo sendo um usuário comum.

## PASSO A PASSO:

1. Compile e execute o servidor:
   > java -jar ctf.jar

2. Faça login para obter seu JWT:
   > curl -X POST http://localhost:8080/auth/login -d '{"username":"alice","password":"senha123"}'
   ou no PowerShell
   >
   > curl.exe -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{\"username\":\"alice\",\"password\":\"senha123\"}'

> Caso não tenha instale: winget install curl

3. Copie o token retornado.

4. Tente acessar o endpoint `/admin/flag` com esse token
    > curl.exe -H "Authorization: Bearer SEU_TOKEN_AQUI" http://localhost:8080/admin/flag
    > Deve obter a resposta:
    >> Você não é admin.


5. Capture a flag no endpoint `/admin/flag`.