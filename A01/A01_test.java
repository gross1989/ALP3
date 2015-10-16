import ALP3.A01.*;
import java.util.Arrays;
import java.util.Random;

public class A01_test {
	
	final static int N = 20000000;
	static float[] arr = new float[N];
	
	public static void main (String[] argv) {
		
		Random rand = new Random();

		for (int i=0; i<N; i++)
			arr[i] = rand.nextFloat();
		
		A01 a01 = new A01();
		//~ System.out.println(Arrays.toString(arr));
		System.out.println("array init");

		a01.qsort(arr, 0, arr.length-1, 5);
		//~ a01.isort(arr, 0, arr.length-1);
		//~ System.out.println(Arrays.toString(arr));
		System.out.println("done");
		System.out.println("sorted?: " + issorted(arr));
		System.out.println("Vergleiche?: " + a01.anz_vergleiche);
		

	}
	
	private static boolean issorted(float arr[]) {
		for (int i=0; i<arr.length-1; i++)
			if (arr[i] > arr[i+1]) return false;
		return true;
	}
}
