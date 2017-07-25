


import java.util.Date;


public class CheckingAccount extends Account {

	private CreditAccount refCredit;
	//private CheckingAccount refChecking;
	final static double OVERDRAFT_CHARGE=20.00;

	public CheckingAccount(String name, double balance, int id, Date date) {
		super(name, balance, id, date);

	}

	public CheckingAccount(String name, double balance) {
		super(name, balance);

	}

	public void setCreditAccount(CreditAccount refCredit) {
		this.refCredit = refCredit;

	}


	public boolean withdraw (double amount)
	{
		if(amount > getBalance()) {
			if (refCredit != null ){
				double balance = getBalance() - amount;	
				setBalance(0);
				refCredit.withdraw(balance);
				return true;
			}
			else {
				setBalance(getBalance()-OVERDRAFT_CHARGE);
				return false;
			}
		}
		else {

			super.withdraw(amount);

		}


		return true;
	}

	public void monthlyEvent()
	{

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
		return "Checking";
	}
	public int getNumType()
	{
		return CHECKING_TYPE;
	}





}
