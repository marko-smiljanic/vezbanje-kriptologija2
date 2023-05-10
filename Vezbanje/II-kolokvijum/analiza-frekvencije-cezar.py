import matplotlib.pylab as plt


alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"


#racunanje frekvencije slova u sifratu i vracanje recnika sa freknvecijom sifrata kao rezultat recnik gde je kljuc upper case slovo a vrednost vrekvencija
def analiza_frekvencije(sifrat):
    sifrat = sifrat.upper()
    frekvencija_slova_u_sifratu = {}
    #prvo za svako slovo iz alfabeta postavimo da je iniijalna frekvencija 0 (kreiranje recnika)
    for s in alfabet:
        frekvencija_slova_u_sifratu[s] = 0

    for ss in sifrat:
        if ss in alfabet:
            frekvencija_slova_u_sifratu[ss] += 1
    return frekvencija_slova_u_sifratu


def iscrtaj_grafikon(frekvencija_u_sifratu):
    centar = range(len(alfabet))
    print(frekvencija_u_sifratu.keys())
    plt.bar(centar, frekvencija_u_sifratu.values(), align='center', tick_label=list(frekvencija_u_sifratu.keys()))
    plt.xlim([0, len(alfabet) - 1])
    plt.show()


def razbijanje_cezara(sifrat):
    rezultat_analize_frekvencije = analiza_frekvencije(sifrat)
    print(rezultat_analize_frekvencije)
    iscrtaj_grafikon(rezultat_analize_frekvencije)

    #cisto ako zakaze grafikon odstampam koje je najfrekventnije slovo
    #najveca_frekvencija = max(analiza.values())
    kljuc_od_najvece_frekvencije = max(rezultat_analize_frekvencije, key=rezultat_analize_frekvencije.get)
    print("Najveca frekvencija u sifratu je: ", rezultat_analize_frekvencije[kljuc_od_najvece_frekvencije], ". Slovo sa najvecom frekvencijom je: ", kljuc_od_najvece_frekvencije)


        
###############################################################################################
#preko cryptool-a dobijemo sifrat
#Za engleski jezik (najfrekventnije slovo u engleskom je E)
#nakon toga izracunamo najfrekventnije slovo i iz alfabeta prebrojimo od slova E do tog slova koliko ima koraka i dobili smo pomeraj

sifrat="BMJS YMJ IBFWKX HFRJ MTRJ YMFY JAJSNSL, YMJD KTZSI XSTB BMNYJ QDNSL TS YMJ LWTZSI. YMJD YMTZLMY XMJ BFX IJFI, GZY BMJS YMJD QNKYJI MJW ZU, YMJD XFB YMFY XMJ BFX TSQD FXQJJU. YMJD STYNHJI YMFY YMJ UTNXTSJI HTRG BFX XYNQQ NS MJW MFNW, XT YMJD HFWJKZQQD WJRTAJI NY. YMJS YMJD XFB YMJ YNLMY QFHNSL HTWXJY, FSI BMJS YMJD QTTXJSJI NY, XSTB BMNYJ GWJFYMJI IJJUQD FSI TUJSJI MJW JDJX. BMJS XMJ XFB YMJ IBFWKX, XMJ XRNQJI FSI XFNI, 'TM, UQJFXJ ITS'Y GJ FKWFNI TK RJ. N FR XT MFUUD YT GJ MJWJ BNYM DTZ.' YMJ IBFWKX BJWJ TAJWOTDJI YT XJJ XSTB BMNYJ FQNAJ FSI BJQQ"

razbijanje_cezara(sifrat)