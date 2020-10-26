package interfacetest;

public class Goblin implements Enemy {

	@Override
	public void attackPlayer() {
		System.out.println("Attack!");
		
	}

	@Override
	public void move() {
		System.out.println("Goblin is moving!");
		
	}

	
	
}
