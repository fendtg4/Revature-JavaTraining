package com.bank.service.impl;

import org.apache.log4j.Logger;

import com.bank.dao.EmployeeDAO;
import com.bank.dao.impl.EmployeeDAOImpl;
import com.bank.exception.BusinessException;
import com.bank.model.Employee;
import com.bank.service.EmployeeAccountCreationService;

public class EmployeeAccountCreationServiceImpl implements EmployeeAccountCreationService{
	
	private static Logger log = Logger.getLogger(EmployeeAccountCreationServiceImpl.class);

	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	@Override
	public int createEmployeeAccount(Employee newEmployee) throws BusinessException {
		
		int accountCreationSuccess = 0;
		if (newEmployee.getUsername().isEmpty() || newEmployee.getPassword().isEmpty() || newEmployee.getFirstName().isEmpty() || 
				newEmployee.getLastName().isEmpty() || newEmployee.getEmail().isEmpty()) {
			throw new BusinessException("You cannot have an empty field! Please start again and re-enter the account creation fields");
		}
		else {
			accountCreationSuccess = employeeDAO.createEmployeeAccount(newEmployee);
			return accountCreationSuccess;
		}

	}

	
}
