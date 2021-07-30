/*

  Assignment:  Team Assignment - Project
  Program : CoronaVirusTracker
  Programmers: Cristian Tapiero, Zain al Thaer, Joshua Vega-Rodriguez
  Created: Apr 14, 2020

*/
package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

/**
 * @author Cristian Tapiero
 *
 */
@SuppressWarnings("serial")
public class LoginScreen extends JFrame
{

	private JPanel contentPane;
	private JTextField userName;
	private JTextField password;
	private static LoginScreen frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		  
	            try {
					JSONResponse.stringOfJason("https://covidtracking.com/api/states%22");
				} catch (ParseException | IOException | org.json.simple.parser.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        

	        EventQueue.invokeLater(new Runnable()
	        {
	            public void run()
	            {
	                try
	                {
	                    frame = new LoginScreen();
	                    frame.setVisible(true);
	                } catch (Exception e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        });
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}

	/**
	 * Create the frame.
	 */
	public LoginScreen()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("CORONA VIRUS DATA \r\n");
		createTitle(lblTitle);
		contentPane.add(lblTitle);

		JLabel lblUserName = new JLabel("UserName:");
		createUserName(lblUserName);
		contentPane.add(lblUserName);

		JLabel lblNewLabel = new JLabel("Login to see the latest updates in your State");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		createSubtitle(lblNewLabel);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password:");
		createPassword(lblPassword);
		contentPane.add(lblPassword);

		userName = new JTextField();
		createTextFieldUserName();
		contentPane.add(userName);

		password = new JPasswordField();
		createTextFieldPassword();
		contentPane.add(password);

		//JLabel lblNewLabel_1 = new JLabel("");
		//createLogo(lblNewLabel_1);

		btnSubmit();
	}

	/**
	 * Creates a JButton that verifies the Username and Password at runtime
	 * 
	 * If successful, it will then open the CoronaData frame
	 */
	public void btnSubmit()
	{
		JButton btnSubmit = new JButton("Login");
		createSubmitButton(btnSubmit);
		contentPane.add(btnSubmit);

		btnSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				Login loginName = new Login("corona", "Covid19");

				if (loginName.getPassword().equals(password.getText())
						&& loginName.getUserName().equals(userName.getText()))
				{

					JOptionPane.showMessageDialog(contentPane, String.format("Welcome %s!", loginName.getUserName()));
					
					frame.dispose();

					CoronaData showData = new CoronaData();
					showData.setVisible(true);

				} else
				{

					JOptionPane.showMessageDialog(contentPane, "Incorrect Password or UserName");

				}

			}
		});
	}

	/**
	 * @param lblNewLabel_1
	 */
	@SuppressWarnings("unused")
	private void createLogo(JLabel lblNewLabel_1)
	{
		lblNewLabel_1.setBounds(353, 16, 52, 52);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(LoginScreen.class.getResource("/login/images/coronaLogo2.png")));
	}

	/**
	 * 
	 */
	private void createTextFieldPassword()
	{
		password.setBackground(Color.LIGHT_GRAY);
		password.setColumns(10);
		password.setBounds(27, 163, 202, 20);
	}

	/**
	 * 
	 */
	private void createTextFieldUserName()
	{
		userName.setBackground(Color.LIGHT_GRAY);
		userName.setBounds(27, 101, 202, 20);

		userName.setColumns(10);
	}

	/**
	 * @param lblPassword
	 */
	private void createPassword(JLabel lblPassword)
	{
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(27, 132, 129, 20);

		lblPassword.setVerticalAlignment(SwingConstants.BOTTOM);
	}

	/**
	 * @param btnSubmit
	 */
	private void createSubmitButton(JButton btnSubmit)
	{
		btnSubmit.setBounds(27, 216, 65, 23);

		btnSubmit.setHorizontalTextPosition(SwingConstants.LEFT);
		btnSubmit.setHorizontalAlignment(SwingConstants.LEFT);
	}

	/**
	 * @param lblNewLabel
	 */
	private void createSubtitle(JLabel lblNewLabel)
	{
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.ITALIC, 12));
		lblNewLabel.setBounds(26, 37, 257, 20);
	}

	/**
	 * @param lblUserName
	 */
	private void createUserName(JLabel lblUserName)
	{
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserName.setBounds(27, 69, 312, 20);
		lblUserName.setMaximumSize(new Dimension(53, 6));
		lblUserName.setMinimumSize(new Dimension(53, 6));
		lblUserName.setVerticalAlignment(SwingConstants.BOTTOM);
	}

	/**
	 * @param lblTitle
	 */
	private void createTitle(JLabel lblTitle)
	{
		lblTitle.setBounds(27, 11, 424, 31);
		lblTitle.setVerticalTextPosition(SwingConstants.TOP);
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 25));
	}
}
