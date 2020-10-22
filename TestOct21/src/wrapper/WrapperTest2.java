package wrapper;

public class WrapperTest2 {

	public static void main(String[] args) {


		double d = 55.5;
		System.out.println("Float.MAX_VALUE : "+Float.MAX_VALUE);
		System.out.println("Long.MAX_VALUE : "+Long.MAX_VALUE);
		
		System.out.println("Integer.MIN_VALUE : "+Integer.MIN_VALUE);
		
		
		
		//System.out.println(Float.toBinaryString(20));
		
		Float f = 44.534f;
		
		//Try to recreate the same features using Double,Float and Long
		
		Long l = Long.parseLong("3434456546545645");
		
		System.out.println(l);
		
		Long l2 = 232342432342343l;
		
		System.out.println(l2.compareTo(l));
		
		
		
	}

}
