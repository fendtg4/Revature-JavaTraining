package com.bank.dao.dbutil;

public class CustomerAccountCreationQueries {

	public static final String INSERTNEWCUSTOMERACCOUNT = "insert into super_bank.customers (username, \"password\", first_name, last_name, email, user_id) values"
			+ " (?, ?, ?, ?, ?, ?)";
	public static final String INSERTCUSTOMERID = "select customer_id from super_bank.customers where username = ?";
}
