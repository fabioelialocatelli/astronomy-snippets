bayerLetters = [ "alf", "bet", "gam", "del", "eps", "zet", "eta", "tet", "iot", "kap", "lam", "mu",
                "nu", "ksi", "omi", "pi", "rho", "sig", "tau", "ups", "phi", "chi", "psi", "ome" ]

bayerNumbers = [ "0", "1", "2", "3", "4", "5" ]

greekAlphabet = [ "α", "β", "γ", "δ", "ε", "ζ", "η", "θ", "ι", "κ", "λ", "μ",
                  "ν", "ξ", "ο", "π", "ρ", "σ", "τ", "υ", "φ", "χ", "ψ", "ω"]

bayerConstellations = ["and", "ant", "aps", "aqr", "aql", "ara", "ari", "aur",
                       "boo", "cae", "cnc", "cma", "cap", "car", "cas", "cen", "cep",
                       "cet", "cha", "cir", "col", "cra", "crb", "crv", "crt",
                       "cru", "cyg", "dor", "dra", "eri", "for", "gem",
                       "gru", "her", "hor", "hya", "hyi", "ind", "leo",
                       "lep", "lib", "lup", "lyr", "men", "mic", "mus",
                       "nor", "oct", "oph", "ori", "pav", "peg", "per",
                       "phe", "pic", "psc", "psa", "pup", "pyx", "ret",
                       "sgr", "sco", "scl", "sct", "ser", "sex", "tau",
                       "tel", "tra", "tuc", "uma", "umi", "vel", "vir", "vol" ]

for i in range(0, len(bayerLetters)):
    for j in range(0, len(bayerNumbers)):
        
        if(bayerNumbers[j] == "0"):
            print ("case \"" + bayerLetters[i] + "\":")
            print("\tdatabaseIdentifier = \"" + greekAlphabet[i] + "\";")
        else:
            print ("case \"" + bayerLetters[i] + bayerNumbers[j] + "\":")
            print("\tdatabaseIdentifier = \"" + greekAlphabet[i] + bayerNumbers[j] + "\";")
        print("\tbreak;")
