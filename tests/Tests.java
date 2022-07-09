package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import programs.Account;
import programs.Bank;

/* Space to write JUnit tests. */

public class Tests {

	@Test
	public void testAccountValues() {
		Account account = new Account("johndoe123@gmail.com", "badpassword123", 0.00);
		
		assertEquals(account.getEmail(), "johndoe123@gmail.com");
		assertEquals(account.getPassword(), "badpassword123");
		assertTrue(account.getBalance() == 0.00);
		
		account.resetPassword("anotherterriblepassword456");
		assertFalse(account.getPassword().equals("badpassword123"));
		assertEquals(account.getPassword(), "anotherterriblepassword456");
		
		account.changeBalance(100.00);
		assertFalse(account.getBalance() == 0.00);
		assertTrue(account.getBalance() == 100.00);
	}
	
	/* Write more tests! */

	@Test
	public void testBankCreation() {
		Bank bank = new Bank("CS Bank");
		
		assertEquals(bank.getName(), "CS Bank");
	}
	
	@Test
	public void testBankFindAccount() {
		Bank bank = new Bank("CS Bank");
		
		Account account = new Account("johndoe123@gmail.com", "badpassword123", 0.00);
		
		bank.addAccount(account);
		assertTrue(bank.containsAccount(account.getEmail()));
	}
}
