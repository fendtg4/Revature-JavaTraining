package com.bank.service;

import com.bank.exception.BusinessException;
import com.bank.model.Customer;

public interface CustomerVerificationService {

	public boolean checkIfCustomerAccountExists(String username) throws BusinessException;
	public int getCustomerIdFromUsername(String username) throws BusinessException;
	public String getCustomerUsernameFromId(int customerId) throws BusinessException;
}
