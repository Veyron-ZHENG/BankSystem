import java.util.Map.Entry;

public class timer extends Thread {
	public void run(){
		while(true){
			if(((int)(System.currentTimeMillis()/1000))%30 == 0){
				for(Entry<Integer,BankAccount> a:Main.status.accounts.entrySet()){
					a.getValue().clear();
					Main.status.saveAccount();
					Main.status.saveCustomers();
				}
				System.out.println("Cleared");
			}
		}
	}
}
