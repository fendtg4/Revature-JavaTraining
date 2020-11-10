package com.bank.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Transaction {

	private int transactionId;
	BigDecimal transactionAmount;
	private String transactionDescription;
	private Timestamp transactionTime;
	private int accountId;
	private String status;
	
	public Transaction(BigDecimal transactionAmount, String transactionDescription, Timestamp transactionTime, int accountId, String status) {
		this.transactionAmount = transactionAmount;
		this.transactionDescription = transactionDescription;
		this.transactionTime = transactionTime;
		this.accountId = accountId;
		this.status = status;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	

	public Timestamp getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionDescription=" + transactionDescription
				+ ", transactionTime=" + transactionTime + ", accountId=" + accountId + "]";
	}
	
	
	
}
