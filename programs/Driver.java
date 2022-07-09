package programs;

import java.util.Scanner;

/* Executes program. */

public class Driver {

	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		String bankName = "";
		
		System.out.println("Welcome to Bank Application. Which bank would you like to visit today?");
		bankName = sc.nextLine();
			
		if (!bankName.equals("exit") && !bankName.equals("quit")) {
			Bank bank = new Bank(bankName);
			bank.welcome();	
		}
		
		sc.close();
		return;
	}

}
