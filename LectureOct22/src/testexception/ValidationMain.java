package testexception;

public class ValidationMain {

	public static void main(String[] args) {
		
	
		
		Validation v = new Validation();
		
		try {
			if (v.isValidId(3)) {
				System.out.println("Id validated");
			}
		}
		catch (BusinessCheckedException e){
				System.out.println("Id not valid");
		}
		
		try {
			if (v.isValidZipCode(12345)) {
				System.out.println("Zip Code validated");
			}
		}
			catch (BusinessUncheckedException e) {
				System.out.println("Zip Code not valid");
			}
		}

	}


