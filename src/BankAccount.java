import java.io.Serializable;

public class BankAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1355007526823567941L;
	protected int accNo;
	protected String accName;
	protected double balance;
	protected int accountType;
	protected boolean suspend;
	protected String PIN;
	protected double uncleared_balance;
	
	public String getPIN(){
		return PIN;
	}
	
	public void set_suspend(boolean a){
		this.suspend = a;
	}
	
	public double getUnBalance(){
		return this.uncleared_balance;
	}
	
	public int getType(){
		return accountType;
	}
	
	public void setPIN(String PIN){
		this.PIN = PIN;
	}
	
	
	public BankAccount(int accNo, String accName, String PIN){
		this.accName = accName;
		this.accNo = accNo;
		this.balance = 0.0;
		this.suspend = false;
		this.PIN = PIN;
	}
	
	public BankAccount(String accName, int accNo){
		this.accName = accName;
		this.accNo = accNo;
		this.balance = 0.0;
		this.suspend = false;
	}
	
	public boolean isSuspend(){
		return suspend;
	}
	
	public int getAccNo(){
		return accNo;
	}
	
	public String getAccName(){
		return accName;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public void setAccName(String accName){
		this.accName = accName;
	}
	
	public void deposit(double amount){
		this.balance += amount;
	}
	
	public void deposit_unclear(double amount){
		this.uncleared_balance += amount;
	}
	
	public void clear(){
		this.balance += this.uncleared_balance;
		this.uncleared_balance = 0;
	}
	
	public boolean withdraw(double amount){
		if(balance >= amount){
			balance -= amount;
			return true;
		}else{
			return false;
		}
	}
	
	public String toString(){
		return "Account number:" + accNo + "\n" + "Account name:" + accName + "\n" + "Balance:" +balance + "\n";
	}
}
