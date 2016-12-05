import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame {
	public static Bank status = new Bank();
	public Main(){
		this.setLayout(new GridLayout(2,1));
		JLabel welcome_info = new JLabel("Welcome to our bank system!");
		this.add(welcome_info);
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(3,2));
		JButton open = new JButton("Open Account");
		JButton close = new JButton("Close Account");
		JButton withdraw = new JButton("Withdraw");
		JButton deposit = new JButton("Deposit");
		JButton manage = new JButton("Account Management");
		JButton set_Notice = new JButton("Set Noticed Date");
		set_Notice.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Identification(2);
			}
			
		});
		
		manage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Account_Manage_frame();
			}
			
		});
		
		open.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Open_frame();
			}
			
		});
		close.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Close_frame();
			}
			
		});
		withdraw.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Identification(1);
			}
			
		});
		deposit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Identification(0);
			}
			
		});
		buttons.add(withdraw);
		buttons.add(deposit);
		buttons.add(open);
		buttons.add(close);
		buttons.add(manage);
		buttons.add(set_Notice);
		this.add(buttons);
		this.setSize(600,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String arg[]){
		new Main();
		timer a = new timer();
		a.start();
	}
}
