package com.bank.dao;

import com.bank.exception.BusinessException;
import com.bank.model.Employee;

public interface EmployeeDAO {

	public int createEmployeeAccount(Employee newEmployee) throws BusinessException;
	public boolean checkIfEmployeeAccountExists(String username) throws BusinessException;
}
