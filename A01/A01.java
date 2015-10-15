package ALP3.A01;

import java.util.Random;
import java.util.Arrays;

public class A01 {
	
	Random rand = new Random();
	
	public void qsort (float[] arr, int low, int high) {
		if (low >= high) 
			return;
		//~ System.out.println("s:" + s + "e:" + e);
		//~ System.out.println(Arrays.toString(arr));
		
		int p = partition(arr, low, high);
		qsort(arr, low, p);
		qsort(arr, p+1, high);
	}
	
	private int partition (float arr[], int low, int high) {
		// zufaelliges Pivot-Element
		int p = rand.nextInt(high - low +1) + low;
		float piv = arr[p];
		
		int i = low;
		int j = high;
		// Array gleichzeitig von links und rechts durchgehen und
		// kleineres als Pivot auf linker Seiter mit groesserem als Pivot
		// auf rechter Seite vertauschen, Ende wenn sich Indizes treffen
		while (i <= j) {
			while (arr[i] < piv) {
				i++;
			}
 
			while (arr[j] > piv) {
				j--;
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
}
