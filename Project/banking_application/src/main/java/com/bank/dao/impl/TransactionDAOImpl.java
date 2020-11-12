package com.bank.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.TransactionDAO;
import com.bank.dao.dbutil.BankAccountServiceQueries;
import com.bank.dao.dbutil.CustomerVerificationQueries;
import com.bank.dao.dbutil.PostgresSqlConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Transaction;

public class TransactionDAOImpl implements TransactionDAO {

	private static Logger log = Logger.getLogger(TransactionDAOImpl.class);
	
	
	@Override
	public boolean checkIfAccountInTransactions(int accountId) throws BusinessException {
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
				
				String sql = BankAccountServiceQueries.CHECKIFACCOUNTINTRANSACTIONS;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, accountId);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					return true;
				}
				else {
					return false;
				}
			} catch (ClassNotFoundException | SQLException e) {
				throw new BusinessException("An internal error occurred! Please contact a system administrator");
			}

	}
	
	@Override
	public void updateAccountStatus(int accountId, String string) throws BusinessException {
		
	try (Connection connection = PostgresSqlConnection.getConnection()) {
			
			String sql = BankAccountServiceQueries.EMPLOYEEUPDATESTATUS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, string);
			preparedStatement.setInt(2, accountId);
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}
	}
	
	@Override
	public boolean checkIfAccountApproved(int accountId) throws BusinessException {
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			
			String status = "APPROVED";
			String sql = BankAccountServiceQueries.CHECKIFACCOUNTAPPROVED;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (status.equals(resultSet.getString("status"))) {
					return true;
				}
			}
			else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}
		return false;
	}
	
	@Override
	public int transfer(int accountIdSender, Transaction transferTransaction) throws BusinessException {
		
		int requestSuccessful = 0;
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountServiceQueries.CREATETRANSACTION;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1, transferTransaction.getTransactionAmount());
			preparedStatement.setString(2,transferTransaction.getTransactionDescription());
			preparedStatement.setTimestamp(3, transferTransaction.getTransactionTime());
			preparedStatement.setInt(4, transferTransaction.getAccountId());
			preparedStatement.setString(5, transferTransaction.getStatus());
			requestSuccessful = preparedStatement.executeUpdate(); 
			if (requestSuccessful ==1) {
				return requestSuccessful;
			}
			else {
				throw new BusinessException("Error, transaction request unsuccessful.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}

	}
	
	@Override
	public void updateStatusAfterMoneySent(int accountId) throws BusinessException {
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			
			String sql = BankAccountServiceQueries.UPDATESTATUSAFTERMONEYSENT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}

	}

	@Override
	public void createNewTransaction(Transaction transaction) throws BusinessException {
		
		
		int requestSuccessful = 0;
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = BankAccountServiceQueries.CREATETRANSACTION;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBigDecimal(1, transaction.getTransactionAmount());
			preparedStatement.setString(2,transaction.getTransactionDescription());
			preparedStatement.setTimestamp(3, transaction.getTransactionTime());
			preparedStatement.setInt(4, transaction.getAccountId());
			preparedStatement.setString(5, transaction.getStatus());
			requestSuccessful = preparedStatement.executeUpdate(); 
			if (requestSuccessful ==1) {
				return;
			}
			else {
				throw new BusinessException("Error, request to create transaction for the new account was unsuccessful.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}

	}

	@Override
	public boolean getAllTransactions() throws BusinessException {
		
		int counter = 0;
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			
			String sql = BankAccountServiceQueries.GETALLTRANSACTIONS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				counter++;
				log.info("Transaction ID: " + resultSet.getInt("transaction_id") + "  Transaction Amount: " + resultSet.getBigDecimal("transaction_amount") +  
						" Transaction Time: " + resultSet.getTimestamp("transaction_time") + " Account ID: " + resultSet.getInt("account_id") + " Status: " + resultSet.getString("status"));
			}
			if (counter>0) {
				return true;
			}
			else {
				throw new BusinessException("There are currently no transactions.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
		}

	}
		
		

	
}
