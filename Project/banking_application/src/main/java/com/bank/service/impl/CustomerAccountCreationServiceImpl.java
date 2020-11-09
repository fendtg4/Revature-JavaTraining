package com.bank.service.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.bank.dao.CustomerDAO;
import com.bank.dao.impl.CustomerDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Customer;
import com.bank.service.CustomerAccountCreationService;

public class CustomerAccountCreationServiceImpl implements CustomerAccountCreationService {


	private static Logger log = Logger.getLogger(CustomerAccountCreationServiceImpl.class);
	
	private CustomerDAO customerDAO = new CustomerDAOImpl();
	@Override
	public int createCustomerAccount(Customer newCustomer) throws BusinessException {
			
			int accountCreationSuccess = 0;
			if (newCustomer.getUsername().isEmpty() || newCustomer.getPassword().isEmpty() || newCustomer.getFirstName().isEmpty() || 
					newCustomer.getLastName().isEmpty() || newCustomer.getEmail().isEmpty()) {
				throw new BusinessException("You cannot have an empty field! Please start again and re-enter the account creation fields");
			}
			else {
				accountCreationSuccess = customerDAO.createCustomerAccount(newCustomer);
				return accountCreationSuccess;
			}
		
	}
	
	//inserts user id from database into a user object
		@Override
		public void insertCustomerId(Customer customer) throws BusinessException {
			
			if (customer ==null) {
				throw new BusinessException("Error, there is no customer.");
			}
			else {
				customerDAO.insertCustomerId(customer);
			}
			
	
		}

		
		
}