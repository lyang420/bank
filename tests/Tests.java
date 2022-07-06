package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import programs.Account;

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
}
