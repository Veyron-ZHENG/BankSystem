import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
public class Bank {
	HashMap<Integer,BankAccount> accounts = new HashMap<Integer,BankAccount>();
	HashMap<Integer,Customer> customers = new HashMap<Integer,Customer>();
	
	boolean addCustomer(int accNo, Customer customer){
		if(customers.containsKey(accNo)) return false;
		customers.put(accNo, customer);
		return true;
	}
	
	public Bank(){
		try{
			this.accounts = readAccount();
			this.customers = readCustomers();
		}catch(Exception ex){
		}
		
	}
	
	boolean openAccount(int accNo, String accName, int accountType, String PIN){
		BankAccount temp;
		if(accounts.containsKey(accNo)) return false;
		if(accountType == 0)
			temp = new savingAccount(accNo,accName,PIN);
		else if(accountType == 1)
			temp = new JuniorAccount(accNo,accName,PIN);
		else
			temp = new currentAccount(accNo,accName,PIN);
		accounts.put(accNo, temp);
		return true;
	}
	
	int closeAccount(int accNo){
		if(!accounts.containsKey(accNo)) return -1;
		if(accounts.get(accNo).getBalance() < 0) return 0;
		accounts.remove(accNo);
		return 1;
	}
	
	int suspendAccount(int accNo){
		if(!accounts.containsKey(accNo)) return 0;
		else if(accounts.get(accNo).suspend) return 1;
		else{
			accounts.get(accNo).suspend = true;
			return 2;
		}
	}
	
	int dissuspendAccount(int accNo){
		if(!accounts.containsKey(accNo)) return 0;
		else if(!accounts.get(accNo).suspend) return 1;
		else{
			accounts.get(accNo).suspend = false;
			return 0;
		}
	}
	
	public void saveAccount(){
		try{
			ObjectOutputStream oo =new ObjectOutputStream(new FileOutputStream(new File("./account")));
			oo.writeObject(this.accounts);
			oo.close();
		}catch(Exception ex){
			System.out.println(1);
		}
		
	}
	
	public HashMap<Integer, BankAccount> readAccount(){
		HashMap<Integer, BankAccount> temp = null;
		try{
			ObjectInputStream ois =new ObjectInputStream(new FileInputStream(new File("./account")));
			temp = (HashMap<Integer, BankAccount>) ois.readObject();
			ois.close();
		}catch(Exception ex){
			System.out.println(2);
			return new HashMap<Integer, BankAccount>();
		}
		return temp;
	}
	
	public void saveCustomers(){
		try{
			ObjectOutputStream oo =new ObjectOutputStream(new FileOutputStream(new File("./customers")));
			oo.writeObject(customers);
			oo.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println(3);
		}
	}
	

	public HashMap<Integer, Customer> readCustomers(){
		HashMap<Integer, Customer> temp = null;
		try{
			ObjectInputStream ois =new ObjectInputStream(new FileInputStream(new File("./customers")));
			temp = (HashMap<Integer, Customer>) ois.readObject();
			ois.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println(4);
			return new HashMap<Integer, Customer>();
		}
		return temp;
	}
}
