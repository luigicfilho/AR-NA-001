# Script de demonstração: quebra XOR com known-plaintext attack
# Uso: python quebrar_xor.py

# Dados encontrados no banco de dados
senha_criptografada = "2'-)#rsq"

print("=" * 60)
print("EXERCÍCIO: QUEBRANDO CRIPTOGRAFIA XOR")
print("=" * 60)
print(f"\nSenha criptografada encontrada: {senha_criptografada}")

# Passo 1: Converter para decimal
print("\n[1] Convertendo para decimal ASCII:")
valores_cripto = [ord(c) for c in senha_criptografada]
print(f"Valores: {valores_cripto}")

# Passo 2: Chutar uma senha
chutes = ["password", "12345678", "admin123", "minha123", "senha123"]

print("\n[2] Testando chutes comuns:")

for chute in chutes:
    if len(chute) != len(senha_criptografada):
        continue
    
    print(f"\n  Tentando: '{chute}'")
    
    # Converter chute para decimal
    valores_chute = [ord(c) for c in chute]
    
    # Calcular XOR para descobrir a chave
    chave_descoberta = []
    for i in range(len(valores_cripto)):
        chave_char = valores_cripto[i] ^ valores_chute[i]
        chave_descoberta.append(chr(chave_char))
    
    chave_str = "".join(chave_descoberta)
    print(f"  Chave resultante: '{chave_str}'")
    
    # Verificar se a chave faz sentido (só letras/números, padrão repetitivo)
    if chave_str.isalnum() and len(set(chave_str)) <= 3:
        print(f"  POSSÍVEL CHAVE ENCONTRADA!")
        
        # Verificar se a chave se repete (padrão curto)
        for tamanho in range(1, 4):
            padrao = chave_str[:tamanho]
            if padrao * (len(chave_str) // tamanho) == chave_str[:tamanho * (len(chave_str) // tamanho)]:
                print(f"  Chave provável: '{padrao}' (se repete)")
                
                # Descriptografar com a chave descoberta
                senha_descoberta = ""
                for i in range(len(valores_cripto)):
                    char_chave = ord(padrao[i % len(padrao)])
                    char_original = valores_cripto[i] ^ char_chave
                    senha_descoberta += chr(char_original)
                
                print(f"  Senha original: '{senha_descoberta}'")
                print("\n" + "=" * 60)
                print("EXERCÍCIO CONCLUÍDO!")
                print("=" * 60)
                break

#print("\n[3] Nenhum chute funcionou? Tente outros valores!")
#print("Dica: a chave é curta (3 caracteres) e usa letras maiúsculas.")