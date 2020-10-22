package testexception;

public class Validation {

	public boolean isValidId(int id) throws BusinessCheckedException {
		
		
		if (id<0) {
			throw new BusinessCheckedException("ID cannot be negative");
		}
		else {
			return true;
		}
	}
	
	public boolean isValidZipCode (int zipCode) throws BusinessUncheckedException {
		System.out.println(zipCode);
		String zipCodeString = Integer.toString(zipCode);
		if (!(zipCodeString.matches("[0-9]{5}")|| zipCodeString.matches("[0-9]{9}")|| zipCodeString.matches("[0-9]{5}[\\-]{1}[0-9]{4}")|| zipCodeString.matches("[-]{1}") )) {
			throw new BusinessUncheckedException("Zip code must be 5 integers");
		}
		else {
			return true;
		}
	}
	
}
