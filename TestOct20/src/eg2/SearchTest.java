package eg2;

public class SearchTest {

	public static void main(String[] args) {
		
		int[] ar = {4,8,10,8,22};
		
		SearchArray sa = new SearchArray();
		
		System.out.println(sa.isExisting(ar, 15));
		
		sa.printAllLocationsOfSearchValue(ar, 8);
		
		sa.printAllPrimeNumbers(ar);
	}
}
