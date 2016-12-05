import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Set_Notice_frame extends JFrame {
	public Set_Notice_frame(Customer customer,BankAccount account){
		Integer day[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		String month[] = {"Jan. ","Feb. ","Mar. ","Apr. ","May  ","Jun  ","July ","Aug  ","Sept.","Oct. ","Dec  "};
		Integer year[] = new Integer[60];
		for(int i = 0; i < 20; i++){
			year[i] = 2016+i;
		}
		Set_Notice_frame temp_frame = this;
		JPanel welcome_info = new JPanel(new GridLayout(3,2));
		JLabel birth_day = new JLabel("The day noticed");
		JComboBox day_box = new JComboBox(day);
		welcome_info.add(birth_day);
		welcome_info.add(day_box);
		JLabel birth_month = new JLabel("The month noticed");
		JComboBox month_box = new JComboBox(month);
		welcome_info.add(birth_month);
		welcome_info.add(month_box);
		JLabel birth_year = new JLabel("The year noticed");
		JComboBox year_box = new JComboBox(year);
		welcome_info.add(birth_year);
		welcome_info.add(year_box);
		JButton confirm = new JButton("Confrim");
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int day = (int) day_box.getSelectedItem();
				int month = month_box.getSelectedIndex() + 1;
				int year = 2016+year_box.getSelectedIndex();
			    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    long to = 0;
			    long from = System.currentTimeMillis();
				try {
					to = df.parse(year + "-" +month + "-" + day).getTime();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(((to - from) / (1000 * 60 * 60 * 24)) < 6){
					JOptionPane.showMessageDialog(null, "The date is too closed.");
					return;
				}else{
					savingAccount temp = (savingAccount)account;
					temp.setNotice(new GregorianCalendar(year,month,day));
					JOptionPane.showMessageDialog(null, "Successfully set");
					Main.status.saveAccount();
					Main.status.saveCustomers();
					temp_frame.dispose();
					return;
				}
				
			}
			
		});
		this.getContentPane().add(BorderLayout.NORTH,new JLabel("Select the noticed day"));
		this.getContentPane().add(BorderLayout.CENTER,welcome_info);
		this.getContentPane().add(BorderLayout.SOUTH,confirm);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}
