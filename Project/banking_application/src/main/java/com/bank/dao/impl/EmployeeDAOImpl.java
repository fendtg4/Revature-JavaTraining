package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.EmployeeDAO;
import com.bank.dao.dbutil.EmployeeAccountCreationQueries;
import com.bank.dao.dbutil.EmployeeVerificationQueries;
import com.bank.dao.dbutil.PostgresSqlConnection;
import com.bank.exception.BusinessException;
import com.bank.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO{

	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);
	
	public int createEmployeeAccount(Employee newEmployee) throws BusinessException {
		int accountCreationSuccess = 0;
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = EmployeeAccountCreationQueries.INSERTNEWEMPLOYEEACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newEmployee.getUsername());
			preparedStatement.setString(2, newEmployee.getPassword());
			preparedStatement.setString(3, newEmployee.getFirstName());
			preparedStatement.setString(4, newEmployee.getLastName());
			preparedStatement.setString(5, newEmployee.getEmail());
			preparedStatement.setInt(6, newEmployee.getUserId());
			accountCreationSuccess = preparedStatement.executeUpdate();
			if (accountCreationSuccess == 1) {
				return accountCreationSuccess;
			}
			else {
				throw new BusinessException("Error, a employee account was not created!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("An internal error occurred! Please contact a system administrator");
			
		}
		
	}

	@Override
	public boolean checkIfEmployeeAccountExists(String username) throws BusinessException {
			
			try (Connection connection = PostgresSqlConnection.getConnection()) {
					
					String sql = EmployeeVerificationQueries.VERIFYUSERNAME;
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, username);
					ResultSet resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) {
						return true;
					}
				} catch (ClassNotFoundException | SQLException e) {
					throw new BusinessException("An internal error occurred! Please contact a system administrator");
				}
				return false;
	}
}
