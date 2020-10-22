package wrapper;

public class WrapperTest {

	public static void main(String[] args) {
		
		Integer i1 = 5;
		Integer i2 = 5;
		Integer i3 = new Integer(5);
		if (i1==i3) {
			System.out.println("true");
		}
		else {
			System.out.println("false");
		}
		
		Integer i5 = 100;
		int x = 101;
		x = i5;
		
	}
	
	
}
