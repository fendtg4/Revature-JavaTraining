package com.bank.dao.dbutil;

public class UserAccountCreationQueries {

	public static final String INSERTNEWUSERACCOUNT = "insert into super_bank.users (username, \"password\", first_name, last_name, email, user_type) values"
			+ " (?, ?, ?, ?, ?, ?)";
	public static final String INSERTUSERID = "select user_id from super_bank.users where username = ?";
}
