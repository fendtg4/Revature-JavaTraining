package scanner;

import java.util.Scanner;
import java.util.Arrays;

public class ArrayFromScanner {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter array size");
		int x = sc.nextInt();
		
		int[] ar = new int[x];
		
		for (int i=0;i<x;i++) {
			ar[i] = sc.nextInt();
		}
		
		sc.close();
		
		Arrays.sort(ar);
		
		System.out.println("Your array sorted: " + Arrays.toString(ar));
		
	}

}
