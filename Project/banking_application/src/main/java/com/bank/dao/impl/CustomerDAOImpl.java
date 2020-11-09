package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.CustomerDAO;
import com.bank.dao.dbutil.CustomerAccountCreationQueries;
import com.bank.dao.dbutil.CustomerVerificationQueries;
import com.bank.dao.dbutil.PostgresSqlConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	private static Logger log = Logger.getLogger(CustomerDAOImpl.class);
	@Override
	public boolean checkIfCustomerAccountExists(String username) throws BusinessException {
		
	try (Connection connection = PostgresSqlConnection.getConnection()) {
			
			String sql = CustomerVerificationQueries.VERIFYUSERNAME;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
		}
		return false;
	}
	@Override
	public int createCustomerAccount(Customer newCustomer) throws BusinessException {
		int accountCreationSuccess = 0;
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = CustomerAccountCreationQueries.INSERTNEWCUSTOMERACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newCustomer.getUsername());
			preparedStatement.setString(2, newCustomer.getPassword());
			log.debug(newCustomer.getFirstName());
			preparedStatement.setString(3, newCustomer.getFirstName());
			preparedStatement.setString(4, newCustomer.getLastName());
			preparedStatement.setString(5, newCustomer.getEmail());
			preparedStatement.setInt(6, newCustomer.getUserId());
			accountCreationSuccess = preparedStatement.executeUpdate();
			return accountCreationSuccess;
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			
		}
		
		return 0;
	}
	@Override
	public void insertCustomerId(Customer customer) throws BusinessException {
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = CustomerAccountCreationQueries.INSERTCUSTOMERID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer.setCustomerId(resultSet.getInt("customer_id"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e);
			}
		
		
	}


	
}
