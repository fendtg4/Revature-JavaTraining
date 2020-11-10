package com.bank.service.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.bank.dao.AccountDAO;
import com.bank.dao.impl.AccountDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Customer;
import com.bank.service.BankAccountCreationService;


public class BankAccountCreationServiceImpl implements BankAccountCreationService {

	private AccountDAO accountDAO = new AccountDAOImpl();
	private static Logger log = Logger.getLogger(BankAccountCreationServiceImpl.class);
	
	@Override
	public int createBankAccount(Customer customer, BigDecimal startingBalance) throws BusinessException {
	
		
		int bankAccountCreationSuccess = 0;
				
		if (customer.getUsername().isEmpty() || customer.getPassword().isEmpty() || customer.getFirstName().isEmpty() || 
				customer.getLastName().isEmpty() || customer.getEmail().isEmpty()) {
			throw new BusinessException("You cannot have an empty field! Please start again and re-enter the account creation fields");
		}
		
		else {
			bankAccountCreationSuccess = accountDAO.createBankAccount(customer, startingBalance);
			return bankAccountCreationSuccess;
		}
		
	}
	@Override
	public int getAccountId(int customerId) throws BusinessException {
		
		int accountId = 0;
		if (customerId<=0) {
			throw new BusinessException("Error, customer id cannot be less than or equal to zero.");
		}
		else {
			accountId = accountDAO.getAccountId(customerId);
		}
		return accountId;
	}
}
