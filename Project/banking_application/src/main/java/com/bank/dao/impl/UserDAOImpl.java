package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.bank.dao.UserDAO;
import com.bank.dao.dbutil.PostgresSqlConnection;
import com.bank.dao.dbutil.UserAccountCreationQueries;
import com.bank.dao.dbutil.UserVerificationQueries;
import com.bank.exception.BusinessException;
import com.bank.model.User;

public class UserDAOImpl implements UserDAO {

	private static Logger log = Logger.getLogger(UserDAOImpl.class);
	
	@Override
	public int createUserAccount(User newUser) throws BusinessException {
		
		int accountCreationSuccess = 0;
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = UserAccountCreationQueries.INSERTNEWUSERACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newUser.getUsername());
			preparedStatement.setString(2, newUser.getPassword());
			preparedStatement.setString(3, newUser.getFirstName());
			preparedStatement.setString(4, newUser.getLastName());
			preparedStatement.setString(5, newUser.getEmail());
			preparedStatement.setString(6, newUser.getUserType());
			accountCreationSuccess = preparedStatement.executeUpdate();
			if (accountCreationSuccess ==1) {
				return accountCreationSuccess;
			}
			else {
				throw new BusinessException("Error, a user account could not be created!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);//Take off when finished
			throw new BusinessException("An internal error occured! Please contact a system administrator");
			
		}
		
		
	}

	@Override
	public boolean checkIfUserExists(String username) throws BusinessException {
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			
			String sql = UserVerificationQueries.VERIFYUSERNAME;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);//Take off when finished
			throw new BusinessException("An internal error occured! Please contact a system administrator");
		}
		return false;
	}

	@Override
	public boolean checkPassword(String username, String password) throws BusinessException {
		
	
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			
			String sql = UserVerificationQueries.VERIFYPASSWORD;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);//Take off when finished
			throw new BusinessException("An internal error occured! Please contact a system administrator");
		}
		return false;
	}
	
	//Returns true if user is customer and false is user is employee
	@Override
	public boolean checkIfCustomer(String username) throws BusinessException {
		

		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = UserVerificationQueries.CHECKUSERTYPE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getString(1).equals("Customer")) {
					return true;
				}
			}
			
		
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);//Take off when finished
			throw new BusinessException("An internal error occured! Please contact a system administrator");
		}
		return false;
	}

	@Override
	public User getUserFromUsername(String username) throws BusinessException {
		
		User user = null;
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = UserVerificationQueries.GETUSERFROMUSERNAME;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User(resultSet.getString("username"),  resultSet.getString("password"),
						resultSet.getString("first_name"),resultSet.getString("last_name"), resultSet.getString("email"), 
						resultSet.getString("user_type"));
				resultSet.getInt("user_id");
				return user;
			}
			else {
				throw new BusinessException("Error, a user could not be retrieved from this username!");
			}
		}  catch (ClassNotFoundException | SQLException e) {
			log.error(e);//Take off when finished
			throw new BusinessException("An internal error occured! Please contact a system administrator");
		}
	
	}

	//inserts user id from database into user object
	@Override
	public void insertUserId(User user) throws BusinessException {
		
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = UserAccountCreationQueries.INSERTUSERID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user.setUserId(resultSet.getInt("user_id"));
			}
			else {
				throw new BusinessException("Error retrieving information from the database!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);//Take off when finished
			throw new BusinessException("An internal error occured! Please contact a system administrator");
			}
		
	}
}
