package com.bank.model;

public class Employee extends User {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int userId;
	private int employeeId;
	
	public Employee() {
		super();
	}


	public Employee(String username, String password, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}


	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", , userId=" + userId + ", employeeId="
				+ employeeId + "]";
	}
	
	
	
}

