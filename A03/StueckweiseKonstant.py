

def berechne(F,x):
    if x<F[0] or x>=F[-1]: return None
    i=2
    while x>=F[i]: i+=2
    return F[i-1]

def verschiebe(F,b):
	ausgabe = ()
	i = 0
	while i < len(F):
		# Spungstellen um -b verschieben
		if i % 2 == 0: ausgabe += (F[i]-b,)
		# Werte beibehalten
		else: ausgabe += (F[i],)
		i+=1
	return ausgabe

def intervall(a, b, F):
	if a > b: return None
	if b <= F[0] or a >= F[-1]: return (F[0],)
	# sonst soviel wie moeglich kopieren
	ausgabe = ()
	i = 0
	first = True
	while i<len(F)-2:
		# Endstueck
		if b > F[i] and b < F[i+2]:
			if first:
				ausgabe += (F[i],F[i+1],b)
				first = False
			else: ausgabe += (F[i+1],b)
		# Anfangsstueck
		if first and a > F[i] and a < F[i+2]:
			if b < F[i+2]:
				ausgabe += (a,F[i+1],b)
			else: ausgabe += (a,F[i+1],F[i+2])
			first = False
		# volle Stuecke
		if F[i] >= a and F[i+2] <= b:
			if first: 
				ausgabe += (F[i],F[i+1],F[i+2])
				first = False
			else: ausgabe += (F[i+1],F[i+2])
		i+=2
	return ausgabe
		
def fallunterscheidung(b, F, G):
	# Vorbedingung: F[-1] > b or G[0] < b], sodass durchgaengiger Db 
	return intervall(F[0],b,F) + intervall(b,G[-1],G)[1:]

def add_generator(F,G):
    ff = fg = fh = None
    i = j = 0
    yield max(F[0],G[0])
    while True:
        # Invariante:
        # ff ist der Wert der Funktion F links von F[i]
        # fg ist der Wert der Funktion G links von G[j]
        # fh=ff+fg
        if F[i]<G[j]:
            if fh != None: yield fh; yield F[i]
            if i==len(F)-1: return
            ff=F[i+1]
            i+=2
        elif F[i]>G[j]:
            if fh != None: yield fh; yield G[j]
            if j==len(G)-1: return
            fg=G[j+1]
            j+=2
        else: # F[i]==G[j]:
            if fh != None: yield fh; yield G[j]
            if i==len(F)-1 or j==len(G)-1: return 
            ff=F[i+1]
            fg=G[j+1]
            i+=2
            j+=2
        if fg!=None and ff!=None: fh=ff+fg
	
def add(F,G):
	return tuple(add_generator(F,G))

def vereinfache(F):
	ausgabe = (F[0],)
	i = 1
	while i<=len(F)-2:
		# letzte Grenze mit gleichem Wert merken
		while i<len(F)-2 and F[i+2] == F[i]: i+=2
		ausgabe += (F[i],F[i+1])
		i+=2
	return ausgabe

def gleich(F,G):
	return vereinfache(F) == vereinfache(G)
	
                 
F1 = (0,2,3,1,5,5,10)
G1 = (1,-4,3,8,11)
H1 = add(F1,G1)
for i in range(-2,13,1):
    i += 0.5
    print i,berechne(F1,i),berechne(G1,i),berechne(H1,i)

print
print "2F ",add(F1,F1)
print " F ",F1
print " G ",G1
print "F+G",add(F1,G1)
print
print "verschiebe 3 F", verschiebe(F1,-2.1)
print "Intervall F", intervall(0,1.5,F1)
#~ for i in range(0,100,8):
	#~ print "fallunterscheidung F", fallunterscheidung(i/10.,F1,G1)
#~ H1 = (1,5,2,5,3,0,3.3,0,3.5,5,4,5,6)
#~ print len(H1)
#~ print H1
#~ print gleich(H1,vereinfache(H1))
