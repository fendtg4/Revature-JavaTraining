package scanner;

import java.util.Scanner;

public class ScannerVariousTypes {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Get a double");
		
		double d = Double.parseDouble(sc.nextLine());
		
		System.out.println("Get int");
		
		int i = Integer.parseInt(sc.nextLine());
		
		System.out.println(d +" " + i);
		
		sc.close();
		
	}
}
