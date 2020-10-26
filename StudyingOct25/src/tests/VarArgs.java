package tests;

public class VarArgs {

	public static void printVarious(int ...x) {
		
		for (int i:x) {
			System.out.println(i);
		}
	}
	
	public static void main (String[] args) {
		
		printVarious(1,3,100,-333,444,555,2);
		
	}
}
