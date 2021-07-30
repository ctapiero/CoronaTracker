/*

  Assignment:  Team Assignment - Project
  Program : CoronaVirusTracker
  Programmers: Cristian Tapiero, Zain al Thaer, Joshua Vega-Rodriguez
  Created: Apr 14, 2020

*/
/**
 * @author 
 */
package login;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class CoronaData extends JFrame
{

	/*
	 * 
	 * ********** REMOVED STUFF **************
	 * 
	 * Removed private field statesOfAmerica, instead we call the method
	 * stateArray()
	 * 
	 * Removed List<String> states, cases, and deaths as private fields, they are
	 * better utilized as local variables within the submitButton method
	 * 
	 * Previously, we used JLabels to display all of the data to the user. Decided
	 * to use a JTextArea that allows for more output without adding additional
	 * components
	 * 
	 * There were some imports that were not used in this program that were
	 * imported. I removed them. Not sure if its the same for you guys, i'll provide
	 * a list:
	 * 
	 * import java.util.ArrayList; import javax.swing.JSpinner;
	 * importjavax.swing.JList; import javax.swing.JToggleButton; import
	 * java.awt.Component;
	 * 
	 * 
	 * ********** END ************************
	 * 
	 * There was a private method that i converted to PUBLIC. I do not remember
	 * which one but I would watch out for that
	 * 
	 * Check methods: stateArray(), createDropMenu(String[] stateArray),
	 * returnResults(JComboBox comboBox, JTextArea textArea)
	 * 
	 * 
	 */
	private static CoronaData frame;
	private static JPanel contentPane;

	// Instantiating local variables with fields from class JSONResponse
	private List<String> states = JSONResponse.covid19;
	private List<String> cases = JSONResponse.covid19Cases;
	private List<String> deaths = JSONResponse.covid19Deaths;

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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					frame = new CoronaData();
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
	public CoronaData()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel selectLabel = new JLabel("Select State:");
		selectLabel.setFont(new Font("Unispace", Font.BOLD, 18));
		selectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectLabel.setBounds(118, 11, 154, 37);
		contentPane.add(selectLabel);

		JComboBox<String> stateBox = createDropMenu(stateArray()); // added stateArray as String[] argument
		
		JTextArea displayData = new JTextArea();
		displayData.setBackground(new Color(70, 130, 180));
		displayData.setTabSize(10);
		displayData.setEditable(false);
		displayData.setBounds(24, 151, 167, 81);
		contentPane.add(displayData);

		//JComboBox stateBox = createDropMenu(stateArray()); // added stateArray as String[] argument

		submitButton(stateBox, displayData);

		compareButton();
		
	 saveButton(stateBox);
		
	}

	/**
	 * 
	 * Creates a JButton and implements an Action Listener
	 * 
	 * @param comboBox
	 * @param textArea
	 */
	public void submitButton(JComboBox<String> comboBox, JTextArea textArea)
	{

		JButton submit = new JButton("Submit");
		submit.setBounds(275, 83, 89, 23);
		contentPane.add(submit);

		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				returnResults(comboBox, textArea);
			}

		});
	}

	/**
	 * The ActionListener matches the index from the state list to the index of 3
	 * different Lists. Each List of type String holds the corresponding data for
	 * each state, such as number of positive cases and deaths.
	 * 
	 * @param comboBox
	 * @param textArea
	 * 
	 */
	public void returnResults(JComboBox<String> comboBox, JTextArea textArea)
	{
		/*
		 * 
		 * Removed statesOfAmerica and used stateArray()
		 * 
		 * Passing comboBox and textArea as parameters because this allows for easier
		 * implementation of THIS EXACT SAME FUNCTION in class CompareStates
		 * 
		 * 
		 * CompareStates uses 2 separate JComboBoxes and JTextAreas. By passing those
		 * components as parameters, it is easier to call and display the data for each
		 * individual object.
		 * 
		 * Look at the method submitButton in class CompareStates, you will see that
		 * this method is called inside of the ActionListener and each component is
		 * passed as a parameter
		 */
		for (int j = 0; j < stateArray().length; j++)
		{
			if (comboBox.getSelectedIndex() == j)
			{
				textArea.setText(String.format( "Total Cases: %s \n" + "Total Deaths: %s",
						cases.get(j).toString(), deaths.get(j).toString()));

			}

		}
	}

	/**
	 * Creates a JButton that closes the current CoronaData window and opens a new
	 * window of CompareStates
	 * 
	 */
	public JButton compareButton()
	{
		JButton compare = new JButton("Compare");
		compare.setBounds(275, 117, 89, 23);
		contentPane.add(compare);

		compare.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// Ending current CoronaData frame and executing Compare class.
				//frame.dispose();

				CompareStates open = new CompareStates();
				open.setVisible(true);
			}

		});

		return compare;
	}

	/**
	 * Creates a JComboBox with a list of US states
	 * 
	 * @return a JComboBox
	 */
	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	public static JComboBox<String> createDropMenu(String[] stateArray)
	{

		/*
		 * Removed the private stateOfAmerica field and added a String[] argument
		 * 
		 */

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 83, 236, 22);
		contentPane.add(comboBox);

		for (int i = 0; i < stateArray.length; i++)
		{
			comboBox.addItem(stateArray[i]);
		}

		return comboBox;
	}
	 
	/**
	 * Creates a String array of U.S. states
	 * 
	 * @return an array of type String
	 */
	public static String[] stateArray()
	{
		/*
		 * previously this String[] was initialized as a private field. created a method
		 * to return the same String[] with all the states
		 * 
		 * This makes it look cleaner.
		 */

		String[] states =
		{ "Alaska", "Alabama", "Arkansas", "Arizona", "California", "Colorado", "Connecticut", "Washington DC",
				"Delaware", "Florida", "Georgia", "Hawaii", "Iowa", "Idaho", "Ilinois", "Indiana", "Kansas", "Kentucky",
				"Louisiana", "Massachusetts ", "Maryland ", "Maine ", "Michigan ", "Minnesota ", "Missouri ",
				"Mississippi ", "Montana ", "North Carolina", "North Dakota", "Nebraska", "New Hampshire", "New Jersey",
				"New Mexico", "Nevada", "New York", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
				"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Virginia ", "Vermont", "Washington",
				"Wisconsin", "West Virginia", "Wyoming", "Puerto Rico" };
		return states;
	}
	/**
     * Saves data into a txt file using the path. Gets the state, cases, and deaths.
     **/
	public JButton saveButton(JComboBox comboBox) {

		JButton save = new JButton("Save");
		save.setBounds(275, 200, 89, 23);
		contentPane.add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int save = chooser.showSaveDialog(chooser);
				File file = chooser.getSelectedFile();
				String path = file.getAbsolutePath() + File.separator;
				// Writing to file
				try (PrintWriter writer = new PrintWriter(path)) {
					for (int j = 0; j < stateArray().length; j++) {
						
							String output = String.format("State: %s \n" + "Total Cases: %s \n" + "Total Deaths: %s",
									states.get(j).toString(), cases.get(j).toString(), deaths.get(j).toString());
							writer.write(output);
							
							writer.println();
							writer.println();

							if (save == JFileChooser.APPROVE_OPTION) {
								file.getAbsoluteFile();
							

							}

						}
					JOptionPane.showMessageDialog(null, "File Saved");
						
					

				} catch (Exception el) {
					el.printStackTrace();
				}

			}
		});
		return save;
	}

	
}