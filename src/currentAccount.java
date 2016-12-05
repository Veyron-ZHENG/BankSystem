import java.io.Serializable;

public class currentAccount extends BankAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1579713202410905127L;
	private double overdraftLimit;

	public currentAccount(int accNo, String accName,String PIN) {
		super(accNo, accName,PIN);
		this.accountType = 2;
		this.overdraftLimit = 10000;
	}
	
	public currentAccount(String accName, int accNo) {
		super(accName, accNo);
		this.accountType = 2;
		this.overdraftLimit = 10000;
	}
	
	public currentAccount(int accNo, String accName, double overdraftLimit, String PIN){
		super(accNo,accName,PIN);
		this.setOverdraftLimit(overdraftLimit);
	}
	
	public double getOverdraftLimit(){
		return overdraftLimit;
	}
	
	public void setOverdraftLimit(double overdraftLimit){
		this.overdraftLimit = overdraftLimit;
	}
	
	public boolean withdraw(double amount){
		if((balance + overdraftLimit) >= amount){
			balance -= amount;
			return true;
		}else{
			return false;
		}
	}

}
