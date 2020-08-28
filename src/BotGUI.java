package ConnectBot;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BotGUI implements ActionListener{
	static Boolean execute = false;
	static JLabel connectLabel;
	static String connections = "Number of connections: ";
	static JLabel logoLabel;
	static JLabel option1, option2;
	static JComboBox dropdown;
	static JTextField pagelimit;
	JFrame frame;
	JPanel panel;
	static JButton runConnectAlgo;
	static JButton runFilterAlgo;
	JButton stop;
	static JTextField unameEntry;
	static JPasswordField passEntry;
	static JTextField targetEntry;
	static JTextField pagesEntry;
	static JLabel title;
	static JLabel connectionLabel;
	static JLabel Note1;
	static JLabel Note2;
	static JLabel Note3;
	static JLabel StartPage;
	
	public BotGUI() {
		JFrame frame = new JFrame();
		runConnectAlgo = new JButton("Run ConnectBot");
		runFilterAlgo = new JButton("Run FilterBot");
		JButton stop = new JButton("Stop");
		JPanel panel = new JPanel();
		JLabel unameLabel = new JLabel("LinkedIn Username:");
		JLabel passLabel = new JLabel("LinkedIn Password:");
		JLabel Message = new JLabel("FilterBot 2020");
		unameEntry = new JTextField("rguhapatra@gmail.com");
		passEntry = new JPasswordField("Eagledtlcruise2020!");
		JLabel targetLabel = new JLabel("Search target:");
		JLabel pagesLabel = new JLabel("Pages to process:");
		targetEntry = new JTextField("Business student Atlanta");
		pagesEntry = new JTextField("5");
		connectionLabel = new JLabel("ConnectBot 2020");
		String options[] = {"Select FilterBot Setting","Older than 2 Weeks" , "Older than 1 Month"};
		dropdown = new JComboBox(options);
		title = new JLabel("LinkBot 2.0");
		title.setFont(new Font("Geneva", Font.PLAIN, 20));
		connectionLabel.setFont(new Font("Geneva", Font.PLAIN, 14));
		Message.setFont(new Font("Geneva", Font.PLAIN, 14));
		pagelimit = new JTextField("10");
		Note1 = new JLabel("See ConnectLog.txt for details");
		Note2 = new JLabel("See LinkedIn: no. of withdraws");
		Note3 = new JLabel("EXIT browser to TERMINATE");
		StartPage = new JLabel("Start page:");

		
		runConnectAlgo.addActionListener(this);
		runFilterAlgo.addActionListener(this);
		dropdown.addActionListener(this);
		
		
		// ****** Selections : "More than 2 Weeks Old" & "More than 1 Month Old"
		

		Color fg1 = new Color(240, 200, 0);
		Color fg2 = new Color(168, 168, 168);
		Color bg = new Color(30, 30, 30);
		
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		panel.setBackground(new Color(50, 50, 50));
		
		
		title.setForeground(fg1);
		runConnectAlgo.setBackground(fg1);
		runFilterAlgo.setBackground(fg1);
		dropdown.setBackground(fg1);
		title.setBackground(bg);
		unameLabel.setForeground(fg1);
		pagelimit.setForeground(fg2);
		passLabel.setForeground(fg1);
		targetLabel.setForeground(fg1);
		pagesLabel.setForeground(fg1);
		unameEntry.setBackground(bg);
		pagelimit.setBackground(bg);
		passEntry.setBackground(bg);
		targetEntry.setBackground(bg);
		pagesEntry.setBackground(bg);
		unameEntry.setForeground(fg2);
		passEntry.setForeground(fg2);
		targetEntry.setForeground(fg2);
		Message.setForeground(new Color(250, 100, 0));
		pagesEntry.setForeground(fg2);
		pagesLabel.setForeground(fg1);
		connectionLabel.setForeground(new Color(250, 100, 0)); 
		connectionLabel.setBackground(bg);
		Note1.setForeground(new Color(0, 100, 200));
		Note2.setForeground(new Color(0, 100, 200));
		Note3.setForeground(new Color(250, 0, 0));
		StartPage.setForeground(fg1);
		
		panel.setLayout(new GridLayout(0,1));
		panel.add(title);
		panel.add(connectionLabel);
//		panel.add(logoLabel);
		panel.add(unameLabel);
		panel.add(unameEntry);
		panel.add(passLabel);
		panel.add(passEntry);
		panel.add(targetLabel);
		panel.add(targetEntry);
		panel.add(pagesLabel);
		panel.add(pagesEntry);
		panel.add(runConnectAlgo);
		panel.add(Message);
		panel.add(StartPage);
//		panel.add(dropdown);
		panel.add(pagelimit);
		panel.add(runFilterAlgo);
		panel.add(Note1);
		panel.add(Note2);
		panel.add(Note3);
		
		

		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ConnectBot 1.0");
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new BotGUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			if(e.getSource() == runConnectAlgo) {
				String uname = unameEntry.getText();
				String pass = String.valueOf(passEntry.getPassword());
				String targ = targetEntry.getText();
				String processPages = pagesEntry.getText();
				ConnectAlgo run = new ConnectAlgo(uname, pass, targ, processPages);
			
			} else if (e.getSource() == runFilterAlgo) {
				String uname = unameEntry.getText();
				String pass = String.valueOf(passEntry.getPassword());
				String limit = pagelimit.getText();
				String processPages = pagesEntry.getText();
				int selection = dropdown.getSelectedIndex();
				try {
					FilterAlgo run = new FilterAlgo(uname, pass, limit, selection);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

	}
	
}
