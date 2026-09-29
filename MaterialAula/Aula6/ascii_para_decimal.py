#!/usr/bin/env python3
# Converte texto para valores decimais ASCII
# Uso: python ascii_para_decimal.py "2'-)#rsq"

import sys

if len(sys.argv) < 2:
    print("Uso: python ascii_para_decimal.py \"texto\"")
    print("Exemplo: python ascii_para_decimal.py \"2'-)#rsq\"")
    sys.exit(1)

texto = sys.argv[1]

for caractere in texto:
    print(ord(caractere))