package login;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class CompareStates extends JFrame
{
	/*
	 * This class is used in order to compare 2 different US states in respect to
	 * the Covid-19
	 * 
	 * Field stateOfAmerica is not used. Instead we use the method stateArray()
	 * 
	 * 
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -8754592805923565360L;
	/**
	 * 
	 */
	
	private static JPanel contentPane;
	private   static JComboBox<String> firstStateBox;
	private static JComboBox<String> secondStateBox;
	private JTextArea textArea;
	private JTextArea textArea_1;

	private CoronaData callCoronaMethod = new CoronaData();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{

		// API call
		try
		{
			JSONResponse.stringOfJason("https://covidtracking.com/api/states");
		} catch (ParseException | IOException | org.json.simple.parser.ParseException e1)
		{
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					CompareStates frame = new CompareStates();
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
	public CompareStates()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel compareLabel = new JLabel("Compare States");
		compareLabel.setFont(new Font("Unispace", Font.BOLD, 20));
		compareLabel.setHorizontalAlignment(SwingConstants.CENTER);
		compareLabel.setBounds(128, 11, 171, 32);
		contentPane.add(compareLabel);

		stateComboBoxes(); // Drop down menus

		textAreas(); // TextAreas to display data

		submitButton(); // calls a method and

	}

	/**
	 * Creates a JButton and implements an Action Listener
	 * 
	 * Calls the method returnResults from class CoronaData, which allows the users
	 * to compare the data from 2 different US states.
	 */
	public void submitButton()
	{
		JButton submit = new JButton("Submit");
		submit.setBounds(75, 213, 89, 23);
		contentPane.add(submit);

		submit.addActionListener(new ActionListener()
		{

			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{
				callCoronaMethod.returnResults(firstStateBox, textArea);
				callCoronaMethod.returnResults(secondStateBox, textArea_1);
			}
		});
	}

	/**
	 * Creates to individual JComboBoxes loaded with a list of US states
	 * 
	 */
	public void stateComboBoxes()
	{
		firstStateBox = CoronaData.createDropMenu(CoronaData.stateArray());
		firstStateBox.setBounds(25, 65, 187, 20);
		contentPane.add(firstStateBox);

		secondStateBox = CoronaData.createDropMenu(CoronaData.stateArray());
		secondStateBox.setBounds(25, 153, 187, 20);
		contentPane.add(secondStateBox);
	}

	/**
	 * Creates 2 separate JTextAreas that display the data when a state is chosen
	 * 
	 */
	public void textAreas()
	{
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBackground(new Color(70, 130, 180));
		textArea.setBounds(278, 66, 128, 55);
		contentPane.add(textArea);

		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea_1.setBackground(new Color(70, 130, 180));
		textArea_1.setBounds(278, 157, 128, 55);
		contentPane.add(textArea_1);
	}

 

}
