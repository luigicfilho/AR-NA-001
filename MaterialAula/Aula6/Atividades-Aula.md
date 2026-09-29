# 1. Análise de Código Inseguro

## Objetivo: 

Identificar falhas de segurança em um trecho de código Java simples.

## Descrição:

Trecho problemático:

```java
String query = "SELECT * FROM contas WHERE user='" + usuario + "' AND senha='" + senha + "'";
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(query);
```

## Perguntas:

- Quais problemas você identifica?
- Como corrigir?

# 2. OWASP Top 10 Mapping

## Objetivo: 
Associar vulnerabilidades reais aos itens do OWASP Top 10.

## Descrição:

Lista de cenários:

- Entrada de usuário não sanitizada → ?
- Falta de autenticação multifator → ?
- Senhas salvas sem criptografia → ?
- Permissões excessivas no banco → ?

Os alunos devem acessar [OWASP Top 10](https://owasp.org/www-project-top-ten/) e relacionar cada cenário ao item correspondente no OWASP Top 10 e propor uma mitigação simples.

# 3. Modelo Zero Trust

## Objetivo: 
Criar um mini diagrama de arquitetura com o conceito de Zero Trust.

## Descrição:

Divida os alunos em grupos.

Cada grupo desenha uma arquitetura simples de aplicação Java e inclui pontos de autenticação, autorização e verificação de identidade com Zero Trust.

Elementos que deve ser incluido no diagrama de arquitetura:

- Autenticação via token
    - JWT ou OAuth2
    - Cada requisição precisa apresentar credenciais válidas
- Autorização baseada em roles
    - Verificar se o usuário tem permissão para acessar recursos específicos
    - Ex.: roles como ADMIN, USER, GUEST
- Verificação contínua em cada requisição
    - Zero Trust: não confiar na rede interna
    - Sempre validar identidade e permissão, mesmo dentro da infraestrutura

