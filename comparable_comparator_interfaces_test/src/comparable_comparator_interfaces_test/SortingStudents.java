package comparable_comparator_interfaces_test;

import java.util.ArrayList;
import java.util.Collections;

public class SortingStudents {

	public static void main(String[] args) {
		ArrayList<Student> sl = new ArrayList<Student>();
		
		
		sl.add(new Student("David", 20));
		sl.add(new Student("David", 23));
		sl.add(new Student("John", 25));
		sl.add(new Student("John", 21));
		sl.add(new Student("Amanda", 20));
		sl.add(new Student("Samantha", 24));
		sl.add(new Student("Amanda", 30));
		
		System.out.println("Sorting by student name");
		
		Collections.sort(sl);
		for(Student s: sl) {
			System.out.println(s.getName() + " " + s.getAge());
		}
		
		System.out.println("Sorting by student age");
		Collections.sort(sl, new StudentAgeComparator());
		for (Student s: sl) {
			System.out.println(s.getName() + " " + s.getAge());
		}
		
	}
	
}
	
