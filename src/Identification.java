import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTextArea;

public class Identification extends JFrame{
	public Identification(int type){
		Identification temp_this = this;
		JLabel welcome_info = new JLabel("Please enter your account No. and PIN to idetify");
		this.getContentPane().add(BorderLayout.NORTH, welcome_info);
		JPanel input = new JPanel(new GridLayout(2,2));
		JLabel accNo_label = new JLabel("Account No.");
		JTextField accNo_text = new JTextField();
		JLabel PIN_label = new JLabel("PIN");
		JTextField PIN_text = new JTextField();
		input.add(accNo_label);
		input.add(accNo_text);
		input.add(PIN_label);
		input.add(PIN_text);
		this.getContentPane().add(BorderLayout.CENTER, input);
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int accNo = 0;
				try{
					accNo = Integer.parseInt(accNo_text.getText());
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Please enter right accNo");
					return;
				}
				
				
				
				String PIN = PIN_text.getText();
				if(!Main.status.accounts.containsKey(accNo)){
					JOptionPane.showMessageDialog(null, "Not such a account.");
					return;
				}
				
				if(Main.status.accounts.get(accNo).isSuspend()){
					JOptionPane.showMessageDialog(null, "The account is suspended now");
					return;
				}
				
				if(!Main.status.accounts.get(accNo).getPIN().equals(PIN)){
					JOptionPane.showConfirmDialog(null, "Wrong PIN");
					return;
				}
				if(type == 0)
					new Deposit_frame2(Main.status.customers.get(accNo),Main.status.accounts.get(accNo));
				
				if(type == 1)
					new Withdraw_frame2(Main.status.customers.get(accNo),Main.status.accounts.get(accNo));
				
				if(type == 2){
					
					if(Main.status.accounts.get(accNo).getType() != 0){
						JOptionPane.showMessageDialog(null, "This function is only for saver account");
						return;
					}
					if(((savingAccount)Main.status.accounts.get(accNo)).isNoticed()){
						JOptionPane.showMessageDialog(null, "The account has been noticed");
						return;
					}
					new Set_Notice_frame(Main.status.customers.get(accNo),Main.status.accounts.get(accNo));
				}
				
				temp_this.dispose();
				return;
			}
			
		});
		this.getContentPane().add(BorderLayout.SOUTH, confirm);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

}
