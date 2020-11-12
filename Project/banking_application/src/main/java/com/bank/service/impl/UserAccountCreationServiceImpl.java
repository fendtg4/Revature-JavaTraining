package com.bank.service.impl;

import org.apache.log4j.Logger;

import com.bank.exception.BusinessException;
import com.bank.model.User;
import com.bank.service.UserAccountCreationService;
import com.bank.dao.UserDAO;
import com.bank.dao.impl.UserDAOImpl;

public class UserAccountCreationServiceImpl implements UserAccountCreationService {
	
	
	private static Logger log = Logger.getLogger(UserAccountCreationServiceImpl.class);
	
	private UserDAO userDAO = new UserDAOImpl();
	
	@Override
	public int createUserAccount(User newUser) throws BusinessException {
		
		int accountCreationSuccess = 0;
	
		if (newUser.getUsername().isEmpty() || newUser.getPassword().isEmpty() || newUser.getFirstName().isEmpty() || 
				newUser.getLastName().isEmpty() || newUser.getEmail().isEmpty() || newUser.getUserType().isEmpty()) {
			throw new BusinessException("You cannot have an empty field! Please start again and re-enter the account creation fields");
		}
		else {
			accountCreationSuccess = userDAO.createUserAccount(newUser);
			return accountCreationSuccess;
		}
		
	}
	//Inserts user id from database into a user object
	@Override
	public void insertUserId(User user) throws BusinessException {
		
		if (user ==null) {
			throw new BusinessException("Error, there is no user.");
		}
		else {
			userDAO.insertUserId(user);
		}
		
	}
}
