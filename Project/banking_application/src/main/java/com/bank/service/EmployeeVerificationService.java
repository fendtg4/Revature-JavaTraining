package com.bank.service;

import com.bank.exception.BusinessException;

public interface EmployeeVerificationService {

	public boolean checkIfEmployeeAccountExists(String username) throws BusinessException;

}
