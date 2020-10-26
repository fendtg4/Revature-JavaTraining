package abstracttest;

public class RevatureEmployee extends EMS {
	
	public static void main(String[] args) {
		
		RevatureEmployee re1 = new RevatureEmployee();
		
		re1.addEmployee();
		re1.readEmployee();
		re1.updateEmployee();
		re1.deleteEmployee();
	}

	@Override
	public void addEmployee() {
		// TODO Auto-generated method stub
		System.out.println("Revature employee added");
	}

	@Override
	public void readEmployee() {
		System.out.println("Revature employee retrieved");
		
	}

	@Override
	public void updateEmployee() {
		System.out.println("Revature employee updated");
		
	}

	@Override
	public void deleteEmployee() {
		System.out.println("Revature employee deleted");
		
	}


	
	

}
