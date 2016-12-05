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
import java.util.GregorianCalendar;

public class Open_frame extends JFrame{
	public Open_frame(){
		Open_frame temp_frame = this;
		Integer day[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		String month[] = {"Jan. ","Feb. ","Mar. ","Apr. ","May  ","Jun  ","July ","Aug  ","Sept.","Oct. ","Nov. ","Dec  "};
		Integer year[] = new Integer[60];
		String type[] = {"Saver account","Junior account","Current account"};
		for(int i = 0; i < 60; i++){
			year[i] = 2016-i;
		}
		int accNo = (int) (100000+900000*Math.random());
		while(Main.status.customers.containsKey(accNo)){
			accNo = (int) (100000+900000*Math.random());
		}
		final int tempaccNo = accNo;
		this.setLayout(new GridLayout(2,1));
		JPanel welcom_info = new JPanel(new GridLayout(3,1));
		JLabel welcom = new JLabel("Your account number is:");
		JLabel display_accNo = new JLabel("                                   "+accNo);
		JLabel welcom2 = new JLabel("Please remember and enter following information:");
		welcom_info.add(welcom);
		welcom_info.add(display_accNo);
		welcom_info.add(welcom2);
		JPanel input = new JPanel(new GridLayout(9,2));
		JLabel name_label = new JLabel("Name");
		JTextField name_text = new JTextField();
		input.add(name_label);
		input.add(name_text);
		JLabel address_label = new JLabel("Address");
		JTextField address_text = new JTextField();
		input.add(address_label);
		input.add(address_text);
		JLabel birth_day = new JLabel("The day you birth");
		JComboBox day_box = new JComboBox(day);
		input.add(birth_day);
		input.add(day_box);
		JLabel birth_month = new JLabel("The month you birth");
		JComboBox month_box = new JComboBox(month);
		input.add(birth_month);
		input.add(month_box);
		JLabel birth_year = new JLabel("The year you birth");
		JComboBox year_box = new JComboBox(year);
		input.add(birth_year);
		input.add(year_box);
		JLabel acc_type = new JLabel("Account type");
		JComboBox type_box = new JComboBox(type);
		input.add(acc_type);
		input.add(type_box);
		JLabel PIN_label = new JLabel("PIN");
		JTextField PIN_text = new JTextField();
		input.add(PIN_label);
		input.add(PIN_text);
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = name_text.getText();
				String address = address_text.getText();
				int day = (int) day_box.getSelectedItem();
				int month = month_box.getSelectedIndex()+1;
				int year = 2016-year_box.getSelectedIndex();
				int type = type_box.getSelectedIndex();
				String PIN = PIN_text.getText();
				if(name.equals("") || address.equals("") || PIN.equals("")){
					JOptionPane.showMessageDialog(null, "Please enter all the quired information!");
					return;
				}else if((year < 2000) && (type == 1)){
					JOptionPane.showMessageDialog(null, "Junior account is only for people under 16 years old!");
					return;
				}else{
					Customer newcustomer = new Customer(name,address,new GregorianCalendar(year,month,day));
					Main.status.openAccount(tempaccNo, name, type, PIN);
					Main.status.addCustomer(tempaccNo, newcustomer);
					Main.status.saveAccount();
					Main.status.saveCustomers();
					temp_frame.dispose();
					return;
				}
			}
			
		});
		input.add(new JLabel(""));
		input.add(new JLabel(""));
		input.add(new JLabel(""));
		input.add(confirm);
		this.add(welcom_info);
		this.add(input);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}
