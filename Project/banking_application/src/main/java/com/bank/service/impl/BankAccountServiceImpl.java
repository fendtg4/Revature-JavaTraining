package com.bank.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import com.bank.dao.AccountDAO;
import com.bank.dao.TransactionDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.dao.impl.TransactionDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.BankAccountService;
import com.bank.service.CustomerVerificationService;

public class BankAccountServiceImpl implements BankAccountService {

	private CustomerVerificationService customerVerificationService = new CustomerVerificationServiceImpl();
	private AccountDAO accountDAO = new AccountDAOImpl();
	private TransactionDAO transactionDAO = new TransactionDAOImpl();
	private static Logger log = Logger.getLogger(BankAccountServiceImpl.class);

	@Override
	public boolean checkIfAccountExists(int accountId) throws BusinessException {
		
		if (accountId<=0) {
			throw new BusinessException("The account ID must be greater than zero!");
		}
		else {
			return accountDAO.checkIfAccountExists(accountId);
		}
		
	}
	

	@Override
	public void updateAccountStatus(int accountId, String string) throws BusinessException {
		
		if (!(checkIfAccountExists(accountId))) {
			throw new BusinessException("There is no account for this account ID!");
		}
		else {
			transactionDAO.updateAccountStatus(accountId, string);
		}
	}

	@Override
	public boolean checkIfAccountApproved(int accountId, int customerId) throws BusinessException {
		
		if (!(checkIfAccountExists(accountId))) {
			throw new BusinessException("There is no account for this account ID!");
		}
		else if (!(checkIfAccountBelongsToCustomer(accountId, customerId))) {
			throw new BusinessException("You do not own the account for this account ID.");
		}
		else {
			return transactionDAO.checkIfAccountApproved(accountId);
		}
		
	}
	

	
		
	
	@Override
	public boolean checkIfAccountBelongsToCustomer(int accountId, int customerId) throws BusinessException {
		
		boolean belongs = false;
		if (!(checkIfAccountExists(accountId))) {
			throw new BusinessException("There is no account for this account ID!");
		}
		else {
			belongs = accountDAO.checkIfAccountBelongsToCustomer(accountId, customerId);
		}
		
		return belongs;
	}
	@Override
	public BigDecimal viewBalance(int accountId, int customerId) throws BusinessException {
		
		BigDecimal balance = BigDecimal.ZERO;
		if (!(checkIfAccountExists(accountId))) {
			throw new BusinessException("There is no account for this account ID!");
		}
		else if (!(checkIfAccountBelongsToCustomer(accountId, customerId))) {
			throw new BusinessException("You do not own the account for this account ID.");
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
		
		if (!(checkIfAccountExists(accountId))) {
			throw new BusinessException("There is no account for this account ID!");
		}
		
		else {
			account = accountDAO.getAccountFromAccountId(accountId);
			return account;
		}
		
	
	}

	@Override
	public int deposit(int accountId, BigDecimal depositAmount, int customerId) throws BusinessException {
		BigDecimal currentBalance = viewBalance(accountId, customerId);
		int depositSuccessful = 0;
		
		if (!(checkIfAccountExists(accountId))) {
			throw new BusinessException("There is no account for this account ID!");
		}
		
		else {
			depositSuccessful = accountDAO.deposit(accountId, depositAmount, currentBalance);
			if (depositSuccessful == 1) {
				String transactionDescription = depositAmount + " was deposited in Customer no " + customerId +"'s account no " + accountId + ". The total balance is now " + currentBalance;
				Transaction depositTransaction = new Transaction(depositAmount, transactionDescription, Timestamp.valueOf(LocalDateTime.now()), accountId, "PROCESSED");
				transactionDAO.createNewTransaction(depositTransaction);
			}
			return depositSuccessful;
		}
		
	}

	@Override
	public int withdraw(int accountId, BigDecimal withdrawalAmount, int customerId) throws BusinessException {
		BigDecimal currentBalance = viewBalance(accountId, customerId);
		int withdrawalSuccessful = 0;
		
		if (!(checkIfAccountExists(accountId))) {
			throw new BusinessException("There is no account for this account ID!");
		}
		else if (withdrawalAmount.compareTo(currentBalance)==1) {
			throw new BusinessException("Cannot withdraw more than the current balance!");
		}
		else {
			withdrawalSuccessful = accountDAO.withdraw(accountId, withdrawalAmount, currentBalance);
			if (withdrawalSuccessful == 1) {
				String transactionDescription = withdrawalAmount + " was withdrew in Customer no " + customerId +"'s account no " + accountId + ". The total balance is now " + currentBalance;
				Transaction withdrawalTransaction = new Transaction(withdrawalAmount, transactionDescription, Timestamp.valueOf(LocalDateTime.now()), accountId, "PROCESSED");
				transactionDAO.createNewTransaction(withdrawalTransaction);
			}
			return withdrawalSuccessful;
		}
		
	}

	@Override
	public int transfer(int accountIdSender, Transaction transferTransaction) throws BusinessException {
		
		int requestSuccessful = 0; 
		if (!(checkIfAccountExists(accountIdSender))) {
			throw new BusinessException("There is no account for the sender's account ID!");
		}
		if (!(checkIfAccountExists(transferTransaction.getAccountId()))) {
			throw new BusinessException("There is no account for the receiver's account ID!");
		}
		else {
			requestSuccessful = transactionDAO.transfer(accountIdSender, transferTransaction);
			return requestSuccessful;
		}
		
	}

	public boolean checkIfAccountInTransactions (int accountId) throws BusinessException {
		
		boolean inTransactions = false;
		if (!(checkIfAccountExists(accountId))) {
			throw new BusinessException("There is no account for this account ID!");
		}
		else {
			inTransactions = transactionDAO.checkIfAccountInTransactions(accountId);
			if (inTransactions) {
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	
	public boolean receiveTransfer(int accountId, int customerId) throws BusinessException {
		
		BigDecimal currentBalance = viewBalance(accountId, customerId);
		boolean transferReceived = false;
		BigDecimal amountTransferred = BigDecimal.ZERO;		
		if (!(checkIfAccountExists(accountId))) {
			throw new BusinessException("There is no account for this account ID!");
		}
		else {
	
			amountTransferred = accountDAO.receiveTransfer(accountId);
			if (amountTransferred.compareTo(BigDecimal.ZERO)>0) {
				
				transferReceived = true;
			}
			if (transferReceived) {
				
				accountDAO.deposit(accountId, amountTransferred, currentBalance);
				transactionDAO.updateStatusAfterMoneySent(accountId);
				return transferReceived;
			}
			else {
				throw new BusinessException("There was no money to transfer.");
			}
			}
	}


	@Override
	public void createNewTransaction(Transaction transaction)  throws BusinessException {
		
		
		if (!(checkIfAccountExists(transaction.getAccountId()))) {
			throw new BusinessException("There is no account for this account ID!");
		}
		else {
			transactionDAO.createNewTransaction(transaction);
		}
	}


	@Override
	public void getAccountsFromCustomerId(int customerId) throws BusinessException {
		
		String username = customerVerificationService.getCustomerUsernameFromId(customerId);
		
		if (!(customerVerificationService.checkIfCustomerAccountExists(username))) {
			throw new BusinessException("There is no account for this customer ID!");
		}
		else {
			accountDAO.getAccountsFromCustomerId(customerId);
		}
	}


	@Override
	public void getAllTransactions() throws BusinessException {
		
		
		if (!transactionDAO.getAllTransactions()) {
			throw new BusinessException("There are currently no transactions");
		}
		
	}

	

	
}
