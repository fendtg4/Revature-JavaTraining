package eg2;

public class SearchArray {

	public boolean isExisting(int[] array, int searchedElement) {
		
		boolean b = false;
		for (int x: array) {
			if (x==searchedElement) {
				b = true;
				return b;
			}
		}
		return b;
	}
	
	public void printAllLocationsOfSearchValue(int[] array, int searchedElement) {
		boolean b = false;
		
		for (int i=0;i<array.length;i++) {
			if (array[i]==searchedElement) {
				b = true;
				System.out.println("Element at index " + i);
			}
			
		}
		if (!b) {
			System.out.println("No matching elements found");
		}
		
		
	}
	
	public void printAllPrimeNumbers(int[] array) {
		boolean primesExist = false;
		
		
		for (int i =0;i<array.length;i++) {
			int counter = 0;
			for (int j=1;j<=array[i];j++) {
				if (array[i]%j==0) {
					counter++;
				}
			}
			if (counter==2) {
				primesExist = true;
				System.out.print(array[i] + " ");
			}
		
		}
		
		if (!primesExist) {
			System.out.println("No prime numbers in this array");
		}
		
	}
}
