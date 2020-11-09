package com.bank.service;


import com.bank.exception.BusinessException;
import com.bank.model.Customer;

public interface CustomerAccountCreationService {

	public int createCustomerAccount(Customer newCustomer) throws BusinessException;
	public void insertCustomerId(Customer customer) throws BusinessException;
}
