package com.bank.dao.impl.test.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.dao.AccountDAO;
import com.bank.dao.impl.test.AccountDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Customer;

class AccountDAOImplTest {

	private static Logger log = Logger.getLogger(AccountDAOImplTest.class);
	private static AccountDAO accountDAO;
	
	@BeforeAll
	public static void setUpService() {
		accountDAO = new AccountDAOImpl();
	}
	
	@Test
	void testCreateBankAccount() {
		Customer customer = new Customer(101, "JohnSmith123", "password123", "John", "Smith", "JohnSmith123@yahoo.com", 101);
		
		BigDecimal startingBalance = new BigDecimal("10000");
		try {
			int success = accountDAO.createBankAccount(customer, startingBalance);
			assertEquals(1, success);
		} catch (BusinessException e) {
			
			log.error(e);
		}
		
	
	}
	
	//increment account ID by 1
	@Test
	void testGetAccountId() {
		
		
		try {
			int accountId = accountDAO.getAccountId(101);
			assertEquals(116, accountId);
		} catch (BusinessException e) {

			log.error(e);
		}
		
	}

	@Test
	void testViewBalance() {
		
		BigDecimal testBalance = new BigDecimal("4100.00");
		try {
			BigDecimal balance = accountDAO.viewBalance(101);
			assertEquals(testBalance, balance);
		}  catch (BusinessException e) {

			log.error(e);
		}
	}
	
	@Test
	void testGetAccountFromAccountId() {
		BigDecimal balance = new BigDecimal("100.00");
		Account testAccount = new Account(101, balance, 101);
		Account account = null;
		try {
			account = accountDAO.getAccountFromAccountId(101);
			assertEquals(testAccount, account);
		} 	catch (BusinessException e) {

			log.error(e);
		}
		
	}

	@Test
	void testDeposit() {
		
		BigDecimal depositAmount = new BigDecimal("1000.00");
		BigDecimal currentBalance = new BigDecimal("3100.00");
		try {
			int success = accountDAO.deposit(101, depositAmount, currentBalance);
			assertEquals(1, success);
		} catch (BusinessException e) {

			log.error(e);
		}
	}

	@Test
	void testWithdraw() {

		BigDecimal withdrawalAmount = new BigDecimal("1000.00");
		BigDecimal currentBalance = new BigDecimal("2100.00");
		try {
			int success = accountDAO.deposit(101, withdrawalAmount, currentBalance);
			assertEquals(1, success);
		} catch (BusinessException e) {

			log.error(e);
		}
	}
	

	@Test
	void testCheckIfAccountExists() {
		
		try {
			boolean b = accountDAO.checkIfAccountExists(101);
			assertTrue(b);
		} catch (BusinessException e) {

			log.error(e);
		}

	}

	@Test
	void testCheckIfAccountBelongsToCustomer() {
		
		try {
			boolean b = accountDAO.checkIfAccountBelongsToCustomer(101, 101);
			assertTrue(b);
		} catch (BusinessException e) {

			log.error(e);
		}
	}


	@Test
	void testReceiveTransfer() {
		
		BigDecimal testAmount = new BigDecimal("2000.00");
		try {
			BigDecimal amount = accountDAO.receiveTransfer(101);
			assertEquals(amount, testAmount);
		} catch(BusinessException e) {
			log.error(e);
		}
	}

	@Test
	void testGetAccountsFromCustomerId() {
		
		
		try {
			accountDAO.getAccountsFromCustomerId(101);
			
		} catch(BusinessException e) {
			log.error(e);
		}
	}

}
