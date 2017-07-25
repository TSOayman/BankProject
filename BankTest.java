




import java.io.FileNotFoundException;
import java.util.Scanner;

public class BankTest
{

	/**
	 * @param args
	 */
	static Bank bankOfNOVA = new Bank(20);
	static Scanner input = new Scanner(System.in);

	/*
	 * addNewAccount
	 *    prompt user for the name: 
	 */
	public static void addNewAccount()
	{
		//prompt for name:   use nextLine() to capture first and last name
		System.out.println("Enter Name: ");
		String name = input.nextLine();
		while(name.length()<2){
			System.out.println("Name must be first and last name, or first name only. Must be more than 2 letters long");
			System.out.println("Please try again");
			name = input.nextLine();
			
			

		}

		//prompt for starting balance
		System.out.println("Enter starting balance: ");
		double balance = input.nextDouble();





		//prompt for type of account: 1:Savings, 2:Checking or 3:Credit
		System.out.println("What type of account do you want to create? 1: Savings  2: Checking  3: Credit");

		int accType= input.nextInt();
		if(accType<1 && accType>3){
			System.out.println("Thats not a valid selection, please choose one of the three given choices, 1: Savings  2: Checking  3: Credit");
			accType=input.nextInt();
		}

		if(bankOfNOVA.findAccount(name, accType)!=null){
			System.out.println("Account already exists, try again");
			return;
		}


		//create new account, type will be based on what the user entered above
		if(accType==1){
			if(balance < 0){
				System.out.println("Cant have a negative balance for savings account.");
				System.out.println("System will now close");
				return;
			}
			else{
				
				Account savings = new Account(name, balance);
				bankOfNOVA.addAccount(savings);
				
				
				if(bankOfNOVA.findAccount(name, accType)!=null){
					System.out.println("Account for that type already exists. ");
					return;
				}
				
			}


		}
		else if(accType==2){

			if(balance < 0){
				System.out.println("Cant have a negative balance for checking account.");
				System.out.println("System will now close");
				return;
			}

			Account checking = new CheckingAccount(name, balance);
			bankOfNOVA.addAccount(checking);
			
		}

		else if(accType==3) {

			Account credit = new CreditAccount(name, balance);
			bankOfNOVA.addAccount(credit);
		}
		
		else{
			System.out.println("Not a valid account type");
			System.exit(0);
		}


	}


	//prompt the user for an amount
	//todo (optional): add in exception processing in case they enter in an invalid value.
	public static double getValue()
	{
		
		System.out.println("Enter amount: ");
		double amount=input.nextDouble();
		return amount;
	}
	/*
	 * processAccess - ATM machine, access individual account
	 *     prompt user for a name
	 *     look up all accounts for the name.
	 *     allow them to deposit, withdraw, transfer
	 */
	public static void processAccess()
	{
		System.out.println("Enter user name: ");
		String name=input.nextLine();
		Account savingsAcc = bankOfNOVA.findAccount(name, Account.SAVINGS_TYPE);
		Account checkAcc = bankOfNOVA.findAccount(name, Account.CHECKING_TYPE);
		Account creditAcc = bankOfNOVA.findAccount(name, Account.CREDIT_TYPE);
		if (savingsAcc == null && checkAcc == null && creditAcc == null)
		{
			System.out.println("could not find an account for "+ name);
			return;
		}

		while(true)
		{
			System.out.println("Account Menu1");
			System.out.println("1..saving deposit");
			System.out.println("2..saving withdrawal");
			System.out.println("3..write a check");
			System.out.println("4..make a charge");
			System.out.println("5..transfer: savings to checking");
			System.out.println("6..transfer: savings to credit");
			System.out.println("7..log off");
			int  choice=input.nextInt();

			/*
			 * make sure the specified account exists
			 */
			if (choice == 1 || choice == 2 || choice == 5 || choice == 6)
			{
				if (savingsAcc == null)
				{
					System.out.println(name + " does not have a savings account");
					continue;
				}
			}
			else if ((choice == 3 || choice == 5) && checkAcc == null)
			{
				System.out.println(name + " does not have a checking account");
				continue;
			}
			else if ((choice == 4 || choice == 6) && creditAcc == null)
			{
				System.out.println(name + " does not have a credit account");
				continue;
			}

			/*
			 * process the command
			 */
			if (choice == 1)
			{

				double amount = getValue();
				if (!savingsAcc.deposit(amount))
					System.out.println("Error in the Deposit");
			}
			else if (choice == 2)
			{
				double amount = getValue();
				if (!savingsAcc.withdraw(amount))
					System.out.println("Error in the withdraw");
			}
			else if (choice == 3)
			{
				double amount = getValue();
				if (!checkAcc.withdraw(amount))
					System.out.println("Error in the withdraw");
			}
			else if (choice == 4)
			{
				double amount = getValue();
				if (!creditAcc.withdraw(amount))
					System.out.println("Error in the withdraw");
			}
			else if (choice == 5)
			{
				double amount = getValue();
				if (!savingsAcc.transferTo(checkAcc, amount))
					System.out.println("Error in the transfer");
			}
			else if (choice == 6)
			{
				double amount = getValue();
				if (!savingsAcc.transferTo(creditAcc, amount))
					System.out.println("Error in the transfer");
			}
			else if (choice == 7)
				return;


			/*
			 * Display status of all accounts
			 */
			if (savingsAcc != null)
				System.out.printf("Savings Balance: %6.2f\n", savingsAcc.getBalance());
			if (checkAcc != null)
				System.out.printf("Checking Balance: %6.2f\n", checkAcc.getBalance());
			if (creditAcc != null)
				System.out.printf("Credit Balance: %6.2f\n", creditAcc.getBalance());
		}
	}
	/*
	 * processMenu
	 *    - main Bank menu
	 */
	public static boolean processMenu()
	{
		System.out.println("1..access");
		System.out.println("2..add");
		System.out.println("3..list");
		System.out.println("4..monthly event");
		System.out.println("5..save data file");
		System.out.println("6..read data file");
		System.out.println("7..exit");
		int choice = input.nextInt();
		String junk = input.nextLine();  //flush the input
		if (choice == 1)
			processAccess();
		else if (choice == 2)
			addNewAccount();
		else if (choice == 3)
			System.out.println(bankOfNOVA);
		else if (choice == 4)
			bankOfNOVA.processMonthlyEvent();


		else if (choice == 5){ //save to data file
			try
			{
				bankOfNOVA.saveData("myBankData.txt");
			} 
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		else if (choice == 6)
		{
			try
			{
				bankOfNOVA.readData("myBankData.txt");
			} catch (FileNotFoundException e){
				e.printStackTrace();
			}
		}


		//else if (choice == 6) read from data file

		else if (choice == 7)
			return true;

		return false;
	}

	public static void testWithdraw(Account acct, double amount)
	{
		if (acct != null)
		{
			boolean result = acct.withdraw(amount);
			System.out.println("withdraw result: "+ result);
		}
	}
	public static void main(String[] args)
	{


		//static rates
		Account.setAnnualInterestRate(0.12);   //12% interest rate, 1% per month (for testing purposes only)
		CreditAccount.setAnnualServiceCharge(0.24);

		String fred = new String ("Fred Flinstone");
		Account a1 = new Account(fred, 1000);
		bankOfNOVA.addAccount(a1);
		CheckingAccount ca1 = new CheckingAccount(fred, 500);
		bankOfNOVA.addAccount(ca1);
		CreditAccount cc1 = new CreditAccount(fred, 100);
		bankOfNOVA.addAccount(cc1);
		ca1.setCreditAccount(cc1);


		String wilma = new String ("Wilma Flinstone");
		Account a2 = new Account(wilma, 2000);
		bankOfNOVA.addAccount(a2);
		CheckingAccount ca2 = new CheckingAccount(wilma, 500);
		bankOfNOVA.addAccount(ca2);

		String barney = new String ("Barney Ruble");

		Account a3 = new Account(barney, 5000);
		bankOfNOVA.addAccount(a3);

		System.out.println("After adding 6 Accounts");
		System.out.println(bankOfNOVA);

		System.out.println("\nTest 1: Withdraw 300 from Fred's checking");
		Account acct = bankOfNOVA.findAccount(fred, Account.CHECKING_TYPE);

		testWithdraw(acct, 300);
		System.out.println(bankOfNOVA);

		System.out.println("\nTest 2: Withdraw 500 from Fred's checking, verify withdraw from credit");
		testWithdraw(acct, 500);	
		System.out.println(bankOfNOVA);

		System.out.println("\nTest 3: Withdraw 600 from Wilma's checking, insufficent funds - should fail");
		System.out.println("\n      : Verify overdraft charge");
		acct = bankOfNOVA.findAccount(wilma, Account.CHECKING_TYPE);
		testWithdraw(acct, 600);	
		System.out.println(bankOfNOVA);

		System.out.println("\nTest 4: Withdraw 2000 from Barney's savings");
		acct = bankOfNOVA.findAccount(barney, Account.SAVINGS_TYPE);
		testWithdraw(acct, 2000);	
		System.out.println(bankOfNOVA);

		boolean result=true;
		System.out.println("\nTest 5: Transfer 100 from Wilma's savings to her checking account");
		acct = bankOfNOVA.findAccount(wilma, Account.SAVINGS_TYPE);
		Account chkAcct = bankOfNOVA.findAccount(wilma, Account.CHECKING_TYPE);
		result = acct.transferTo(chkAcct, 100);
		System.out.println("transfer result: "+ result);
		System.out.println(bankOfNOVA);


		System.out.println("\nTest 7: Transfer 500 from Fred's checking to his savings account - should come from credit");
		acct = bankOfNOVA.findAccount(fred, Account.SAVINGS_TYPE);
		chkAcct = bankOfNOVA.findAccount(fred, Account.CHECKING_TYPE);
		result = chkAcct.transferTo(acct, 500);
		System.out.println("transfer result: "+ result);
		System.out.println(bankOfNOVA);

		System.out.println("\nTest 8: process monthly events, add interest to savings, charge interest to credit accounts");
		bankOfNOVA.processMonthlyEvent();
		System.out.println(bankOfNOVA);

		boolean exitFlag = false;
		while (!exitFlag)
			exitFlag = processMenu();



	}

}
