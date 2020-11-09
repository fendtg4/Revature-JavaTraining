package com.bank.model;

import java.time.LocalDateTime;

public class Transaction {

	private int transactionId;
	private String transactionDescription;
	private LocalDateTime transactionTime;
	private int accountId;
	
	public Transaction(String transactionDescription, LocalDateTime transactionTime) {
		super();
		this.transactionDescription = transactionDescription;
		this.transactionTime = transactionTime;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionDescription=" + transactionDescription
				+ ", transactionTime=" + transactionTime + ", accountId=" + accountId + "]";
	}
	
	
	
}
