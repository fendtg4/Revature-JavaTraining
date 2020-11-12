package com.bank.service;

import com.bank.exception.BusinessException;
import com.bank.model.Employee;

public interface EmployeeAccountCreationService {

	public int createEmployeeAccount(Employee newEmployee) throws BusinessException;
}
