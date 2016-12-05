import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class savingAccount extends BankAccount implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 3567762150616257889L;
	protected GregorianCalendar noticeDate=new GregorianCalendar();
//	 protected double noticeSaving;
	 protected boolean Noticed = false;
	    
	    
//	 public savingAccount(int accNO, Customer customer, double balance, int pin, boolean suspend, boolean noticeNeeded, int accountType, double unclearAmount, double noticeSaving, GregorianCalendar noticeDate, String chequeDate){
//	    	super(accNO, customer, balance, pin, suspend, noticeNeeded, accountType, unclearAmount, noticeSaving,chequeDate);
//	    	this.noticeDate=noticeDate;
//	    }
	    
	 public savingAccount(int accNo,String accName,String PIN) {
	        super(accNo,accName,PIN);
	        accountType=0;
	    }
	 
	 public boolean isNoticed(){
		 return this.Noticed;
	 }
	 
	 public void setNotice(GregorianCalendar noticeDate){
//	        this.noticeSaving=this.noticeSaving+noticeSaving;
		 	this.Noticed = true;
	        this.noticeDate=noticeDate;
	    }

//	 public double getNoticeSaving() {
//	        return noticeSaving;
//	    }

	 public GregorianCalendar getNoticeDate() {
	        return noticeDate;
	    }
//	 public void info(){
//	        System.out.println("    Account number: "+this.accNo+"\n"+"    User name: "+this.customer.getName()+"\n"+"    Balance: "+this.balance+"\n"+"    NoticeAmount: "+this.noticeSaving+"\n"+"    Notice Date: "+noticeDate.get(Calendar.YEAR)+"."+noticeDate.get(Calendar.MONTH)+"."+noticeDate.get(Calendar.DATE)+"\n");
//	    }      
}
