#!/usr/bin/env python3
"""
Gerador de Senhas Criptografadas com XOR
"""

def xor_encrypt(senha: str, chave: str) -> str:
    """Criptografa uma senha usando XOR com uma chave repetitiva."""
    resultado = []
    for i, char in enumerate(senha):
        key_char = chave[i % len(chave)]
        xor_val = ord(char) ^ ord(key_char)
        resultado.append(chr(xor_val))
    return ''.join(resultado)

def mostrar_tabela(senha: str, chave: str):
    """Mostra a tabela completa do cálculo XOR."""
    print("\n" + "="*60)
    print("TABELA DE CÁLCULO XOR")
    print("="*60)
    print(f"\n{'Pos':<4} {'Senha':<6} {'ASCII':<6} {'Chave':<6} {'ASCII':<6} {'XOR':<6} {'Cripto':<8}")
    print("-"*60)
    
    criptografado = []
    for i, char in enumerate(senha):
        key_char = chave[i % len(chave)]
        xor_val = ord(char) ^ ord(key_char)
        cripto_char = chr(xor_val)
        criptografado.append(cripto_char)
        
        print(f"{i:<4} {char:<6} {ord(char):<6} {key_char:<6} {ord(key_char):<6} {xor_val:<6} {repr(cripto_char):<8}")
    
    print("-"*60)
    return ''.join(criptografado)

def main():
    print("\n" + "="*50)
    print("  GERADOR DE SENHA XOR - AULA DE ARQUITETURA")
    print("="*50)
    
    print("\nDigite a senha que deseja criptografar:")
    senha = input("Senha (ex: senha123): ").strip()
    
    print("\nDigite a chave XOR:")
    chave = input("Chave (ex: ABC): ").strip()
    
    if not senha or not chave:
        print("\n Erro: Senha e chave não podem estar vazios!")
        return
    
    criptografado = mostrar_tabela(senha, chave)
    
    print("\n" + "="*50)
    print("RESUMO")
    print("="*50)
    print(f"Senha original:     {senha}")
    print(f"Chave XOR:          {chave}")
    print(f"Senha criptografada: {criptografado}")
    
    print("\n" + "="*50)
    print("DADOS PARA O EXERCÍCIO")
    print("="*50)
    print(f"""
            | Usuário | Senha "criptografada" |
            |---------|----------------------|
            | admin   | `{criptografado}` |

            **Informações:**
            - Chave tem {len(chave)} caracteres
            - Senha tem {len(senha)} caracteres
            - Chave são letras maiúsculas em sequência
            """)

if __name__ == "__main__":
    main()
