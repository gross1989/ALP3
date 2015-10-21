package ALP3.A01;

import java.util.Random;
import java.util.Arrays;

public class A01 {
	
	Random rand = new Random();
	public long anz_vergleiche = 0;
	
	public void qsort (float[] arr, int low, int high, int b) {
		// bei nur noch wenigen Elementen auf isort umschalten
		if (low + b >= high) {
			isort(arr, low, high);
			return;
		}
		
		int p = partition(arr, low, high);
		qsort(arr, low, p, b);
		qsort(arr, p+1, high, b);
	}
	
	private int partition (float arr[], int low, int high) {
		// zufaelliges Pivot-Element
		int p = rand.nextInt(high - low +1) + low;
		float piv = arr[p];
		
		int i = low;
		int j = high;
		// Array gleichzeitig von links und rechts durchgehen und
		// groesseres als Pivot auf linker Seiter mit kleinerem als Pivot
		// auf rechter Seite vertauschen, Ende wenn sich Indizes treffen
		while (i <= j) {
			while (arr[i] < piv) {
				i++;
				anz_vergleiche++;
			}
 
			while (arr[j] > piv) {
				j--;
				anz_vergleiche++;
			}
 
			if (i <= j) {
				float temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
		return j;
	}
	
	public void isort (float arr[], int low, int high) {
		int n = high - low + 1;
		int i, j;
		for (i=1; i<n; i++) {
			float v = arr[low+i];
			j = i-1;
			while (j>=0 && arr[low+j] >= v) {
				// Zahlen groesser-gleich verschieben
				arr[low+j+1] = arr[low+j];
				j--;
				anz_vergleiche++;
			}
			// hinter kleinerer Zahl einsortieren
			arr[low+j+1] = v;
		}
	}
}
