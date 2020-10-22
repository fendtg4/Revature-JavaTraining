package inheritance;

public class MobileMain {
	public static void main(String[] args) {
		
		MobileV3 v3=new MobileV3();
		MobileV3 v33 = new MobileV3();
		System.out.println(v3.hashCode());
		System.out.println(v33.hashCode());
		v3.calling();
		v3.gaming();
		v3.socialNwApps();
		v3.sendMessage();
		v3.sendMMS();
		System.out.println("v3.getClass() : "+v3.getClass());
		System.out.println("v3.hashCode() : "+v3.hashCode());
		v3.videoCalls();
		
		MobileV1 v12 = new MobileV2(); // Implicit Casting
		v12.calling();
		MobileV2 v2 = (MobileV2)v12; // Explicit Casting
		v2.calling();
		v2.gaming();
	}
}
