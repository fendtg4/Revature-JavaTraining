package com.bank.service;

import com.bank.exception.BusinessException;
import com.bank.model.User;

public interface UserVerificationService {

	public boolean checkIfUserExists(String username) throws BusinessException;
	public boolean checkPassword(String username, String password) throws BusinessException;
	public boolean checkIfCustomer(String username) throws BusinessException;
	public User getUserFromUsername(String username) throws BusinessException;
}
