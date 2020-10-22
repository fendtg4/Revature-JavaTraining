package inheritance;

public class MobileV2 extends MobileV1 {

	public MobileV2() {
		//super();
		System.out.println("V2");	
		}
	public void gaming() {
		System.out.println("Gaming feature from V2");
	}
	
	public void sendMMS() {
		System.out.println("MMS feature from V2");
	}
	@Override
	public void calling() {
		System.out.println("Calling feature from V2");
	}
}
