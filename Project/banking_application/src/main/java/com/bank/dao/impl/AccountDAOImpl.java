package com.bank.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.AccountDAO;
import com.bank.dao.dbutil.BankAccountCreationQueries;
import com.bank.dao.dbutil.CustomerAccountCreationQueries;
import com.bank.dao.dbutil.PostgresSqlConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Customer;

public class AccountDAOImpl implements AccountDAO {
	
	private static Logger log = Logger.getLogger(AccountDAOImpl.class);
			
	@Override
	public int createBankAccount(Customer customer, BigDecimal startingBalance) throws BusinessException {
		int accountCreationSuccess = 0;
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountCreationQueries.INSERTNEWBANKACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1, startingBalance);
			preparedStatement.setInt(2, customer.getCustomerId());
			accountCreationSuccess = preparedStatement.executeUpdate();
			return accountCreationSuccess;
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			
		}
		
		return 0;
	}

	
}
