


import java.util.Date;


public class CreditAccount extends Account {

	static double annualServiceRate;

	public CreditAccount(String name, double balance, int id, Date date) {
		super(name, balance, id, date);

	}

	public CreditAccount(String name, double balance) {
		super(name, balance*-1);

	}
	static double setAnnualServiceCharge(double rate){
		annualServiceRate=rate;
		return annualServiceRate;
	}

	public boolean withdraw (double amount)
	{




		setBalance(getBalance()+(amount));

		return true;

	}

	public void monthlyEvent()
	{
		//balance += balance * annualInterestRate/12;
		double balance = getBalance() *  annualServiceRate/12;

		withdraw(balance);

	}
	public double getBalance()
	{
		return super.getBalance(); 
	}

	//note: the access type protected.  - why?
	protected void setBalance(double balance)
	{
		super.setBalance(balance);
	}


	public String getStringType()
	{
		return "Credit";
	}
	public int getNumType()
	{
		return CREDIT_TYPE;
	}

}

