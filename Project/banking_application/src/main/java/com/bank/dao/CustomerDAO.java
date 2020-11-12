package com.bank.dao;

import com.bank.exception.BusinessException;
import com.bank.model.Customer;

public interface CustomerDAO {

	public int createCustomerAccount(Customer newCustomer) throws BusinessException;
	public void insertCustomerId(Customer customer) throws BusinessException;
	public boolean checkIfCustomerAccountExists(String username) throws BusinessException;
	public int getCustomerIdFromUsername(String username) throws BusinessException;
	public String getCustomerUsernameFromId(int customerId) throws BusinessException;

}
