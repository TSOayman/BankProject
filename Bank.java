


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Date;
import java.util.Scanner;



public class Bank
{
	
	public static final int SAVINGS_TYPE = 1;
	public static final int CHECKING_TYPE = 2;
	public static final int CREDIT_TYPE = 3;
	
	Account [] myAccounts;
	int nextNewCustomer;

	public Bank (int numberCustomers)
	{
		myAccounts = new Account[numberCustomers];
		nextNewCustomer = 0;
	}
	public void addAccount(Account acc)
	{
		if (nextNewCustomer < myAccounts.length)
			myAccounts[nextNewCustomer++] = acc;
	}
	public void processMonthlyEvent()
	{
		for (int k=0; k<nextNewCustomer; k++)
			myAccounts[k].monthlyEvent();
	}
	
	public Account findAccount (String name)
	{
		for (int k=0; k<nextNewCustomer; k++)
		{
			if (myAccounts[k].getName().equals(name))
				return myAccounts[k];
		}
		return null; 
	}
	/*
	 *  todo:
	 *     add:
	 *        public Account findAccount (String name, int type)
	 */
	public Account findAccount (String name, int type){
		for (int k=0; k<nextNewCustomer; k++)
		{
			if (myAccounts[k].getName().equals(name) && myAccounts[k].getNumType()==type)
				return myAccounts[k];
		}
		return null; 
		
	}
	
	public void saveData(String filename) throws FileNotFoundException{
		File file = new File(filename);
		PrintWriter outFile = new PrintWriter ( file );
		
		for(int i=0; i<nextNewCustomer;i++) {
			
			
			
			long dateCreated = myAccounts[i].getDate().getTime();
			
			outFile.printf("%-30s  %8d %8.2f %2d %16d\r\n",myAccounts[i].getName(),myAccounts[i].getId(), myAccounts[i].getBalance(), 
					myAccounts[i].getNumType(), dateCreated);
			
			


		}
		outFile.close();
	
	}
	
	public void readData(String fileName) throws FileNotFoundException
	{
		File file = new File( fileName );

	myAccounts = new Account[myAccounts.length];
		nextNewCustomer = 0;
		String line = "";

	      Scanner file_input = new Scanner (file);
	      while (file_input.hasNext())
		{
	//read in data in the same format as it was written to file
	    	  line = file_input.nextLine();
	    		Scanner temp = new Scanner (line);
				String name = temp.next();  			//first
				name = name + " " + temp.next();		//last name
				int acntID = temp.nextInt();				//count
				double balance = temp.nextDouble();	//balance
				int acntType = temp.nextInt();
				long dateCreated = temp.nextLong();
				Date acntCreateDate = new Date(dateCreated);
				System.out.printf("%s, %4d, %8.2f %4d, %s\n", name, acntID, balance, acntType, acntCreateDate);
				
	    	 if(acntType==SAVINGS_TYPE){
				 Account a1 = new Account( name,  balance, acntID, acntCreateDate);
				 addAccount(a1);
	    		  
	    	  }
	    	 else if(acntType==CHECKING_TYPE){
	    	CheckingAccount ch1 = new CheckingAccount(name,  balance, acntID, acntCreateDate);
	    	addAccount(ch1);
	    	 }
	    	 else if(acntType==CREDIT_TYPE){
	    		 CreditAccount cr1 = new CreditAccount(name,  balance, acntID, acntCreateDate);
	    		 addAccount(cr1);
	    		 
	    	 }
	    	 else{
	    		 System.out.println();
	    	 }
	    	 temp.close();
	    	 }
	      file_input.close();
	    	// file_input.next() .printf("%-30s  %8d %8.2f %2d %8d\r\n",myAccounts[i].setName(line.indexOf(0,30)),myAccounts[i].getId(), myAccounts[i].getBalance(), 
				//		myAccounts[i].getNumType(), dateCreated());
//	use TestPrintWriter.java as an example
	    	  }

	//date:
	//long dateCreated = temp.nextLong();
	//Date date = new Date(dateCreated);

	
	
	
	
	public String toString()
	{
		String str = String.format("%-30s  %8s %8s %10s\n", "Name", "ID", "Balance", "Type");
		for (int k=0; k<nextNewCustomer; k++)
		{
			String name = myAccounts[k].getName();
			int id = myAccounts[k].getId();
			double balance = myAccounts[k].getBalance();
			String sType = myAccounts[k].getStringType();
			String entry = String.format("%-30s  %8d %8.2f %10s", name, id, balance, sType);
			str += entry + "\n";
		}
		return str;
	}

	
	    	  
	    	  
}
