package strings;

import java.util.Arrays;

public class StringTest {

	public static void main(String[] args) {
		
		String s1= "hello";
		String s2 = "hello";
		String s3 = new String("hello");
		String s4 = new String("hello");
		
		
		System.out.println(s1.equals(s3));
		
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));
		System.out.println(System.identityHashCode(s3));
		
		char c[] = s1.toCharArray();
		
		System.out.println(s1);
		System.out.println(Arrays.toString(c));
		
		String s5 = "      sfsdf   f ";
		
		System.out.println(s5);
	}
}
