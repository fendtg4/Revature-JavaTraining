package com.bank.dao;

import com.bank.exception.BusinessException;
import com.bank.model.Transaction;

public interface TransactionDAO {

	public int transfer(int accountIdSender, Transaction transferTransaction) throws BusinessException;
}
