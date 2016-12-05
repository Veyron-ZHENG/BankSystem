import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Account_Manage_frame extends JFrame{
	public Account_Manage_frame(){
		this.init();
	}
	
	public void clear(){
		this.getContentPane().removeAll();
		this.repaint();
	}
	public void init(){
		this.getContentPane().add(BorderLayout.NORTH,new JScrollPane(setTable()));
		//this.setSize(900,600);
		JButton suspend = new JButton("Suspend account");
		JButton dissuspend = new JButton("Dissuspend account");
		JButton set_limit = new JButton("Set overdraft limit");
		suspend.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				suspend(true);
			}
		});
		
		dissuspend.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				suspend(false);
			}
			
		});
		
		set_limit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SetLimit();
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.add(suspend);
		panel.add(dissuspend);
		panel.add(set_limit);
		this.getContentPane().add(BorderLayout.SOUTH,panel);
		this.setSize(1300,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JTable setTable(){
		Object [][] tableData = new Object[Main.status.accounts.size()][11];
		String type[] = {"Saver account","Junior account","Current account"};
		Object [] columnTitle = {"accNo.","Name","Birth Date","Address","Account Type","Balance","Un-cleared Balance","Overdraft Limit","Noticed Date","State","PIN"};
		int i = 0;
		for(Entry<Integer,BankAccount> a:Main.status.accounts.entrySet()){
			int accNo = a.getKey();
			tableData[i][0] = accNo;
			tableData[i][1] = Main.status.customers.get(accNo).getName();
			tableData[i][2] = Main.status.customers.get(accNo).getBirth().get(Calendar.YEAR)+"/"+Main.status.customers.get(accNo).getBirth().get(Calendar.MONTH)+"/"+Main.status.customers.get(accNo).getBirth().get(Calendar.DAY_OF_MONTH);
			tableData[i][3] = Main.status.customers.get(accNo).getAddress();
			tableData[i][4] = type[a.getValue().getType()];
			tableData[i][5] = a.getValue().getBalance();
			tableData[i][6] = a.getValue().getUnBalance();
			if(a.getValue().getType() != 2)
			tableData[i][7] = "--";
			else tableData[i][7] = ((currentAccount)a.getValue()).getOverdraftLimit();
			if(a.getValue().getType() != 0){
//				tableData[i][8] = "--";
				tableData[i][8] = "--";
			}
			else{
				savingAccount temp = (savingAccount) a.getValue();
//				tableData[i][8] = temp.getNoticeSaving();
				if(temp.isNoticed())
					tableData[i][8] = temp.getNoticeDate().get(Calendar.YEAR) + "/" + temp.getNoticeDate().get(Calendar.MONTH) + "/" +temp.getNoticeDate().get(Calendar.DAY_OF_MONTH);
				else
					tableData[i][8] = "Haven't set";
			}
			if(a.getValue().isSuspend())
				tableData[i][9] = "Suspended";
			else tableData[i][9] = "Normal";
			tableData[i][10] = a.getValue().getPIN();
			i++;
		}
		JTable table = new JTable(tableData,columnTitle);
		return table;
	}
	
	void suspend(boolean a){
		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Suspend account");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(3,1));
		frame.add(new JLabel("Please enter a account number"));
		final JTextField accNo = new JTextField();
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!Main.status.accounts.containsKey(Integer.parseInt(accNo.getText()))){
					JOptionPane.showMessageDialog(null, "Account doesn't exit!");
					return;
				}
				Main.status.accounts.get(Integer.parseInt(accNo.getText())).set_suspend(a);
				Main.status.saveAccount();
				Main.status.saveCustomers();
				frame.dispose();
				clear();
				init();
				
			}
			
		});
		frame.add(accNo);
		frame.add(confirm);
		frame.setSize(300,200);;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	void SetLimit(){
		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Set overdraft limit");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(4,1));
		frame.add(new JLabel("Please enter a account number"));
		final JTextField accNo = new JTextField("Account Number");
		final JTextField limit = new JTextField("Overdraft Limit");
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!Main.status.accounts.containsKey(Integer.parseInt(accNo.getText()))){
					JOptionPane.showMessageDialog(null, "Account doesn't exit!");
					return;
				}else if(Main.status.accounts.get(Integer.parseInt(accNo.getText())).getType() != 2){
					JOptionPane.showMessageDialog(null, "It's not a current account.");
					return;
				}
				((currentAccount)Main.status.accounts.get(Integer.parseInt(accNo.getText()))).setOverdraftLimit(Integer.parseInt(limit.getText()));
				Main.status.saveAccount();
				Main.status.saveCustomers();
				frame.dispose();
				clear();
				init();
				
			}
			
		});
		frame.add(accNo);
		frame.add(limit);
		frame.add(confirm);
		frame.setSize(300,200);;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
