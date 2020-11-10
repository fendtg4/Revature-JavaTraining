package com.bank.service;

import java.math.BigDecimal;

import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Transaction;

public interface BankAccountService {

	public BigDecimal viewBalance(int accountId) throws BusinessException;
	public Account getAccountFromAccountId(int accountId) throws BusinessException;
	public int deposit(int accountId, BigDecimal depositAmount) throws BusinessException;
	public int withdraw(int accountId, BigDecimal withdrawalAmount) throws BusinessException;
	public int transfer(int accountIdSender, Transaction transferTransaction) throws BusinessException;
}
