package programs;

/* The Account class represents a bank account. A bank account has an email,
 * a password, and a balance. */

public class Account {
	
	private String email;
	private String password;
	private double balance;
	
	/* Construct a new Account. */
	public Account(String email, String password, double balance) {
		this.email = email;
		this.password = password;
		this.balance = balance;
	}
	
	/* Return the account's email. */
	public String getEmail() {
		return this.email;
	}
	
	/* Return the account's password. */
	public String getPassword() {
		return this.password;
	}
	
	/* Reset the account's password. */
	public void resetPassword(String newPassword) {
		this.password = newPassword;
	}
	
	/* Return the account's balance. */
	public double getBalance() {
		return this.balance;
	}
	
	/* Change the account's balance. */
	public void changeBalance(double amount) {
		this.balance += amount;
	}
}
