import ALP3.A01.*;
import java.util.Arrays;

public class A01_test {
	
	public static void main (String[] argv) {
		
		float[] a = {6.2f, 3.4f, 4.6f, 0.2f}; 
		
		A01 a01 = new A01();
		System.out.println(Arrays.toString(a));
		a01.qsort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
	}
	
}
