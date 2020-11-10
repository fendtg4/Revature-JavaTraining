package com.bank.service.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.bank.dao.AccountDAO;
import com.bank.dao.TransactionDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.dao.impl.TransactionDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {

	private AccountDAO accountDAO = new AccountDAOImpl();
	private TransactionDAO transactionDAO = new TransactionDAOImpl();
	private static Logger log = Logger.getLogger(BankAccountServiceImpl.class);
	
	@Override
	public BigDecimal viewBalance(int accountId) throws BusinessException {
		
		BigDecimal balance = BigDecimal.ZERO;
		if (accountId<=0) {
			throw new BusinessException("The account ID must be greater than zero!");
		}
		else {
			balance = accountDAO.viewBalance(accountId);
			if (balance == null) {
				throw new BusinessException("There is no account with this ID!");
			}
			return balance;
		}
	}

	@Override
	public Account getAccountFromAccountId(int accountId) throws BusinessException {
		
		Account account = null;
		if (accountId<=0) {
			throw new BusinessException("The account ID must be greater than zero!");
		}
		else {
			account = accountDAO.getAccountFromAccountId(accountId);
			return account;
		}
		
	
	}

	@Override
	public int deposit(int accountId, BigDecimal depositAmount) throws BusinessException {
		BigDecimal currentBalance = viewBalance(accountId);
		int depositSuccessful = 0;
		
		if (accountId<=0) {
			throw new BusinessException("The account ID must be greater than zero!");
		}
		
		else {
			depositSuccessful = accountDAO.deposit(accountId, depositAmount, currentBalance);
			return depositSuccessful;
		}
		
	}

	@Override
	public int withdraw(int accountId, BigDecimal withdrawalAmount) throws BusinessException {
		BigDecimal currentBalance = viewBalance(accountId);
		int withdrawalSuccessful = 0;
		
		if (accountId<=0) {
			throw new BusinessException("The account ID must be greater than zero!");
		}
		else if (withdrawalAmount.compareTo(currentBalance)==1) {
			throw new BusinessException("Cannot withdraw more than the current balance!");
		}
		else {
			withdrawalSuccessful = accountDAO.withdraw(accountId, withdrawalAmount, currentBalance);
			return withdrawalSuccessful;
		}
		
	}

	@Override
	public int transfer(int accountIdSender, Transaction transferTransaction) throws BusinessException {
		
		int requestSuccessful = 0; 
		int accountIdReceiver = transferTransaction.getAccountId();
		if (transferTransaction.getTransactionAmount().compareTo(BigDecimal.ZERO)<0) {
			throw new BusinessException("You must transfer a positive amount!");
		}
		//create an else if condition, checking if the receiver exists in the accounts table
		//must create a service method above first like checkIfAccountExists from ID
		else {
			requestSuccessful = transactionDAO.transfer(accountIdSender, transferTransaction);
			return requestSuccessful;
		}
		
	}
		

	
}
