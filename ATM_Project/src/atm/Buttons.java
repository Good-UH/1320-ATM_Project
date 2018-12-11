package atm;
import java.lang.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

import java.io.*;

public class Buttons extends JFrame
{
	private JButton withdraw;
	private JButton deposit;
	private JButton checkBalance;
	private JButton transfer;
	private JButton changePassword;
	private JButton logout;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JPanel thirdPanel;
	private static String accountinfo = null;
	private static String firstName = null;
	private static String middleName = null;
	private static String lastName = null;
	private static int amount = 0;
	private static String activation = null;
	private static String password = null;
	public Buttons()
	{	
			super("ATM");
			setSize(WIDTH, HEIGHT);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			JPanel biggerPanel = new JPanel();
			biggerPanel.setLayout(new GridLayout(3,1));
			
			firstPanel = new JPanel();
			biggerPanel.add(firstPanel);
			secondPanel = new JPanel();
			biggerPanel.add(secondPanel);
			thirdPanel = new JPanel();
			biggerPanel.add(thirdPanel);
			
			Icon b = new ImageIcon(getClass().getResource("ok.png"));
			
			withdraw = new JButton ("Withdraw",b);
			add(withdraw);
			withdraw.setFont(new Font("Verdana", Font.BOLD, 32));
			firstPanel.add(withdraw);
			deposit = new JButton("Deposit",b);
			add(deposit);
			deposit.setFont(new Font("Verdana", Font.BOLD, 32));
			firstPanel.add(deposit);
			checkBalance = new JButton ("Check Balance",b);
			add(checkBalance);
			checkBalance.setFont(new Font("Verdana", Font.BOLD, 32));
			secondPanel.add(checkBalance);
			transfer = new JButton("Make a Transfer",b);
			add(transfer);
			transfer.setFont(new Font("Verdana", Font.BOLD, 32));
			secondPanel.add(transfer);
			changePassword = new JButton ("Change Password",b);
			add(changePassword);
			changePassword.setFont(new Font("Verdana", Font.BOLD, 32));
			thirdPanel.add(changePassword);
			logout = new JButton("Logout",b);
			add(logout);
			logout.setFont(new Font("Verdana", Font.BOLD, 32));
			thirdPanel.add(logout);
			
			add(biggerPanel,BorderLayout.CENTER);
			withdraw.addActionListener(new withdrawAction());
			deposit.addActionListener(new depositAction());
			checkBalance.addActionListener(new checkBalanceAction());
			transfer.addActionListener(new tranferAction());
			changePassword.addActionListener(new changepwAction());
			logout.addActionListener(new logoutAction());

	}
	
	static class withdrawAction implements ActionListener
	{
		static JFrame frame;
		JLabel label;
		static JTextField jwithdraw;
		JPanel panel;
		JButton wbutton;
		public void actionPerformed(ActionEvent event)
		{
			frame = new JFrame("Bank (Withdraw)");
			frame.setVisible(true);
			frame.setSize(400,250);
			frame.setLocation(200, 230);
			
			label = new JLabel ("How much would you like to withdraw?");
			jwithdraw = new JTextField(30);
			panel = new JPanel();
			wbutton = new JButton("Enter");
			
			frame.add(panel);
			panel.add(label);
			panel.add(jwithdraw);
			panel.add(wbutton);
			readfile();
		
			wbutton.addActionListener(new wbuttonAction());
		}
		static class wbuttonAction implements ActionListener
		{

			public void actionPerformed(ActionEvent e1)
			{
				try
				{
				int checkForNumbers = Integer.parseInt(jwithdraw.getText());
				int withdrawlAmount = Integer.parseInt(jwithdraw.getText());
				int temporary = (amount - withdrawlAmount);
				
				if(temporary >=0)
				{
					if(withdrawlAmount >0 && withdrawlAmount <= 500)
					{
					amount = temporary;
					JOptionPane.showMessageDialog(null, String.format("Please take cash below", e1.getActionCommand()));
					frame.setVisible(false);
					}
					else
					{
						amount = amount;
						JOptionPane.showMessageDialog(null, String.format("Invalid Amount. Please withdraw between $1-$500.", e1.getActionCommand()));
					}
				}
				else
				{
					amount = amount;
					JOptionPane.showMessageDialog(null, String.format("Insufficent Funds", e1.getActionCommand()));
				}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, String.format("Please enter only numbers!", e1.getActionCommand()));
				}
				
				
				try 
				{
					PrintWriter output;
					output = new PrintWriter(new FileOutputStream("accountInformation.txt"));
					output.println(accountinfo);
					output.println(firstName);
					output.println(middleName + " " + lastName);
					output.println(amount);
					output.println(activation);
					output.close();
				} 
				catch (FileNotFoundException e) 
				{
					System.out.println("Error! Couldn't replace");
				}
			}
			
		}
		public void readfile()
		{
			try
			{
				 Scanner scanner = new Scanner(new FileInputStream("AccountInformation.txt"));
		         while (scanner.hasNextLine()) 
		         {
		            accountinfo = scanner.next();
		            firstName = scanner.next();
		            middleName = scanner.next();
		            lastName = scanner.next();
		            amount = scanner.nextInt();
		            activation = scanner.next();
		         }
		         scanner.close();
			}
			catch(Exception e)
			{
				
			}
		}	
}
static class depositAction implements ActionListener
{
	static String depositAmount = null;
	static JFrame frame;
	JLabel label;
	static JTextField jdeposit;
	JPanel panel;
	JButton wbutton;
	public void actionPerformed(ActionEvent event)
	{
		frame = new JFrame("Bank (Deposit)");
		frame.setVisible(true);
		frame.setSize(400,250);
		frame.setLocation(200, 230);
		
		JLabel label = new JLabel ("How much would you like to deposit?");
		jdeposit = new JTextField(30);
		panel = new JPanel();
		wbutton = new JButton("Enter");
		
		frame.add(panel);
		panel.add(label);
		panel.add(jdeposit);
		panel.add(wbutton);
		
		readfile();
		wbutton.addActionListener(new enterButtonAction());
	}
	static class enterButtonAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e1)
		{
			try
			{
			int checkForNumbers = Integer.parseInt(jdeposit.getText());
			int depositAmount = Integer.parseInt(jdeposit.getText());
			amount = (amount + depositAmount);
			JOptionPane.showMessageDialog(null, String.format("Money Deposited", e1.getActionCommand()));
			frame.setVisible(false);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, String.format("Please enter only numbers!", e1.getActionCommand()));
			}
			try 
			{
				PrintWriter output;
				output = new PrintWriter(new FileOutputStream("accountInformation.txt"));
				output.println(accountinfo);
				output.println(firstName);
				output.println(middleName + " " + lastName);
				output.println(amount);
				output.println(activation);
				output.close();
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("Error! Couldn't replace");
			}
		}
	}
	public void readfile()
	{
		try
		{
			 Scanner scanner = new Scanner(new FileInputStream("AccountInformation.txt"));
	         while (scanner.hasNextLine()) 
	         {
	            accountinfo = scanner.next();
	            firstName = scanner.next();
	            middleName = scanner.next();
	            lastName = scanner.next();
	            amount = scanner.nextInt();
	            activation = scanner.next();
	         }
	         scanner.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
static class checkBalanceAction implements ActionListener
{
	JFrame frame;
	JLabel label;
	JPanel panel;
	public void actionPerformed(ActionEvent event)
	{
		readfile();
		
		frame = new JFrame("Bank (Balance)");
		frame.setVisible(true);
		frame.setSize(400,250);
		frame.setLocation(200, 230);
		
		label = new JLabel ("This is your current balance: " + amount);
		label.setFont(new Font ("Serif", Font.PLAIN,25));
		panel = new JPanel();
		
		frame.add(panel);
		panel.add(label);
	}
	public void readfile()
	{
		try
		{
			 Scanner scanner = new Scanner(new FileInputStream("AccountInformation.txt"));
	         while (scanner.hasNextLine()) 
	         {
	            accountinfo = scanner.next();
	            firstName = scanner.next();
	            middleName = scanner.next();
	            lastName = scanner.next();
	            amount = scanner.nextInt();
	            activation = scanner.next();
	         }
	         scanner.close();
		}
		catch(Exception e)
		{
			
		}
	}	
}
static class tranferAction implements ActionListener
{
	JFrame frame;
	JLabel label, label2, label3;
	JTextField name,account,amount2;
	JPanel panel;
	JButton wbutton;
	public void actionPerformed(ActionEvent event)
	{
		frame = new JFrame("Bank (Tranfer)");
		frame.setVisible(true);
		frame.setSize(400,300);
		frame.setLocation(200, 230);
		
		label = new JLabel ("Account Holder Name ");
		label2 = new JLabel ("Transfer to (Account Number)  ");
		label3 = new JLabel ("Amount: $ ");
		name = new JTextField(30);
		account = new JTextField(30);
		amount2 = new JTextField(30);
		panel = new JPanel();
		wbutton = new JButton("Submit");
		
		frame.add(panel);
		panel.add(label);
		panel.add(name);
		panel.add(label2);
		panel.add(account);
		panel.add(label3);
		panel.add(amount2);
		panel.add(wbutton);

		label.setFont(new Font ("Serif", Font.PLAIN,25));
		label2.setFont(new Font ("Serif", Font.PLAIN,25));
		label3.setFont(new Font ("Serif", Font.PLAIN,26));
		wbutton.setFont(new Font ("Serif", Font.BOLD,20));
		
		readfile();
		wbutton.addActionListener(new submitActionPerformed());
	}
	private class submitActionPerformed implements ActionListener
	{
		public void actionPerformed(ActionEvent e1)
		{
			try{
			int checkForNumbers = Integer.parseInt(amount2.getText());
			checkForNumbers = Integer.parseInt(account.getText());
			int transferAmount = Integer.parseInt(amount2.getText());
			String accountNumber = account.getText();
			String accountName = name.getText();
			int temp = (amount - transferAmount);
			if(temp >= 0)
			{
				amount = temp;
				JOptionPane.showMessageDialog(null, String.format("$" + transferAmount + " transferred to " + accountName, e1.getActionCommand()));
				try
				{
					PrintWriter output2;
					output2 = new PrintWriter(new FileOutputStream("transferRecords.txt"));
					output2.println(accountNumber);
					output2.println(accountName);
					output2.println(transferAmount);
					output2.close();
				}
				catch(FileNotFoundException e)
				{
					
				}
				frame.setVisible(false);
			}
			else
			{
				amount = amount;
				JOptionPane.showMessageDialog(null, String.format("Insufficent Funds", e1.getActionCommand()));
			}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, String.format("Please enter only numbers for Account Number and Amount.", e1.getActionCommand()));
			}
			try 
			{
				PrintWriter output;
				output = new PrintWriter(new FileOutputStream("accountInformation.txt"));
				output.println(accountinfo);
				output.println(firstName);
				output.println(middleName + " " + lastName);
				output.println(amount);
				output.println(activation);
				output.close();
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("Error! Couldn't replace");
			}
		}
	}
	public void readfile()
	{
		try
		{
			 Scanner scanner = new Scanner(new FileInputStream("AccountInformation.txt"));
	         while (scanner.hasNextLine()) 
	         {
	            accountinfo = scanner.next();
	            firstName = scanner.next();
	            middleName = scanner.next();
	            lastName = scanner.next();
	            amount = scanner.nextInt();
	            activation = scanner.next();
	         }
	         scanner.close();
		}
		catch(Exception e)
		{
			
		}
	}	
}
static class changepwAction implements ActionListener
{
	String inputForPassword =null;
	String inputForNewPassword = null;
	JTextField jwithdraw;
	JTextField jwithdraw2;
	JFrame frame;
	JPanel panel;
	JPanel panel2;
	JLabel label;
	JLabel label2;
	JButton wbutton;
	JButton wbutton2;
	public void actionPerformed(ActionEvent event)
	{
		frame = new JFrame("Bank (Password Center)");
		frame.setVisible(true);
		frame.setSize(400,250);
		frame.setLocation(200, 230);
		
		label = new JLabel ("Enter your current Password:");
		jwithdraw = new JPasswordField(30);
		panel = new JPanel();
		
		frame.add(panel);
		panel.add(label);
		panel.add(jwithdraw);
		
		label2 = new JLabel ("Enter your new Password:");
		jwithdraw2 = new JPasswordField(30);
		panel2 = new JPanel();
		wbutton = new JButton("Enter");
		
		panel.add(label2);
		panel.add(jwithdraw2);
		panel.add(wbutton);
		readfile();

		wbutton.addActionListener(new enterAction());
	}
	class enterAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			inputForPassword = jwithdraw.getText();
			inputForNewPassword = jwithdraw2.getText();
			if(inputForPassword.equals(password))
			{
				password = inputForNewPassword;
				JOptionPane.showMessageDialog(null, String.format("Success!", e.getActionCommand()));
				frame.setVisible(false);
				try
				{
					PrintWriter output;
					output = new PrintWriter(new FileOutputStream("Password.txt"));
					output.println(accountinfo);
					output.println(password);
					output.close();
				}
				catch(FileNotFoundException e2)
				{
					System.out.println("Could not print info to file");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, String.format("The password is incorrect.\nPlease try again.", e.getActionCommand()));
			}
		}
	}
	public void readfile()
	{
		try
		{
			 Scanner scanner = new Scanner(new FileInputStream("Password.txt"));
	         while (scanner.hasNextLine()) 
	         {
	            accountinfo = scanner.next();
	            password = scanner.next();
	         }
	         scanner.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
	static class logoutAction implements ActionListener
	{
		JFrame frame;
		JLabel label;
		JPanel panel;
		JButton ybutton;
		JButton nbutton;
		public void actionPerformed(ActionEvent event)
		{
			frame = new JFrame("Bank (LogOut)");
			frame.setVisible(true);
			frame.setSize(400,250);
			frame.setLocation(200, 230);
			
			label = new JLabel ("Would you like to Logout?");
			panel = new JPanel();
			ybutton = new JButton("Yes");
			nbutton = new JButton("No");
			
			frame.add(panel);
			panel.add(label);
			panel.add(ybutton);
			panel.add(nbutton);
			
			ybutton.addActionListener(new ybuttonAction());
			nbutton.addActionListener(new nbuttonAction ());
		}
		class ybuttonAction implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		}
		class nbuttonAction implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				frame.setVisible(false);
			}
		}
	}
}