/*

  Assignment:  Team Assignment - Project
  Program : CoronaVirusTracker
  Programmers: Cristian Tapiero, Zain al Thaer, Joshua Vega-Rodriguez
  Created: Apr 14, 2020

*/
/**
 * @author Cristian Tapiero
 */
package login;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.*;

/**
 * @author Cristian Tapiero
 *
 */
public class JSONResponse
{
	public static List<String> covid19 = new ArrayList<>();
	public static List<String> covid19Deaths = new ArrayList<>();
	public static List<String> covid19Cases = new ArrayList<>();
	public static List<String> combined = new ArrayList<String>();

	/**
	 * 
	 * Generates an API call
	 * 
	 * @param args
	 * @throws org.json.simple.parser.ParseException
	 * @throws ParseException
	 * @throws IOException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, ParseException, ClassCastException, NullPointerException
	{
		try
		{
			stringOfJason("https://covidtracking.com/api/states");
		} catch (ParseException | IOException | org.json.simple.parser.ParseException | NullPointerException e)
		{

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Establishes a connection and verifies that we are able to contact the server,
	 * it then uses a StringBuilder object to store the data from the API call and
	 * closes the connection
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void stringOfJason(String urlf)
			throws ParseException, IOException, org.json.simple.parser.ParseException
	{
		StringBuilder sb = new StringBuilder(99999999);
		urlf = "https://covidtracking.com/api/states";
		try
		{

			URL url = new URL(urlf);
			// Parse URL into HttpURLConnection in order to open the connection in order to
			// get the JSON data
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// Set the request to GET or POST as per the requirements
			conn.setRequestMethod("GET");
			// Use the connect method to create the connection bridge
			conn.connect();
			// Get the response status of the Rest API
			int responsecode = conn.getResponseCode();

			// Iterating condition to if response code is not 200 then throw a runtime
			// exception
			// else continue the actual process of getting the JSON data
			if (responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			else
			{
				// Scanner functionality will read the JSON data from the stream
				Scanner sc = new Scanner(url.openStream());

				sb.append(sc.nextLine());

				sb.append(System.getProperty("line.separator"));

				sc.close(); // Close the stream when reading the data has been finished
			}

			conn.disconnect();// Disconnect the HttpURLConnection stream

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		createArrayOfValues(sb);

	}

	/**
	 * Creates a JSONArray out of the StringBuilder object, which holds the contents
	 * of the API, and iterates through the Array
	 * 
	 * Stores the corresponding JSONObject in a String which is then added to a
	 * List<String>
	 * 
	 * @param a StringBuilder object
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	private static void createArrayOfValues(StringBuilder sb)
			throws org.json.simple.parser.ParseException, NumberFormatException
	{

		/**
		 * Changed the variable names for JSONParser and JSONArray
		 */

		JSONParser apiParser = new JSONParser();

		JSONArray apiJsonArray = (JSONArray) apiParser.parse(sb.toString());

		try
		{
			for (int i = 0; i < apiJsonArray.size(); i++)
			{

				JSONObject covid = (JSONObject) apiJsonArray.get(i);

				String states = covid.get("state").toString();
				String deaths = covid.get("death").toString();
				String cases = covid.get("positive").toString();

				covid19Deaths.add(deaths);
				covid19Cases.add(cases);
				covid19.add(states);

			}
		} catch (NullPointerException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * I dont know if this method needs to be deleted, seems like it was used for
	 * testing but i'll leave it to you, Cristian, to decide.
	 *

    **
 * @param combined
	 **/
	public static void callEachState(int index)
	{

		combined.addAll(covid19);
		combined.addAll(covid19Deaths);
		combined.addAll(covid19Cases);
	for (int i = 0; i < 52; i++)
	{
		System.out.println(
				combined.get(index) + " " + combined.get(index + 52) + " " + combined.get(index + (52 * 2)));
		index++;
	}

	}

}
