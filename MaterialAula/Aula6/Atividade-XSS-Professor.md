# Atividade Prática: XSS (Cross-Site Scripting)

## Objetivo do Exercício

Demonstrar de forma prática como funciona um ataque **XSS (Cross-Site Scripting)** e por que é uma vulnerabilidade perigosa em aplicações web. Ao final, você entenderá como um atacante pode injetar código malicioso em sites legítimos.

## Passo a Passo

### Passo 1: Abrir a Página

1. Abra a página: `https://lcdev.com.br/AR-NA-001/` no seu navegador
2. Você verá uma página que imita um portal bancário
3. Observe que ela parece legítima: tem saldo, menu, mensagens...

### Passo 2: Localizar o Campo de Mensagem

1. Role a página até encontrar "Central de Mensagens"
2. Veja que há um campo de texto para enviar mensagens
3. Abaixo do campo, há uma área vermelha com " payloads" de exemplo

### Passo 3: Testar um Payload XSS

1. Clique no botão **"Usar Payload 1"**
2. Veja que o campo foi preenchido com um código estranho
3. Clique em **"Enviar Mensagem"**
4. **Observe o que acontece!** Um alerta aparece na tela!

### Passo 4: Testar Outros Payloads

| Payload | O que faz |
|---------|-----------|
| **Payload 1** | Exibe um alerta simples |
| **Payload 2** | "Rouba" cookies do navegador |
| **Payload 3** | Executa código ao fazer o toggle do elemento HTML |

Teste cada um e observe os resultados!

## O que está acontecendo?

### Explicação

Quando você envia uma mensagem, a página salva o texto e depois **exibe de volta** na tela. O problema é que ela **não verifica** se o texto contém código malicioso.

```
[Usuário envia] → [Servidor/Site salva] → [Página exibe] → [Navegador EXECUTA!]
```

### O Código Vulnerável

```javascript
// CÓDIGO VULNERÁVEL - NÃO USE EM PRODUÇÃO!
messageDiv.innerHTML = `
    <div class="message-content">${content}</div>
`;
```

O `innerHTML` diz ao navegador: "interprete este texto como HTML". Se o texto contiver javascript, o navegador **executa** o script!

## Payloads Explicados

### Payload 1: Imagem com Erro
```html
<img src=x onerror=alert(1)>
```
**Como funciona:**
1. Tenta carregar uma imagem de uma fonte inválida ("x")
2. Quando falha, dispara o evento `onerror`
3. Executa o JavaScript no evento de erro

### Payload 2: Imagem com Erro, capturando informações.
```html
<img src=x onerror=alert(document.cookie)>
```
**Como funciona:**
1. Tenta carregar uma imagem de uma fonte inválida ("x")
2. Quando falha, dispara o evento `onerror`
3. Executa o JavaScript no evento de erro, que captura o cookie do navegador e o mostra num alerta.

**Por que é perigoso:** Em um ataque real, enviaria seus cookies para um servidor do atacante!

### Payload 3: Elemento Interativo
```html
<details closed ontoggle='alert("Cookies: " + document.cookie)'>
    <summary>Clique para ver seus dados</summary>
</details>
```
**Como funciona:**
1. Cria um elemento visual (div vermelha)
2. Quando o usuário passa o mouse, executa o código, que captura o cookie do navegador e o mostra num alerta.
3. Mostra que XSS pode ser ativado por interação do usuário

## Como se Proteger

### Método 1: Escapar Caracteres

Converter caracteres perigosos em texto seguro:

| Caractere | Perigoso | Seguro |
|-----------|----------|--------|
| `<` | Tag HTML | `&lt;` |
| `>` | Tag HTML | `&gt;` |
| `"` | Atributo | `&quot;` |
| `&` | Entidade | `&amp;` |

**Código Seguro:**
```javascript
function safeHTML(text) {
    const div = document.createElement('div');
    div.textContent = text;  // NÃO interpreta como HTML
    return div.innerHTML;
}

messageDiv.innerHTML = `<div class="message-content">${safeHTML(content)}</div>`;
```

Ou, mais simples ainda:
```javascript
messageDiv.textContent = content;  // Nunca executa HTML!
```

### Método 2: Content Security Policy (CSP)

Adicionar um header HTTP que bloqueia scripts não autorizados:
```
Content-Security-Policy: default-src 'self'; script-src 'self'
```

### Método 3: Usar Frameworks Modernos

React, Vue e Angular **escapam automaticamente** por padrão:
```jsx
<div>{userInput}</div>  // Escapa automaticamente

// React - PERIGOSO
<div dangerouslySetInnerHTML={{__html: userInput}} />
```

## Questões para Discussão

### Nível Básico
1. Por que o alerta aparece quando você envia a mensagem?
2. O que aconteceria se isso fosse um site bancário real?
3. Como você saberia se um site é vulnerável ou não?

### Nível Intermediário
4. Por que o navegador executa código de "fontes não confiáveis"?
5. Qual a diferença entre `innerHTML` e `textContent`?
6. Como um atacante poderia usar isso para roubar senhas?

### Nível Avançado
7. Quais outros vetores de XSS existem além de `<script>`?
8. Como o CSP (Content Security Policy) ajuda a mitigar XSS?
9. Por que `dangerouslySetInnerHTML` existe no React se é perigoso?

## Aviso Legal

> **Este exercício é exclusivamente educacional.**
>
> Tentar explorar vulnerabilidades XSS em sites de terceiros **sem autorização** é:
> - Ilegal em muitos países
> - Antiético
> - Passível de processos criminais
>
> Sempre pratique em ambientes controlados como este!

## Conteúdo Adicional

- [OWASP XSS Prevention Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.html)
- [MDN: Content Security Policy](https://developer.mozilla.org/en-US/docs/Web/HTTP/CSP)
- [OWASP Top 10](https://owasp.org/www-project-top-ten/)

## Modificações Extras

<img src=x onerror="document.querySelectorAll('.balance-value').forEach(e=>e.innerText='R$ 999.999,99')">