package com.bank.dao;

import java.math.BigDecimal;

import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Transaction;

public interface AccountDAO {
	public int createBankAccount(Customer customer, BigDecimal startingBalance) throws BusinessException;
	public int getAccountId(int Customerid) throws BusinessException;
	public BigDecimal viewBalance (int accountId) throws BusinessException;
	public Account getAccountFromAccountId(int accountId) throws BusinessException;
	public int deposit(int accountId, BigDecimal depositAmount, BigDecimal currentBalance)throws BusinessException;
	public int withdraw(int accountId, BigDecimal withdrawalAmount, BigDecimal currentBalance)throws BusinessException;
	public boolean checkIfAccountExists(int accountId) throws BusinessException;
	public boolean checkIfAccountBelongsToCustomer(int accountId, int customerId) throws BusinessException;
	public BigDecimal receiveTransfer(int accountId) throws BusinessException;
	public void getAccountsFromCustomerId(int customerId) throws BusinessException;
	
}
