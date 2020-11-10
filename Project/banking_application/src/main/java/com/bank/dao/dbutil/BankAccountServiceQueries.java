package com.bank.dao.dbutil;

public class BankAccountServiceQueries {

	public static final String GETBALANCE = "select balance from super_bank.accounts where account_id = ?";
	public static final String GETACCOUNT = "select account_id, balance, customer_id from super_bank.accounts where account_id = ?";
	public static final String UPDATEACCOUNTBALANCE = "update super_bank.accounts set balance = ? where account_id = ?";
	public static final String CREATETRANSFERREQUEST = "insert into super_bank.transactions (transaction_description, transaction_time, account_id, status) values (?, ?, ?, ?)";
	
}
