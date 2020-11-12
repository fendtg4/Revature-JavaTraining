package com.bank.dao.dbutil;

public class BankAccountServiceQueries {

	public static final String GETBALANCE = "select balance from super_bank.accounts where account_id = ?";
	public static final String GETACCOUNT = "select account_id, balance, customer_id from super_bank.accounts where account_id = ?";
	public static final String UPDATEACCOUNTBALANCE = "update super_bank.accounts set balance = ? where account_id = ?";
	public static final String CREATETRANSACTION = "insert into super_bank.transactions (transaction_amount, transaction_description, "
			+ "transaction_time, account_id, status) values (?, ?, ?, ?, ?)";
	
	public static final String VERIFYACCOUNTID = "select account_id from super_bank.accounts where account_id = ?";
	public static final String CHECKIFACCOUNTBELONGSTOCUSTOMER = "select customer_id from super_bank.accounts where account_id = ?";
	public static final String CHECKIFACCOUNTINTRANSACTIONS = "select transaction_id from super_bank.transactions where account_id = ?";
	public static final String RECEIVETRANSACTIONS = "select transaction_amount from super_bank.transactions where account_id = ? and "
			+ "transaction_description like 'Account%' and status = 'PENDING'";
	
	public static final String UPDATESTATUSAFTERMONEYSENT = "update super_bank.transactions set status = 'ACCEPTED' where "
			+ "transaction_description like 'Account%' and account_id = ?";
	
	public static final String CHECKIFACCOUNTAPPROVED = "select status from super_bank.transactions where transaction_description like"
			+ " 'Customer%' and account_id = ?";
	
	public static final String EMPLOYEEUPDATESTATUS = "update super_bank.transactions set status = ? where transaction_description like "
			+ "'Customer%' and account_id = ?";
	
	public static final String GETACCOUNTSFROMCUSTOMERID = "select account_id, balance from super_bank.accounts where customer_id = ?";
	public static final String GETALLTRANSACTIONS = "select transaction_id, transaction_amount, transaction_description, transaction_time, "
			+ "account_id, status from super_bank.transactions";
}
