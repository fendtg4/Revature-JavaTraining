package com.bank.service;

import java.math.BigDecimal;

import com.bank.exception.BusinessException;
import com.bank.model.Customer;

public interface BankAccountCreationService {

	public int createBankAccount(Customer customer, BigDecimal startingBalance) throws BusinessException;
}
