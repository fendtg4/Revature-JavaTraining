package comparable_comparator_interfaces_test;

public class Student implements Comparable<Student>{

	String name;
	int age;
	
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public int compareTo(Student s) {
		
		return this.name.compareTo(s.name);
	}
}
	
	
	