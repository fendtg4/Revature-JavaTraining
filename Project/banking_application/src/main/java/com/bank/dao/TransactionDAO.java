package com.bank.dao;

import com.bank.exception.BusinessException;
import com.bank.model.Transaction;

public interface TransactionDAO {

	public int transfer(int accountIdSender, Transaction transferTransaction) throws BusinessException;
	public boolean checkIfAccountInTransactions(int accountId) throws BusinessException;
	public void updateStatusAfterMoneySent(int accountId) throws BusinessException;
	public boolean checkIfAccountApproved(int accountId) throws BusinessException;
	public void updateAccountStatus(int accountId, String string) throws BusinessException;
	public void createNewTransaction(Transaction transaction) throws BusinessException;
	public boolean getAllTransactions() throws BusinessException;
}
