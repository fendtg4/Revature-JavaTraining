package com.bank.main;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import com.bank.exception.BusinessException;
import com.bank.model.Customer;
import com.bank.model.Employee;
import com.bank.model.Transaction;
import com.bank.model.User;
import com.bank.service.BankAccountCreationService;
import com.bank.service.BankAccountService;
import com.bank.service.CustomerAccountCreationService;
import com.bank.service.CustomerVerificationService;
import com.bank.service.EmployeeAccountCreationService;
import com.bank.service.EmployeeVerificationService;
import com.bank.service.UserAccountCreationService;
import com.bank.service.UserVerificationService;
import com.bank.service.impl.BankAccountCreationServiceImpl;
import com.bank.service.impl.BankAccountServiceImpl;
import com.bank.service.impl.CustomerAccountCreationServiceImpl;
import com.bank.service.impl.CustomerVerificationServiceImpl;
import com.bank.service.impl.EmployeeAccountCreationServiceImpl;
import com.bank.service.impl.EmployeeVerificationServiceImpl;
import com.bank.service.impl.UserAccountCreationServiceImpl;
import com.bank.service.impl.UserVerificationServiceImpl;
public class UserMain {
	
	private static Logger log = Logger.getLogger(UserMain.class);

	public static void main(String[] args) {
		
		UserAccountCreationService userAccountCreationService = new UserAccountCreationServiceImpl();
		UserVerificationService userVerificationService = new UserVerificationServiceImpl();
		CustomerAccountCreationService customerAccountCreationService = new CustomerAccountCreationServiceImpl();
		CustomerVerificationService customerVerificationService = new CustomerVerificationServiceImpl();
		EmployeeAccountCreationService employeeAccountCreationService = new EmployeeAccountCreationServiceImpl();
		EmployeeVerificationService employeeVerificationService = new EmployeeVerificationServiceImpl();
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
				//Sets user menu integer to 0 so the default case is activated
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
							if (!(customerVerificationService.checkIfCustomerAccountExists(username))) {
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
					
					try {
						if (!userVerificationService.checkIfCustomer(username)) {
							
							//checks if users of type employee do not have an employee account yet, and creates one if necessary
							if (!(employeeVerificationService.checkIfEmployeeAccountExists(username))) {
								User user = userVerificationService.getUserFromUsername(username);
								userAccountCreationService.insertUserId(user);
								Employee newEmployee = new Employee(user);
								employeeAccountCreationService.createEmployeeAccount(newEmployee);
							
							}
							
						}
					} catch(BusinessException e) {
						log.error(e.getMessage());
					}
					
					//Customer Menu
					try {
						
						//tests if user is a customer
						if (userVerificationService.checkIfCustomer(username)) {
							
							//gets customer id from customer username
							int customerId = 0;
							try {
								customerId = customerVerificationService.getCustomerIdFromUsername(username);
							} catch (BusinessException e) {
								log.error(e.getMessage());
							}
							
						
							//used for switch cases in customer menu
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
									//Activates the default case so the customer stays in the customer menu
									cm = 0; 
								}
								
								
								
								switch (cm) {
										
									case 1:
										
										accountId = 0;
										startingBalance = BigDecimal.ZERO;
										BigDecimal minimumAmount = new BigDecimal("25");
										
										log.info("How much money would you like to deposit into the new account? The minimum amount is 25. Please enter the amount: ");
										while (startingBalance.compareTo(minimumAmount)<0) {
											try { 
										
											startingBalance = new BigDecimal(scanner.nextLine());
											//if starting balance is negative asks for input again
											while (startingBalance.compareTo(minimumAmount)<0) {
												log.info("Please input at least 25.");
												startingBalance = new BigDecimal(scanner.nextLine());
												
											}
										}  catch (NumberFormatException e) {
											log.warn("Please input a number.");
											} 
										}
										
										String creationTransactionDescription = "Customer no " + customerId + " is requesting to create an account";
									
										try {
											User user = userVerificationService.getUserFromUsername(username);
											Customer customer = new Customer(user);
											customerAccountCreationService.insertCustomerId(customer);
											bankAccountCreationService.createBankAccount(customer, startingBalance);
											accountId = bankAccountCreationService.getAccountId(customer.getCustomerId());
											Transaction creationTransaction = new Transaction(startingBalance, creationTransactionDescription, Timestamp.valueOf(LocalDateTime.now()), accountId, "PENDING");
											bankAccountService.createNewTransaction(creationTransaction);
											log.info("Your bank account with a starting balance of " + startingBalance + " was created. "
													+ "Your account ID is " + accountId + ".\n Please wait for an employee's approval "
															+ "before you can use your account further.\n");
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
											if (!bankAccountService.checkIfAccountApproved(accountId, customerId)) {
												log.info("Sorry, cannot proceed until your account is approved.");
												break;
											};
										} catch(BusinessException e) {
											
											log.error(e.getMessage());
											break;
										}
										
										
										try {
											balance = bankAccountService.viewBalance(accountId, customerId);
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
										
										try {
											if (!bankAccountService.checkIfAccountApproved(accountId, customerId)) {
												log.info("Sorry, cannot proceed until your account is approved.");
												break;
											};
										} catch(BusinessException e) {
											
											log.error(e.getMessage());
											break;
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
												
												if (bankAccountService.deposit(accountId, depositAmount, customerId)==1) {
													log.info("Successfully deposited! Your new balance is " + bankAccountService.viewBalance(accountId, customerId) +".\n");
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
												
												if (bankAccountService.withdraw(accountId, withdrawalAmount, customerId)==1) {
													log.info("Successfully withdrew! Your new balance is " + bankAccountService.viewBalance(accountId, customerId) +".\n");
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
										
										try {
											if (!bankAccountService.checkIfAccountApproved(accountIdSender, customerId)) {
												log.info("Sorry, cannot proceed until your account is approved.");
												break;
											};
										} catch(BusinessException e) {
											log.error(e.getMessage());
											break;
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
										
										String transferTransactionDescription = "Account no " + accountIdSender + " is sending " + transferAmount + " to account no " + accountIdReceiver + ".";
										
										try {
											//withdraws from sender bank
											bankAccountService.withdraw(accountIdSender, transferAmount, customerId);
											//creates transaction object to send transaction
											transaction = new Transaction(transferAmount, transferTransactionDescription, Timestamp.valueOf(LocalDateTime.now()), accountIdReceiver, "PENDING");
											if (bankAccountService.transfer(accountIdSender, transaction)==1) {
												log.info("Sent a transfer request! Your new balance is " + bankAccountService.viewBalance(accountIdSender, customerId) +".\n");
											}
										} catch(BusinessException e) {
											log.error(e.getMessage());
										}
										break;
										
									case 5:
								
										accountId = 0;
										
										log.info("Which account would you like to receive a money transfer?");
										
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
											if (!bankAccountService.checkIfAccountApproved(accountId, customerId)) {
												log.info("Sorry, cannot proceed until your account is approved.");
												break;
											};
										} catch(BusinessException e) {
											
											log.error(e.getMessage());
											break;
										}
										
										try {
											
											if (bankAccountService.receiveTransfer(accountId, customerId)) {
												log.info("The money was received! Your current balance is now " + bankAccountService.viewBalance(accountId, customerId) + ".");
											}
										}  catch(BusinessException e) {
											log.error(e.getMessage());
										}
										break;
										
									case 6:
										log.info("I hope our service was satisfactory!\n");
										break;
										
									default:
										log.info("\nPlease enter a number between 1-6 only!\n");
										break;
								}
							} while (cm!=6);
							}
						}  catch(BusinessException e) {
							log.error(e.getMessage());
						}
						
				
					
					try {
						
						//tests if user is an employee
						if (!userVerificationService.checkIfCustomer(username)) {
							
						
							//used for switch cases in employee menu
							int em = 0;
							do {
								
								log.info("===================================================================");
								log.info("Welcome to the Employee Menu! What would you like to do today?\n");
								log.info("1) Approve or reject an account.");
								log.info("2) View a customer's bank accounts.");
								log.info("3) View a log of all transactions.");
								log.info("4) Exit the employee menu.");
								try {
									em = Integer.parseInt(scanner.nextLine());
								} catch (NumberFormatException e) {
									//Activates the default case so the customer stays in the employee menu
									em = 0; 
								}
								
								
								
								switch (em) {
								
									case 1:
										
										accountId = 0;
										log.info("What account number do you want to approve or reject?");
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
										
										log.info("Do you want to approve or reject this account? Press 1 to approve or 2 to reject");
										
										int decision = 0;
										while (decision!=1 && decision!=2) {
											try {
										
												decision = Integer.parseInt(scanner.nextLine());
												while (decision!=1 && decision!=2) {
													log.info("Please input either 1 to approve or 2 to reject the account");
													decision = Integer.parseInt(scanner.nextLine());
											}
											
										} catch (NumberFormatException e) {
											log.error("Please input a number!");
											} 
										}
										
										if (decision==1) {
											try {
												bankAccountService.updateAccountStatus(accountId, "APPROVED");
												
											}  catch(BusinessException e) {
													log.warn(e.getMessage());
											}
									}
										
										if (decision==2) {
											try {
												bankAccountService.updateAccountStatus(accountId, "REJECTED");
											}  catch(BusinessException e) {
													log.warn(e.getMessage());
											}
										}
										
										break;
										
									case 2:
										
										int customerId = 0;
										
										log.info("Do you wish to enter a customer's ID or a customer's username to access their accounts?"
												+ " Press 1 for ID or 2 to check through a username");
										
										decision = 0;
										while (decision!=1 && decision!=2) {
											try {
												decision = Integer.parseInt(scanner.nextLine());
												while (decision!=1 && decision!=2) {
													log.info("Please input either 1 to enter a customer ID or 2 to check through a username!");
													decision = Integer.parseInt(scanner.nextLine());
											}
											
										} catch (NumberFormatException e) {
											log.error("Please input a number!");
											} 
										}
										if (decision==1) {
											try {
												log.info("Please enter the customer ID.");
												while (customerId<=0) {
													try {
														customerId = Integer.parseInt(scanner.nextLine());
														while (customerId<=0) {
															log.info("Please input a positive ID");
															customerId = Integer.parseInt(scanner.nextLine());
														}
													
													} catch (NumberFormatException e) {
														 log.error("Please input a number!");
														} 
												}
												
												//gets Customers accounts from customer ID
												log.info("Below are the customer's accounts:\n");
												bankAccountService.getAccountsFromCustomerId(customerId);
											
												
											}  catch(BusinessException e) {
													log.warn(e.getMessage());
											}
										}
										
										if (decision==2) {
											log.info("Please enter the customer's username");
												username = scanner.nextLine();
										
											try {
												customerId = customerVerificationService.getCustomerIdFromUsername(username);
												//gets Customers accounts from customer ID
												log.info("Below are the customer's accounts:\n");
												bankAccountService.getAccountsFromCustomerId(customerId);
											}  catch(BusinessException e) {
												log.warn(e.getMessage());
											}
											
										}
										
										break;
										
									case 3:
										
										log.info("Below is a list of all transactions:\n");
										try {
											
											bankAccountService.getAllTransactions();
										} catch(BusinessException e) {
											log.warn(e.getMessage());
										}
										break;
									case 4:
										log.info("Thank you for choosing to work at Super Bank.\n");
										break;
									
									default:
										log.warn("\nPlease enter a number between 1-4 only!\n");
										break;
								}
								
							} while (em!=4);
						}
					}  catch(BusinessException e) {
						log.warn(e.getMessage());
					}
					
					break;
				case 3:
					log.info("Thank you for visiting us at Super Bank.");
					break;
					
				default:
					log.warn("\nPlease enter a number between 1-3 only!\n");
					break;
				}
			} while (um!=3);
		scanner.close();
	}

}
