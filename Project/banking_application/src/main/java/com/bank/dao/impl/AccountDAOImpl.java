package com.bank.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.AccountDAO;
import com.bank.dao.dbutil.BankAccountCreationQueries;
import com.bank.dao.dbutil.BankAccountServiceQueries;
import com.bank.dao.dbutil.PostgresSqlConnection;
import com.bank.dao.dbutil.UserVerificationQueries;
import com.bank.exception.BusinessException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Transaction;

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
			if (accountCreationSuccess==1) {
				return accountCreationSuccess;
			}
			else {
				throw new BusinessException("Error, account was not created!");
			}
		} 	catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
			
		}
		
	}
	
	//Gets the account ID from the most recent account created by customer from database
	@Override
	public int getAccountId(int customerId) throws BusinessException {
		
		int accountId = 0;
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountCreationQueries.GETACCOUNTID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,  customerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				accountId = resultSet.getInt("account_id");
			} 
			else {
				throw new BusinessException("There was no account with that customer ID!");
			}
			return accountId;
		}	catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}
		
	}
	@Override
	public BigDecimal viewBalance(int accountId) throws BusinessException {
		
		BigDecimal balance = BigDecimal.ZERO;
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountServiceQueries.GETBALANCE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,  accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				balance = resultSet.getBigDecimal("balance");
				return balance;
			}
			else {
				throw new BusinessException("There was no account with that account ID!");
			}
		}	catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}
		
		
	}
	@Override
	public Account getAccountFromAccountId(int accountId) throws BusinessException {
		
		BigDecimal balance = BigDecimal.ZERO;
		int customerId = 0;
		Account account = null;
	
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountServiceQueries.GETACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,  accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				balance = resultSet.getBigDecimal("balance");
				customerId = resultSet.getInt("customer_id");
				account = new Account(accountId, balance, customerId);
				return account;
			}
			else {
				throw new BusinessException("There was no account with that account ID!");
			}
		} 	catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}
		
		
	}
	@Override
	public int deposit(int accountId, BigDecimal depositAmount, BigDecimal currentBalance) throws BusinessException {
		
		int depositSuccessful = 0;
		
		BigDecimal totalBalance = depositAmount.add(currentBalance);
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountServiceQueries.UPDATEACCOUNTBALANCE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1, totalBalance);
			preparedStatement.setInt(2, accountId);
			depositSuccessful = preparedStatement.executeUpdate(); 
			if (depositSuccessful ==1) {
				return depositSuccessful;
			}
			else {
				throw new BusinessException("Error, deposit failed.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}

	}
	@Override
	public int withdraw(int accountId, BigDecimal withdrawalAmount, BigDecimal currentBalance) throws BusinessException {
		
		int withdrawalSuccessful = 0;
	
		BigDecimal totalBalance = currentBalance.subtract(withdrawalAmount);
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountServiceQueries.UPDATEACCOUNTBALANCE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1, totalBalance);
			preparedStatement.setInt(2, accountId);
			withdrawalSuccessful = preparedStatement.executeUpdate(); 
			if (withdrawalSuccessful ==1) {
				return withdrawalSuccessful;
			}
			else {
				throw new BusinessException("Error, withdrawal failed.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}

	}
	@Override
	public boolean checkIfAccountExists(int accountId) throws BusinessException {
		
			try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountServiceQueries.VERIFYACCOUNTID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}
		return false;
		
	}
	@Override
	public boolean checkIfAccountBelongsToCustomer(int accountId, int customerId) throws BusinessException {
		
			try (Connection connection = PostgresSqlConnection.getConnection()) {
			
			String sql = BankAccountServiceQueries.CHECKIFACCOUNTBELONGSTOCUSTOMER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int customerIdToCheck = resultSet.getInt("customer_id");
				if (customerId == customerIdToCheck) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				throw new BusinessException("Error occurred when trying to find account with this account ID!");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}

	}
	@Override
	public BigDecimal receiveTransfer(int accountId) throws BusinessException {
		

		BigDecimal amountReceived = BigDecimal.ZERO;
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountServiceQueries.RECEIVETRANSACTIONS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,  accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				amountReceived = amountReceived.add(resultSet.getBigDecimal("transaction_amount"));
			}

			return amountReceived;
			
		}	catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}
			
	}
	@Override
	public void getAccountsFromCustomerId(int customerId) throws BusinessException {
		
		int accountId = 0;
		BigDecimal balance =  BigDecimal.ZERO;
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountServiceQueries.GETACCOUNTSFROMCUSTOMERID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,  customerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				accountId = resultSet.getInt("account_id");
				balance = resultSet.getBigDecimal("balance");
				log.info("Account ID: " + accountId + " Balance: " + balance + "\n");
			}
			
		}	catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}
		
	}
	
	
}

