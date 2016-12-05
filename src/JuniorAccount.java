import java.io.Serializable;

public class JuniorAccount extends BankAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5980295221973088225L;

	public JuniorAccount(int accNo,String accName,String PIN){
		super(accNo,accName,PIN);
		this.accountType = 1;
	}
}
