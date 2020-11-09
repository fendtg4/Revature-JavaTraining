package com.bank.dao.dbutil;

public class BankAccountCreationQueries {

	public static final String INSERTNEWBANKACCOUNT = "insert into super_bank.accounts (balance, customer_id) values (?, ?)";
}
