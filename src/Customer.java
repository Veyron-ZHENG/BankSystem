import java.io.Serializable;
import java.util.GregorianCalendar;

public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 416272809837580707L;
	private String name;
	private String address;
    private GregorianCalendar birthDate;
    public Customer(String name,String address,GregorianCalendar birthDate){
    	this.name = name;
    	this.address = address;
    	this.birthDate = birthDate;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public String getAddress(){
    	return this.address;
    }
    
    public GregorianCalendar getBirth(){
    	return this.birthDate;
    }
}
