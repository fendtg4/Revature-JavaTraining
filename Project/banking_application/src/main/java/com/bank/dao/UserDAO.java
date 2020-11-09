package com.bank.dao;

import com.bank.exception.BusinessException;
import com.bank.model.User;
	
public interface UserDAO {
	
	public int createUserAccount(User newUser) throws BusinessException;
	public void insertUserId(User user) throws BusinessException;
	public boolean checkIfUserExists(String username) throws BusinessException;
	public boolean checkPassword(String username, String password) throws BusinessException;
	public boolean checkIfCustomer(String username) throws BusinessException;
	public User getUserFromUsername(String username) throws BusinessException;

}