package com.bank.model;

import java.math.BigDecimal;

public class Account {

	private int accountId;
	private BigDecimal balance;
	private int customerId;
	
	public Account(int accountId, BigDecimal balance, int customerId) {
		this.accountId = accountId;
		this.balance = balance;
		this.customerId = customerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", customerId=" + customerId + "]";
	}
	
	
}
