package settest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetDemo {
	
	public static void main(String[] args) {
		
		Set<Integer> is1 = new HashSet<>();
		
		is1.add(3);
		is1.add(4);
		is1.add(4);
		is1.add(5);
		
		System.out.println(is1);
		
		Set<Employee> employees = new HashSet<>();
		
		employees.add(new Employee(12345, "John Smith"));
		employees.add(new Employee(12345, "John Smith"));
		
		for (Employee e:employees) {
			System.out.println(e);
		}
		
		
		/*Iterator <Employee> i = employees.iterator();
		
		while (i.hasNext()) {
			System.out.println(i.next());
		}*/
		
	}

}
