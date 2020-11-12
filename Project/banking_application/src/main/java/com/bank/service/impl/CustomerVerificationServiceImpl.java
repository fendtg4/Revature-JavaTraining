package com.bank.service.impl;

import com.bank.exception.BusinessException;
import com.bank.service.CustomerVerificationService;
import com.bank.model.Customer;

import org.apache.log4j.Logger;

import com.bank.dao.CustomerDAO;
import com.bank.dao.impl.CustomerDAOImpl;
public class CustomerVerificationServiceImpl implements CustomerVerificationService {

	private static Logger log = Logger.getLogger(CustomerVerificationServiceImpl.class);
	CustomerDAO customerDAO = new CustomerDAOImpl();
	@Override
	public boolean checkIfCustomerAccountExists(String username) throws BusinessException {
		
	
		if (username.isEmpty()) {
			throw new BusinessException("You must input a username!");
		}
		else if (customerDAO.checkIfCustomerAccountExists(username)) {
			return true;
		}
		return false;
	}
	@Override
	public int getCustomerIdFromUsername(String username) throws BusinessException {
		
		int customerId = 0;
		if (username.isEmpty()) {
			throw new BusinessException("You must input a username!");
		}
		
		else {
			customerId = customerDAO.getCustomerIdFromUsername(username);
			return customerId;
		}
		
	}
	
	public String getCustomerUsernameFromId(int customerId) throws BusinessException {
		
		String username = null;
		if (customerId<=0) {
			throw new BusinessException("You must input a positive ID!");
		}
		else {
			username = customerDAO.getCustomerUsernameFromId(customerId);
			return username;
		}
		
	}
	
	
}
