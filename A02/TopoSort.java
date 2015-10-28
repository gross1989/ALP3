import java.io.*;
import java.util.Arrays;

class TopoSort
{ 
    int n;
    Knoten [] Liste; // Liste aller Knoten 1,...,n
    Knoten [] freieKnoten; // Menge Q der Knoten ohne Vorgänger
    int AnzfreieKnoten;    // (als Stapel gespeichert)
	Knoten [] Pfad;
	int len_Pfad;
	
    static IntReader input;

public static void main (String[] args) throws IOException
    {
	input = new IntReader(args);
	TopoSort x = new TopoSort();
    }
    
private void Kreisfinden (Knoten u)
{
	if (u.visited == true && u.finished == false) {
		// Kreis gefunden, Knoten auf dem Pfad ab u bilden Kreis
		int i;
		for (i=0; Pfad[i] != u; i++) ;
		for (; i<len_Pfad; i++) System.out.print(Pfad[i].Name + " ");
		System.out.println(u.Name);
	}
	else if (u.visited == false) {
		u.visited = true;
		// Pfad um u erweitern
		Pfad[len_Pfad++] = u;
		// Tefensuche rekursiv fuer Nachfolgeknoten aufrufen
		Kante z = u.ersterNachfolger;
		while (z != null) {
			Kreisfinden(z.v);
			z = z.next;
		}
	    u.finished = true;
	    // u aus dem Pfad entfernen
	    len_Pfad--;
	}
}

TopoSort() throws IOException
{
    System.out.println("Topologischen Sortieren.");
    // Einlesen
    n = input.readInt();
        
    // Inititalisieren array:
    Liste = new Knoten [n+1];
    for (int i=1; i<=n; i++) Liste[i] = new Knoten(i);

    // Einlesen Kanten
    try	{ 
	for (;;) {
	    Knoten e = Liste[input.readInt()];
	    Knoten f = Liste[input.readInt()];
	    Kante x = new Kante(e,f);
	    x.next = e.ersterNachfolger;
	    e.ersterNachfolger = x;
	    f.anzVorgänger++;
    }   }
    catch(EOFException e) {}
	
    // Vorbereiten:
    freieKnoten = new Knoten [n];
    AnzfreieKnoten = 0;
    for (int i=1; i<=n; i++) {
	Knoten e = Liste[i];
	if (e.anzVorgänger==0)
	    freieKnoten[AnzfreieKnoten++]=e;
    }
    // Sortieren:
    System.out.println("");
 sort:
    for (int i=1; i<=n; i++) {
	if (AnzfreieKnoten == 0) {
	    System.out.println("Die Eingabe enthält einen Kreis."); 
	    Pfad = new Knoten[n];
	    len_Pfad = 0;
	    // Tiefensuche von einem der restlichen Knoten aus
	    for (int j=1; j<=n; j++)
			if (Liste[i].anzVorgänger > 0) Kreisfinden(Liste[i]);
	    break sort;
	}
	// Wähle einen Knoten ohne Vorgänger
	Knoten x = freieKnoten[--AnzfreieKnoten];
	System.out.println(x);
	// Entferne ausgehende Kanten:
	Kante z = x.ersterNachfolger;
	while (z != null) {
	    z.v.anzVorgänger--;
	    if (z.v.anzVorgänger == 0)
		freieKnoten[AnzfreieKnoten++]=z.v;
	    z = z.next;
	}
    }
}
}

class Knoten{
	int Name, anzVorgänger;
	Kante ersterNachfolger;
	boolean visited, finished;
	Knoten (int i) {
	    Name = i;
	    anzVorgänger = 0; 
	    ersterNachfolger = null;
	    visited = false;
	    finished = false;
	}
	public String toString() {return String.valueOf(Name); }
}

class Kante {
	Knoten u,v;
	Kante next;
	Kante(Knoten uu, Knoten vv) {u = uu; v = vv;}
}
