import ALP3.A01.*;
import java.util.Arrays;
import java.util.Random;

public class A01_test {
	
	final static int N = 20000000;
	static float[] arr = new float[N];
	
	public static void main (String[] argv) {
		
		A01 a01 = new A01();
		
		// Argument b einlesen
		int b = argv.length == 1 ? Integer.parseInt(argv[0]) : 5;
		System.out.println("b = " + b);
		System.out.println("N = " + N);
		
		// Zufallseingabe der Groesse N generieren
		Random rand = new Random();
		for (int i=0; i<N; i++)
			arr[i] = rand.nextFloat();
		System.out.println("array inited");

		//~ System.out.println(Arrays.toString(arr));
		
		long startTime = System.nanoTime();

		a01.qsort(arr, 0, arr.length-1, b);
		
		long estimatedTime = System.nanoTime() - startTime;

		System.out.println("Vergleiche: " + a01.anz_vergleiche);
		System.out.println("Zeit (s): " + estimatedTime * 1E-9f);
		
		System.out.println("sorted?: " + issorted(arr));
	}
	
	private static boolean issorted(float arr[]) {
		for (int i=0; i<arr.length-1; i++)
			if (arr[i] > arr[i+1]) return false;
		return true;
	}
}
