package atm;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class LoginScreen extends JFrame implements ActionListener
{
	JButton btnlogin;
	JButton sendToEmail;
	JTextField jtfuser;
	JPasswordField jpfpass;
	JLabel jlabuser, jlabpass;
	private String accountName = null;
	private String password = null;
	private String accountName2 = null;
	private String password2 = null;
	private String securityAnswer = null;
	public LoginScreen(String name)
	{
		super(name);
		readfile();
		readfile2();
		btnlogin = new JButton("Login");
		sendToEmail = new JButton("Forgot Password");
		
		jtfuser = new JTextField();
		jpfpass = new JPasswordField();
		
		jlabuser = new JLabel("Account Number:");
		jlabpass = new JLabel("Password:");

		jlabuser.setFont(new Font("Verdana", Font.PLAIN, 24));
		jlabpass.setFont(new Font("Verdana", Font.PLAIN, 24));
		btnlogin.setFont(new Font("Verdana", Font.PLAIN, 16));
		sendToEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnlogin.addActionListener(this);
		
		jlabuser.setBounds(125,10,220,220); //(x,y,width,height)
		jlabpass.setBounds(210,40,220,220);
		
		jtfuser.setBounds(340, 115, 300, 22);
		jpfpass.setBounds(340, 145, 300, 22);
		
		btnlogin.setBounds(390, 220, 150, 40);
		sendToEmail.setBounds(390, 300, 150, 40);
		
		this.add(jlabuser);
		this.add(jlabpass);
		this.add(jtfuser);
		this.add(jpfpass);
		this.add(btnlogin);
		this.add(sendToEmail);

		
		sendToEmail.addActionListener(new securityQuestion());

		this.setSize(900, 500);
		this.setLocation(200, 230);
		this.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent event)
	{
		String username = jtfuser.getText();
		char [] password = jpfpass.getPassword();
		String readPassword = new String(password);
		if(username.equals(accountName) && readPassword.equals(this.password))
		{
			setVisible(false);
			Buttons go = new Buttons();
			go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			go.setSize(900,500);
			go.setLocation(200,230);
			go.setVisible(true);
		}
		else if(username.equals(accountName2) && readPassword.equals(this.password2))
		{
			setVisible(false);
			Buttons go = new Buttons();
			go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			go.setSize(900,500);
			go.setLocation(200,230);
			go.setVisible(true);
		}
		else
		{
			JOptionPane.showMessageDialog(null, String.format("The information is wrong.", event.getActionCommand()));
		}
	}
	private class securityQuestion implements ActionListener
	{
		JFrame frame3;
		JTextField answer;
		JTextField accountNumber;
		JLabel accountNumberLabel;
		JLabel securityQuestion;
		JLabel question;
		JButton submit;
		JPanel panel2;
		public void actionPerformed(ActionEvent e2)
		{
			frame3 = new JFrame("Security Question");
			frame3.setVisible(true);
			frame3.setSize(400, 300);
			frame3.setLocation(200,230);
			
			panel2 = new JPanel();
			frame3.add(panel2);
			
			accountNumberLabel = new JLabel ("Account Number:");
			accountNumber = new JTextField (30);
			panel2.add(accountNumberLabel);
			panel2.add(accountNumber);
			
			securityQuestion = new JLabel("Security Question:");
			question = new JLabel("What is your first pet?");
			answer = new JTextField(30);
			
			submit = new JButton("Submit");
			
			panel2.add(securityQuestion);
			panel2.add(question);
			panel2.add(answer);
			panel2.add(submit);

			submit.addActionListener(new forgotActionPerformed());
		}
		private class forgotActionPerformed implements ActionListener
		{
			JFrame frame2;
			JTextField email;
			JLabel emailLabel;
			JButton enter2;
			JPanel panel;
			public void actionPerformed(ActionEvent e)
			{
				if(answer.getText().equals(securityAnswer) && accountNumber.getText().equals(accountName))
				{
				frame2 = new JFrame("Forgot Password");
				frame2.setVisible(true);
				frame2.setSize(400,300);
				frame2.setLocation(200, 230);
				
				emailLabel = new JLabel("Email:");
				email = new JTextField(30);
				panel = new JPanel();
				enter2 = new JButton("Send Password");
				
				frame2.add(panel);
				panel.add(emailLabel);
				panel.add(email);
				panel.add(enter2);
				
				enter2.addActionListener(new enter2ActionPerformed());
				}
				else
				{
					JOptionPane.showMessageDialog(null, String.format("Invalid Information", e.getActionCommand()));
				}
			}
			private class enter2ActionPerformed implements ActionListener
			{
				public void actionPerformed(ActionEvent e1)
				{
				JOptionPane.showMessageDialog(null, String.format("Sent to " + email.getText(), e1.getActionCommand()));
				frame2.setVisible(false);
				frame3.setVisible(false);
				}
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
	            accountName = scanner.next();
	            password = scanner.next();  
	            //accountName2 = scanner.next();
	           // password2 = scanner.next();  
	         }
	         scanner.close();
		}
		catch(Exception e)
		{
			
		}
	}
	public void readfile2()
	{
		try
		{
			Scanner scanner2 = new Scanner(new FileInputStream("SecurityQuestion.txt"));
			while(scanner2.hasNextLine())
	         {
	        	 securityAnswer = scanner2.next();
	         }
			scanner2.close();
		}
		catch(Exception e2)
		{
			
		}
		
	}
}

