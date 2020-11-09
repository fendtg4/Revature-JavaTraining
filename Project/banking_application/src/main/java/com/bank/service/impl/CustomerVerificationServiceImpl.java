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
	public boolean checkIfCustomerExists(String username) throws BusinessException {
		
	
		if (username.isEmpty()) {
			throw new BusinessException("You must input a username!");
		}
		else if (customerDAO.checkIfCustomerAccountExists(username)) {
			return true;
		}
		return false;
	}
	
	
	
}
