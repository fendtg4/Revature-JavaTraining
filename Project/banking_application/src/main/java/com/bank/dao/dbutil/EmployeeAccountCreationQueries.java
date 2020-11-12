package com.bank.dao.dbutil;

public class EmployeeAccountCreationQueries {

	public static final String INSERTNEWEMPLOYEEACCOUNT = "insert into super_bank.employees (username, \"password\", first_name, last_name, email, user_id) values" 
	+ " (?, ?, ?, ?, ?, ?)";
}
