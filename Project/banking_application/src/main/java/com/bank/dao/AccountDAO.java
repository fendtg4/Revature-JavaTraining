package com.bank.dao;

import java.math.BigDecimal;

import com.bank.exception.BusinessException;
import com.bank.model.Customer;

public interface AccountDAO {
	public int createBankAccount(Customer customer, BigDecimal startingBalance) throws BusinessException;
}
