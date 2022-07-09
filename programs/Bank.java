package programs;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/* The Bank class represents a bank. A bank has many clients and accounts,
 * as well as a name. Customers can interact with the bank and request
 * to create a new account or access an existing account. */

public class Bank {

	private Scanner sc;
	private Set<Account> accounts;
	private String bankName;

	/* Construct a new Bank. */
	
	public Bank(String name) {
		this.sc = new Scanner(System.in);
		this.accounts = new HashSet<Account>();
		this.bankName = name;
	}

	/* Return the bank's name. */
	
	public String getName() {
		return this.bankName;
	}
	
	/* Check to see if a customer already has an account with the bank.
	   Clients may only register one account per email. */
	
	public boolean containsAccount(String email) {
		for (Account a : this.accounts) {
			if (a.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	/* Return the account belonging to a customer. */
	
	public Account getAccount(String email) {
		for (Account a : this.accounts) {
			if (a.getEmail().equals(email)) {
				return a;
			}
		}
		return null;
	}
	
	/* Check to see if a customer entered their password correctly. */
	
	public boolean checkPassword(String email, String password) {
		if (getAccount(email).getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	/* Reset a customer's password if they forgot it. */
	
	public void resetPassword(String email, String newPassword) {
		getAccount(email).resetPassword(newPassword);
	}
	
	/* Method to manually add an account to the bank. */
	
	public void addAccount(Account account) {
		accounts.add(account);
	}

	public void welcome() {
		System.out.println("Thank you for visiting " + getName() + "!");
		System.out.println("Your visit may be recorded for quality assurance purposes.");
		
		homeScreen();
		sc.close();
		return;
	}
	
	public void homeScreen() {
		String option = "";
		boolean running = true;
		
		while (running) {
			System.out.println("Please press 1 to log in to an account, or 2 to create a new account.");
			System.out.println("Press 3 to repeat these options.");
			System.out.println("Press 0 to leave " + this.getName() + ".");
			
			option = sc.next();
			
			if (option.equals("1")) {
				login();
			} else if (option.equals("2")) {
				createAccount();
			} else if (option.equals("3")) {
				System.out.println("Repeating options:");
			} else if (option.equals("0")) {
				System.out.println("We hope you had a good experience at " + getName() + " today. Come again!");
				running = false;
			} else {
				System.out.println("Invalid selection; please try again.");
			}
		}

		return;
	}
	
	public void login() {
		String email = "", password = "", reset = "", newPassword = "";
		boolean running = true;
		
		while (running) {
			System.out.println("Enter your email, or exit to go back to the Home Screen:");
			email = sc.next();
			
			if (email.equals("exit") || email.equals("quit")) {
				running = false;
				break;
			}
			
			System.out.println("Enter your password: ");
			password = sc.next();
			
			if (!containsAccount(email)) {
				System.out.println("There is no account registered to this email.");
			} else if (!checkPassword(email, password)) {
				System.out.println("Password or email is incorrect.");
				System.out.println("Would you like to reset your password? [Y/N]");
				reset = sc.next();
				
				if (reset.equals("Y")) {
					System.out.println("Enter your new password: ");
					newPassword = sc.next();
					if (newPassword.equals(getAccount(email).getPassword())) {
						System.out.println("Your new password cannot be the same as your old password.");
					} else {
						resetPassword(email, newPassword);	
					}
				}
			} else {
				enterAccount(email, false);
				running = false;
			}	
		}
		
		return;
	}
	
	public void createAccount() {
		String email = "", password = "";
		boolean running = true;
		
		while (running) {
			System.out.println("Enter your email, or exit to go back to the Home Screen:");
			email = sc.next();
			
			if (email.equals("exit") || email.equals("quit")) {
				running = false;
				break;
			}
			
			if (containsAccount(email)) {
				/* Customers cannot open multiple accounts under the same email. */
				System.out.println("There already exists an account registered to that email.");
			} else {
				System.out.println("Create your password:");
				password = sc.next();
				
				addAccount(new Account(email, password, 0.00));
				enterAccount(email, true);
				running = false;
			}
		}
		
		return;
	}
	
	public void enterAccount(String email, boolean firstVisit) {
		String option = "";
		boolean running = true;
		
		if (firstVisit) {
			System.out.println("Welcome to your new account at " + getName() + "!");
		}
		
		while (running) {
			System.out.println("Press 1 to make a deposit, 2 to withdraw an amount, 3 to view your balance, or 0 to log out of your account.");
			option = sc.next();
			
			if (option.equals("1")) {
				deposit(email);
			} else if (option.equals("2")) {
				withdraw(email);
			} else if (option.equals("3")) {
				viewBalance(email);
			} else if (option.equals("0")) {
				running = false;
			} else {
				System.out.println("Invalid selection.");
			}
		}
		
		return;
	}
	
	public void deposit(String email) {
		double deposit = 0.00;
		
		System.out.println("Enter an amount to deposit:");
		deposit = sc.nextDouble();
		getAccount(email).changeBalance(deposit);
		System.out.println("$" + deposit + " deposited in account belonging to " + email + ".");
	}
	
	public void withdraw(String email) {
		double withdraw = 0.00;
		
		System.out.println("Enter an amount to withdraw:");
		withdraw = sc.nextDouble();
		
		/* If you withdraw too much money at once, you'll attract the attention of the IRS. */
		
		if (withdraw > 9999.99) {
			System.out.println("Withdraw cannot exceed $10,000.00; Please hold and expect an audit from the Internal Revenue Service within two (2) business days.");
		} else {
			if (getAccount(email).getBalance() < withdraw) {
				System.out.println("Error: Withdraw amount cannot exceed account balance.");
			} else {
				getAccount(email).changeBalance(withdraw * -1);
				System.out.println("$" + withdraw + " withdrawn from account belonging to " + email + ".");	
			}
		}
	}
	
	public void viewBalance(String email) {
		System.out.println("Your balance is $" + getAccount(email).getBalance());
	}

}
