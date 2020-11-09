package com.bank.service.impl;

import com.bank.exception.BusinessException;
import com.bank.model.User;
import com.bank.service.UserVerificationService;

import org.apache.log4j.Logger;

import com.bank.dao.UserDAO;
import com.bank.dao.impl.UserDAOImpl;

public class UserVerificationServiceImpl implements UserVerificationService {
	
	private static Logger log = Logger.getLogger(UserVerificationService.class);
	private UserDAO userDAO = new UserDAOImpl();
	@Override
	public boolean checkIfUserExists(String username) throws BusinessException {
		
		
		if (username.isEmpty()) {
			throw new BusinessException("You must input a username!");
		}
		else if (userDAO.checkIfUserExists(username)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkPassword(String username, String password) throws BusinessException {
		
		if (password.isEmpty()) {
			throw new BusinessException("You must input a password!");
		}
		else if (userDAO.checkPassword(username,password)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkIfCustomer(String username) throws BusinessException {
		
		if (username.isEmpty()) {
			throw new BusinessException("Error, username is empty.");
		}
		else if (userDAO.checkIfCustomer(username)) {
			return true;
		}
		return false;
	}

	@Override
	public User getUserFromUsername(String username) throws BusinessException {
		
		if (username.isEmpty()) {
			throw new BusinessException("Error, username is empty.");
		}
		else {
			User user = userDAO.getUserFromUsername(username);
			return user;
		}
	}



}
