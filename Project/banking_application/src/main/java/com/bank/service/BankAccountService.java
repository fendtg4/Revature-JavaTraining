package com.bank.service;

import java.math.BigDecimal;

import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Transaction;

public interface BankAccountService {

	public boolean checkIfAccountExists(int accountId) throws BusinessException;
	public boolean checkIfAccountApproved(int accountId, int customerId) throws BusinessException;
	public boolean checkIfAccountBelongsToCustomer(int accountId, int customerId) throws BusinessException;
	public BigDecimal viewBalance(int accountId, int customerId) throws BusinessException;
	public Account getAccountFromAccountId(int accountId) throws BusinessException;
	public int deposit(int accountId, BigDecimal depositAmount, int customerId) throws BusinessException;
	public int withdraw(int accountId, BigDecimal withdrawalAmount, int customerId) throws BusinessException;
	public int transfer(int accountIdSender, Transaction transferTransaction) throws BusinessException;
	public boolean checkIfAccountInTransactions(int accountId) throws BusinessException;
	public boolean receiveTransfer(int accountId, int customerId) throws BusinessException;
	public void updateAccountStatus(int accountId, String string) throws BusinessException;
	public void createNewTransaction(Transaction transaction) throws BusinessException;
	public void getAccountsFromCustomerId(int customerId) throws BusinessException;
	public void getAllTransactions() throws BusinessException;
}
