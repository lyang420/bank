package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import programs.Bank;

public class Tests {

	@Test
	public void testScanner() {
		Bank bank = new Bank("Bank of Java");
		assertEquals(bank.getName(), "Bank of Java");
	}
	
	/* Write more tests! */
}
