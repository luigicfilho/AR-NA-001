#!/usr/bin/env python3
# Converte valores decimais ASCII para texto
# Uso: python decimal_para_ascii.py 115 101 110 104 97 49 50 51

import sys

if len(sys.argv) < 2:
    print("Uso: python decimal_para_ascii.py 82 71 45 ...")
    print("Exemplo: python decimal_para_ascii.py 115 101 110 104 97 49 50 51")
    sys.exit(1)

resultado = ""
for numero in sys.argv[1:]:
    resultado += chr(int(numero))

print(resultado)