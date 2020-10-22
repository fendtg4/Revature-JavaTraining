package eg2;

import java.util.Arrays;

public class BinarySearch {

	public static void main(String[] args) {
		
		int ar[] = {22, 55, 4, 8, 11, 6, 39};
		
		Arrays.sort(ar);
		System.out.println(Arrays.toString(ar));
		
		System.out.println("Arrays.binarySearch(ar, 100): " + Arrays.binarySearch(ar, 11));
		
		int ar2[] = Arrays.copyOf(ar, ar.length+5);
		
		System.out.println(Arrays.toString(ar2));
		
		Arrays.fill(ar2, 100);
		
		System.out.println(Arrays.toString(ar2));
		
		Arrays.fill(ar2, 3, 5, 1000);
		
		System.out.println(Arrays.toString(ar2));
		
	}
	
}
