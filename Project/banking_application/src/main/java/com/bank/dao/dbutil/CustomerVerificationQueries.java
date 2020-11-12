 package com.bank.dao.dbutil;

public class CustomerVerificationQueries {

	public final static String VERIFYUSERNAME = "select username from super_bank.customers where username = ?";
	public final static String GETCUSTOMERIDFROMUSERNAME = "select customer_id from super_bank.customers where username = ?";
	public static final String GETCUSTOMERUSERNAMEFROMID = "select username from super_bank.customers where customer_id = ?";
}
