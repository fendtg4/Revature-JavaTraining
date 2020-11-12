package com.bank.service.impl;

import com.bank.dao.EmployeeDAO;
import com.bank.dao.impl.EmployeeDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.service.EmployeeVerificationService;

public class EmployeeVerificationServiceImpl implements EmployeeVerificationService {

	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	@Override
	public boolean checkIfEmployeeAccountExists(String username) throws BusinessException {
		
	
		if (username.isEmpty()) {
			throw new BusinessException("You must input a username!");
		}
		else if (employeeDAO.checkIfEmployeeAccountExists(username)) {
			return true;
		}
		return false;
	}
	
	

}
