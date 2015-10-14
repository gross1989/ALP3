package ALP3.A01;

import java.util.Random;
import java.util.Arrays;

public class A01 {
	
	Random rand = new Random();
	
	public void qsort (float[] arr, int indexA, int indexB) {
		if (indexB==indexA || indexB < 0 || indexA > arr.length-1 ) 
			return;
		// zufaelliges Pivot-Element
		int p = rand.nextInt(indexB-indexA+1) + indexA;
		float piv = arr[p];
		// jedes Element durchgehen
		int i;
		int s = p; // Anfang der Liste von Gleichen mit p
		int e = p; // Ende -"-
		for (i=indexA; i<indexB+1; i++) {
				// kleiner-gleich Pivot und danach
				if (arr[i] <= piv && i > e) {
					// i zum Anfang der Gleichen
					arr[s] = arr[i];
					// das erste groessere nach i
					arr[i] = arr[e+1];
					// Pivot nach rechts schieben
					arr[e+1] = piv;
					// Ende eins groesser
					e++;
					// Anfang um eins groesser wenn echt 
					if (arr[s] < piv)
						s++;
				// groesser-gleich Pivot und davor
				} else if (arr[i] >= piv && i < s) {
					// i zum Ende der Gleichen
					arr[e] = arr[i];
					// das erste kleinere nach i
					arr[i] = arr[s-1];
					// Pivot nach links schieben
					arr[s-1] = piv;
					// Anfang eins kleiner
					s--;
					// Ende eins kleiner wenn echt 
					if (arr[e] > piv)
						e--;					
				}
		}	
		System.out.println(piv + " " + indexA + " " + indexB);
		System.out.println(s + " " + e);
		System.out.println(Arrays.toString(arr));
		
		qsort(arr, 0, s-1);
		qsort(arr, e+1, arr.length-1);
	}
	
}
