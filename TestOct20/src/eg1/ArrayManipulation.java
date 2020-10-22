package eg1;
import java.util.Arrays;

public class ArrayManipulation {

	public static void main(String[] args) {
		int ar[] = new int[10];
		
		ar[0] = 43;
		ar[1] = 23;
		ar[2] = 12;
		ar[3] = 55;
		ar[4] = 4;
		ar[5] = 112;
		ar[6] = 66;
		
		int n = 7;
		int addPos = 5;
		int value = 26;
		for (int i=n;i>=addPos;i--) {
			ar[i] = ar[i-1];
		}
		ar[addPos-1] = value;
		System.out.println(Arrays.toString(ar));
		
		n++;
		int subtractPos = 3;
		for (int i=subtractPos-1;i<=n;i++) {
			ar[i] = ar[i+1];
		}
		System.out.println(Arrays.toString(ar));
	}	
	
}
