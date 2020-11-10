package com.bank.main;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.exception.BusinessException;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.service.BankAccountCreationService;
import com.bank.service.BankAccountService;
import com.bank.service.CustomerAccountCreationService;
import com.bank.service.CustomerVerificationService;
import com.bank.service.UserAccountCreationService;
import com.bank.service.UserVerificationService;
import com.bank.service.impl.BankAccountCreationServiceImpl;
import com.bank.service.impl.BankAccountServiceImpl;
import com.bank.service.impl.CustomerAccountCreationServiceImpl;
import com.bank.service.impl.CustomerVerificationServiceImpl;
import com.bank.service.impl.UserAccountCreationServiceImpl;
import com.bank.service.impl.UserVerificationServiceImpl;
public class UserMain {
	
	private static Logger log = Logger.getLogger(UserMain.class);

	public static void main(String[] args) {
		
		UserAccountCreationService userAccountCreationService = new UserAccountCreationServiceImpl();
		UserVerificationService userVerificationService = new UserVerificationServiceImpl();
		CustomerAccountCreationService customerAccountCreationService = new CustomerAccountCreationServiceImpl();
		CustomerVerificationService customerVerificationService = new CustomerVerificationServiceImpl();
		BankAccountCreationService bankAccountCreationService = new BankAccountCreationServiceImpl();
		BankAccountService bankAccountService = new BankAccountServiceImpl();
		Scanner scanner = new Scanner(System.in);
		String username;
		String password;
		String first_name;
		String last_name;
		String email;
		BigDecimal startingBalance = BigDecimal.ZERO;
		BigDecimal balance = BigDecimal.ZERO;
		int accountId = 0;
		LocalDateTime ldt = LocalDateTime.now();
		Timestamp ts = Timestamp.valueOf(ldt);
		//um stands for user menu
		int um = 0;
		log.info("Welcome to Super Bank!");
		
	
		//User Menu
		do {
			log.info("----------------------------------------------------------------------");
			log.info("Welcome to the Main Menu! Please select one of the options below.\n");
			log.info("1) Enter 1 if you are a new user to Super Bank.");
			log.info("2) Enter 2 if you are a returning user.");
			log.info("3) Enter 3 to exit the Super Bank application.");
			try {
				um = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				//Enters 0 so the user stays in the user menu
				um = 0;
			}
			switch (um) {
				case 1:
					
					int userTypeInput = 0;
					int userAccountCreationSuccess = 0;
					String userType;
					log.info("You must create an account to use the banking system. Please enter a username:");
					
					username = scanner.nextLine();
						
					log.info("Please enter a password for this account:");
					password = scanner.nextLine();
					
					log.info("Please enter your first name:");
					first_name = scanner.nextLine();
					
					log.info("Please enter your last name:");
					last_name = scanner.nextLine();
					
					log.info("Please enter your email address:");
					email = scanner.nextLine();
					while (!(userTypeInput==1 || userTypeInput==2)) {
						try {
							log.info("Are you a customer or employee? Enter 1 for customer or 2 for employee.");
							userTypeInput = Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException e) {
							log.warn("Please input 1 or 2");
						}
					}
					if (userTypeInput==1) {
						userType = "Customer";
					}
					else {
						userType = "Employee";
					}
					
					User newUser = new User(username, password, first_name, last_name, email, userType);
					try {
						userAccountCreationSuccess = userAccountCreationService.createUserAccount(newUser);
						if (userAccountCreationSuccess==1) {
							log.info("Congratulations. Your account has been made. Please proceed to enter 2 in the main menu to log in.\n");
						}
						else {
							log.error("Sorry, an error occurred while creating your account. Please try again.");
						}
						
					} catch(BusinessException e) {
						log.error(e.getMessage());
					}
					break;
				case 2:
					boolean usernameFound = false;
					boolean loginSuccessful = false;

					log.info("Please enter your username:");
					username = scanner.nextLine();
					
					while (!(usernameFound)) {
						try {
							if (userVerificationService.checkIfUserExists(username)) {
								usernameFound = true;
							} 
							else if (username.toLowerCase().equals("q")) {
								break;
							}
							else {
								log.info("Username cannot be found. Please re-enter your username, or enter q to quit");
								username = scanner.nextLine();
							}
							
						} catch(BusinessException e) {
							log.error(e.getMessage());
						}
					
					}
					//exits to main menu if user quit
					if (username.toLowerCase().equals("q")) {
						break;
					}
					
					log.info("Please enter your password:");
					password = scanner.nextLine();
					
					while (!(loginSuccessful)) {
						try {
							if (userVerificationService.checkPassword(username, password)) {
								loginSuccessful = true;
							}
							else if (password.toLowerCase().equals("q")) {
								break;
							}
							else {
								log.info("The password entered did not match your username. Please re-enter your password, or enter q to quit.");
								password = scanner.nextLine();
							}
							
						} catch(BusinessException e) {
							log.error(e.getMessage());
						}
					}
					
					//exits to main menu if user quit
					if (password.toLowerCase().equals("q")) {
						break;
					}
					
					try {
						if (userVerificationService.checkIfCustomer(username)) {
							
							//checks if users of type customer do not have a customer account yet, and creates one if necessary
							if (!(customerVerificationService.checkIfCustomerExists(username))) {
								User user = userVerificationService.getUserFromUsername(username);
								userAccountCreationService.insertUserId(user);
								Customer newCustomer = new Customer(user);
								customerAccountCreationService.createCustomerAccount(newCustomer);
								customerAccountCreationService.insertCustomerId(newCustomer);

							}
							
						}
					} catch(BusinessException e) {
						log.error(e.getMessage());
					}
					
					//Customer Menu
					try {
						
						if (userVerificationService.checkIfCustomer(username)) {
							
						
						int cm = 0;
						do {
							
							log.info("===================================================================");
							log.info("Welcome to the Customer Menu! What would you like to do today?\n");
							log.info("1) Apply for a new Bank Account.");
							log.info("2) View the balance of an existing account.");
							log.info("3) Make a deposit or withdrawal to an existing account.");
							log.info("4) Transfer money to another account");
							log.info("5) Accept a money transfer");
							log.info("6) Exit the customer menu.");
							try {
								cm = Integer.parseInt(scanner.nextLine());
							} catch (NumberFormatException e) {
								//Enters 0 so the customer stays in the customer menu
								cm = 0; 
							}
							switch (cm) {
									
								case 1:
									accountId = 0;
									startingBalance = BigDecimal.ZERO;
									log.info("You have applied for a new account. Please wait for an employee's approval before you can use the account");
									
									//Code employee approval in employee menu as a condition for making a new account
									
									log.info("How much money would you like to deposit into the new account? Please enter the amount: ");
									while (startingBalance.compareTo(BigDecimal.ZERO)<=0) {
										try { 
									
										startingBalance = new BigDecimal(scanner.nextLine());
										//if starting balance is negative asks for input again
										while (startingBalance.compareTo(BigDecimal.ZERO)<=0) {
											log.info("Please input a positive amount");
											startingBalance = new BigDecimal(scanner.nextLine());
											
										}
									}  catch (NumberFormatException e) {
										log.warn("Please input a number.");
									} 
									}
									try {
										User user = userVerificationService.getUserFromUsername(username);
										Customer customer = new Customer(user);
										customerAccountCreationService.insertCustomerId(customer);
										bankAccountCreationService.createBankAccount(customer, startingBalance);
										accountId = bankAccountCreationService.getAccountId(customer.getCustomerId());
										log.info("Your bank account with a starting balance of " + startingBalance + " was successfully created! "
												+ "Your account ID is " + accountId + ".\n");
									} catch(BusinessException e) {
										log.error(e.getMessage());
									}
									break;
									
								case 2:
									accountId = 0;
									balance = BigDecimal.ZERO;
									log.info("Please enter the account ID for the account you wish to view.");
									while (accountId<=0) {
										try {
									
										accountId = Integer.parseInt(scanner.nextLine());
										while (accountId<=0) {
											log.info("Please input a positive ID");
											accountId = Integer.parseInt(scanner.nextLine());
										}
										
									} catch (NumberFormatException e) {
										log.error("Please input a number!");
									} 
									}
									try {
										balance = bankAccountService.viewBalance(accountId);
										if (balance.compareTo(BigDecimal.ZERO)>=0) {
											log.info("The balance for account with ID " + accountId + " is " + balance);
										}
									} catch(BusinessException e) {
										
										log.error(e.getMessage());
									}
									
									break;
									
								case 3:
									accountId = 0;
									balance = BigDecimal.ZERO;
									int dOrW = 0;
									BigDecimal depositAmount = BigDecimal.ZERO;
									BigDecimal withdrawalAmount = BigDecimal.ZERO;
									log.info("Please enter the account ID for the account you wish to make a transaction.");
									while (accountId<=0) {
										try {
									
										accountId = Integer.parseInt(scanner.nextLine());
										while (accountId<=0) {
											log.info("Please input a positive ID");
											accountId = Integer.parseInt(scanner.nextLine());
										}
										}
									 catch (NumberFormatException e) {
										log.error("Please input a number!");
										} 
									}
									log.info("Do you wish to make a deposit or withdrawal? Enter 1 for a deposit or 2 for a withdrawal.");
									
									while (dOrW!=1 && dOrW!=2 ) {
										try {
									
										dOrW = Integer.parseInt(scanner.nextLine());
										while (dOrW!=1 && dOrW!=2 ) {
											log.info("Please input either 1 for deposit or 2 for withdrawal.");
											dOrW = Integer.parseInt(scanner.nextLine());
										} 
									} catch (NumberFormatException e) {
										log.error("Please input a number!");
									} 
									}
									if (dOrW==1) {
										while (depositAmount.compareTo(BigDecimal.ZERO)<=0) {
											try {
												log.info("How much would you like to deposit?");
												depositAmount = new BigDecimal(scanner.nextLine());
												while (depositAmount.compareTo(BigDecimal.ZERO)<=0) {
													log.warn("Please deposit a positive amount");
													depositAmount = new BigDecimal(scanner.nextLine());
												}
											}  catch (NumberFormatException e) {
												log.warn("Please input a number!");
											} 
										}
										try {
											
											if (bankAccountService.deposit(accountId, depositAmount)==1) {
												log.info("Successfully deposited! Your new balance is " + bankAccountService.viewBalance(accountId) +".\n");
											}
											else {
												log.info("An error occurred while depositing.");
											};
										} catch(BusinessException e) {
											log.error(e.getMessage());
										}
									}
									else {
										while (withdrawalAmount.compareTo(BigDecimal.ZERO)<=0) {
											try {
												log.info("How much would you like to withdraw?");
												withdrawalAmount = new BigDecimal(scanner.nextLine());
												while (withdrawalAmount.compareTo(BigDecimal.ZERO)<=0) {
													log.warn("Please withdraw a positive amount");
													withdrawalAmount = new BigDecimal(scanner.nextLine());
												}
											}  catch (NumberFormatException e) {
												log.warn("Please input a number!");
											} 
										}
										try {
											
											if (bankAccountService.withdraw(accountId, withdrawalAmount)==1) {
												log.info("Successfully withdrew! Your new balance is " + bankAccountService.viewBalance(accountId) +".\n");
											}
										} catch(BusinessException e) {
											log.error(e.getMessage());
										}
									}
									
									break;
									
								case 4:
									int accountIdSender= 0;
									int accountIdReceiver = 0;
									balance = BigDecimal.ZERO;
									BigDecimal transferAmount = BigDecimal.ZERO;
									Transaction transaction = null;
									
									log.info("Please enter the account ID for the account that is going to send the money to another account.");
									while (accountIdSender<=0) {
										try {
									
										accountIdSender = Integer.parseInt(scanner.nextLine());
										while (accountIdSender<=0) {
											log.info("Please input a positive ID");
											accountIdSender = Integer.parseInt(scanner.nextLine());
										}
										}
										catch (NumberFormatException e) {
										 log.error("Please input a number!");
										} 
									}	
									
									log.info("Please enter the account ID for the account that is going to receive the money");
									while (accountIdReceiver<=0) {
										try {
										
										accountIdReceiver = Integer.parseInt(scanner.nextLine());
										while (accountIdReceiver<=0) {
											log.info("Please input a positive ID");
											accountIdReceiver = Integer.parseInt(scanner.nextLine());
										}
										}	 catch (NumberFormatException e) {
												log.error("Please input a number!");
												} 
									}
									
									while (accountIdReceiver==accountIdSender) {
										
										try {
											log.warn("Please enter a different account to send to.");
											accountIdReceiver = Integer.parseInt(scanner.nextLine());
										} catch (NumberFormatException e) {
											log.error("Please input a number!");
											} 
									}
									
									while (transferAmount.compareTo(BigDecimal.ZERO)<=0) {
										try {
											log.info("How much would you like to send?");
											transferAmount = new BigDecimal(scanner.nextLine());
											while (transferAmount.compareTo(BigDecimal.ZERO)<=0) {
												log.warn("Please send a positive amount");
												transferAmount = new BigDecimal(scanner.nextLine());
											}
										}  catch (NumberFormatException e) {
											log.warn("Please input a number!");
										} 
									}
									
									String transactionDescription = "Account no " + accountIdSender + " is sending " + transferAmount + " to account " + accountIdReceiver + ".";
									
									try {
										//withdraws from sender bank
										bankAccountService.withdraw(accountIdSender, transferAmount);
										//creates transaction object to send transaction
										transaction = new Transaction(transferAmount, transactionDescription, ts, accountIdReceiver, "PENDING");
										if (bankAccountService.transfer(accountIdSender, transaction)==1) {
											log.info("Sent a transfer request! Your new balance is " + bankAccountService.viewBalance(accountIdSender) +".\n");
										}
									} catch(BusinessException e) {
										log.error(e.getMessage());
									}
									break;
									
								case 5:
									
									break;
									
								case 6:
									log.info("I hope our service was satisfactory!");
									break;
									
								default:
									log.info("\nPlease enter a number between 1-6 only!\n");
									break;
							}
						} while (cm!=6);
						}
					}  catch(BusinessException e) {
						log.warn(e.getMessage());
					}
						
					//Code employee menu here
					
					
						break;
						
					case 3: 
						log.info("Thank you for using Super Bank!");
						
						break;
					default:
						log.info("\nPlease enter 1, 2, or 3 only!\n");
						break;
					}
				
			} while (um!=3);
		scanner.close();
	}

}
