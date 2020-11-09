package com.bank.service;

import com.bank.exception.BusinessException;
import com.bank.model.User;
public interface UserAccountCreationService {

	public int createUserAccount(User newUser) throws BusinessException;
	public void insertUserId(User user) throws BusinessException;
}
