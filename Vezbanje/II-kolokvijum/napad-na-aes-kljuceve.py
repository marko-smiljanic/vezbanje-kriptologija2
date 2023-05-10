from Crypto.Cipher import AES
from Crypto.Util import Counter
import os
from pwn import xor
import binascii


kljuc = os.urandom(16)

def encrypt(plaintext):
    cipher = AES.new(kljuc, AES.MODE_CTR, counter = Counter.new(128))
    ciphertext = cipher.encrypt(plaintext)
    return ciphertext.hex()

tekst = b"Neki tekst koji je potrebno sifrovati."
sifrovatni_tekst = encrypt(tekst)

tekst2 = b"Neki drugi tekst koji je potrebno sifrovati."
sifrovatni_tekst2 = encrypt(tekst2)

print(sifrovatni_tekst)
print(sifrovatni_tekst2)


# Pretvaranje iz heksadekadskog oblika u binarni oblik
sifrovatni_tekst_bin = binascii.unhexlify(sifrovatni_tekst)
sifrovatni_tekst2_bin = binascii.unhexlify(sifrovatni_tekst2)

# XOR izmedju dva kriptovana teksta kako bi se dobio XOR izmedju originalnih tekstova
# XOR sifrata
xor_tekst = bytes([a ^ b for a, b in zip(sifrovatni_tekst_bin, sifrovatni_tekst2_bin)])

# Poznat originalni tekst (treba znati barem jedan od originalnih tekstova)
# Zamisljeni scenario: napadac se dokopao jednog izvornog teksta
poznat_tekst = b"Neki tekst koji je potrebno sifrovati."

# Izvodjenje XOR operacije izmedju XOR-a originalnog teksta i XOR-a dekriptovanih tekstova kako bi se dobio XOR kljuca
xor_kljuc = bytes([a ^ b for a, b in zip(xor_tekst, poznat_tekst)])

# Dekriptovanje prvog kriptovanog teksta kako bi se dobio prvi originalni tekst
sifrat1 = AES.new(xor_kljuc, AES.MODE_CTR, counter=Counter.new(128))
plain_text1 = sifrat1.decrypt(sifrovatni_tekst_bin)

# Dekriptovanje drugog kriptovanog teksta kako bi se dobio drugi originalni tekst
sifrat2 = AES.new(xor_kljuc, AES.MODE_CTR, counter=Counter.new(128))
plain_text2 = sifrat2.decrypt(sifrovatni_tekst2_bin)

# Ispisivanje dobijenih originalnih tekstova
print("Originalni tekst 1: ", plain_text1)
print("Originalni tekst 2: ", plain_text2)