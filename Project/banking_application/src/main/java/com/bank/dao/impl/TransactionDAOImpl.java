package com.bank.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.TransactionDAO;
import com.bank.dao.dbutil.BankAccountServiceQueries;
import com.bank.dao.dbutil.PostgresSqlConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Transaction;

public class TransactionDAOImpl implements TransactionDAO {

	private static Logger log = Logger.getLogger(TransactionDAOImpl.class);
	
	public int transfer(int accountIdSender, Transaction transferTransaction) throws BusinessException {
		
		int requestSuccessful = 0;
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountServiceQueries.CREATETRANSFERREQUEST;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,transferTransaction.getTransactionDescription());
			preparedStatement.setTimestamp(2, transferTransaction.getTransactionTime());
			preparedStatement.setInt(3, transferTransaction.getAccountId());
			preparedStatement.setString(4, transferTransaction.getStatus());
			requestSuccessful = preparedStatement.executeUpdate(); 
			if (requestSuccessful ==1) {
				return requestSuccessful;
			}
			else {
				throw new BusinessException("Error, transaction request unsuccessful.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);//Take off when finished
			throw new BusinessException("An internal error occured! Please contact a system administrator");
		}

	}
}
