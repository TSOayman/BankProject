


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;


public class Account
{
	public static final int SAVINGS_TYPE = 1;
	public static final int CHECKING_TYPE = 2;
	public static final int CREDIT_TYPE = 3;
	
	private String myName;
	private int id;
	private double balance;
	private Date dateCreated;
	static int nextID = 100;
	static double annualInterestRate;

	public Account(String name, double balance)
	{
		this.myName = name;
		this.id = nextID++;
		this.balance = balance;
		dateCreated = new Date();
	}
	public Account(String name, double balance, int id, Date date)
	{
		this.myName = name;
		this.id = id;
		this.balance = balance;
		dateCreated = date;
		if (id >= nextID)
			nextID = id + 1;
	}

	public static void setAnnualInterestRate(double rate)
	{
		annualInterestRate = rate;
	}
	public boolean withdraw (double amount)
	{
		if (amount <= balance)
		{
			balance -= amount;
			return true;
			
		}
		else 
			return false;
	}
	public boolean deposit (double amount)
	{
		if (amount > 0)
		{
			balance += amount; 
			return true;
		} 
		else 
			return false;
	}
	
   //todo: transfer from one account to the other
	//      - return true if successful,
	//      - return false if not successful
	public boolean transferTo(Account acct, double amount)
	{
		
		
		boolean result = false;
		
			if(acct != null ) {
				if(withdraw(amount)){
				result = acct.deposit(amount);
				
				
				
			} else 
				result = false;
		}
		return result;
			
	} 
	public void setId(int id){
		this.id=id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return myName;
	}
	public double getBalance()
	{
		return this.balance;
	}
	
	//note: the access type protected.  - why?
	protected void setBalance(double balance)
	{
		this.balance = balance;
	}
	 
	public Date getDate()
	{
		return dateCreated;
	}
	public void monthlyEvent()
	{
		this.balance += this.balance * annualInterestRate/12;
	}

	@Override
	public String toString()
	{
		return "Account [" + myName + ", id=" + id + ", balance=" + balance + "]";
	}

	public String getStringType()
	{
		return "Savings";
	}
	public int getNumType()
	{
		return SAVINGS_TYPE;
	}
	 public void setDateCreated(long accountDate){
		 this.dateCreated = new Date(accountDate);
		 
		 }   	 
	 
	 public void setNumType(int accType){
		 
	 }
	 
	 
	 
	 }
	


