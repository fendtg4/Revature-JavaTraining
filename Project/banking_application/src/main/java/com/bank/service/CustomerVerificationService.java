package com.bank.service;

import com.bank.exception.BusinessException;

public interface CustomerVerificationService {

	public boolean checkIfCustomerExists(String username) throws BusinessException;
}
