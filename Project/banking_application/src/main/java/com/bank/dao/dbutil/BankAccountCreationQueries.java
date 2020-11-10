package com.bank.dao.dbutil;

public class BankAccountCreationQueries {

	public static final String INSERTNEWBANKACCOUNT = "insert into super_bank.accounts (balance, customer_id) values (?, ?)";
	public static final String GETACCOUNTID = "select account_id from super_bank.accounts where customer_id = ? order by account_id desc limit 1";
}
