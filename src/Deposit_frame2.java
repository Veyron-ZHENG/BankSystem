import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Deposit_frame2 extends JFrame{
	public Deposit_frame2(Customer customer,BankAccount account){
		Deposit_frame2 temp_frame = this;
		String type[] = {"Saver account","Junior account","Current account"};
		String fund_type[] = {"Cleared Fund","Un-cleared Fund"};
		JPanel welcome_info = new JPanel(new GridLayout(3,1));
		JLabel welcome_name = new JLabel("Welcome, " + customer.getName());
		JLabel welcome_type = new JLabel("Your account type: " + type[account.getType()]);
		welcome_info.add(welcome_name);
		welcome_info.add(welcome_type);
		JTextArea detail_info = new JTextArea();
		detail_info.setEditable(false);
		detail_info.append("Cleared balance:    " + account.getBalance() + "\n");
		detail_info.append("Un-cleared balance: " + account.getUnBalance() + "\n");
		if(account.getType() == 2){
			currentAccount temp = (currentAccount) account;
			detail_info.append("Overdrft limit:     " + temp.getOverdraftLimit());
		}
		welcome_info.add(detail_info);
		JPanel operation = new JPanel(new GridLayout(3,2));
		JLabel amount_label = new JLabel("Deposit amount");
		JTextField amount_text = new JTextField();
		JButton confirm = new JButton("Confirm");
		operation.add(amount_label);
		operation.add(amount_text);
		operation.add(new JLabel("Fund type"));
		JComboBox fund_box = new JComboBox(fund_type);
		operation.add(fund_box);
		operation.add(new JLabel());
		operation.add(confirm);
		
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int amount = Integer.parseInt(amount_text.getText());
				int type = fund_box.getSelectedIndex();
				if(type == 0)
					account.deposit(amount);
				else if(type == 1)
					account.deposit_unclear(amount);
				JOptionPane.showMessageDialog(null, "Successfully deposit");
				Main.status.saveAccount();
				temp_frame.dispose();
			}
			
		});
		this.getContentPane().add(BorderLayout.CENTER,welcome_info);
		this.getContentPane().add(BorderLayout.SOUTH,operation);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}
