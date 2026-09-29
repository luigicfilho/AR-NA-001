#!/usr/bin/env python3
# Calcula XOR entre dois números
# Uso: python xor.py 82 115

import sys

if len(sys.argv) < 3:
    print("Uso: python xor.py valor1 valor2")
    print("Exemplo: python xor.py 82 115")
    sys.exit(1)

valor1 = int(sys.argv[1])
valor2 = int(sys.argv[2])

resultado = valor1 ^ valor2

print(resultado)