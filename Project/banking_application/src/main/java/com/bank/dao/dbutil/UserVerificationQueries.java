package com.bank.dao.dbutil;

public class UserVerificationQueries {

	public final static String VERIFYUSERNAME = "select username from super_bank.users where username = ?";
	public static final String VERIFYPASSWORD = "select user_id from super_bank.users where username = ? and password = ?";
	public static final String CHECKUSERTYPE = "select user_type from super_bank.users where username = ?";
	public static final String GETUSERFROMUSERNAME = "select user_id, username, password, first_name, last_name, email, user_type from super_bank.users where username = ?";
}