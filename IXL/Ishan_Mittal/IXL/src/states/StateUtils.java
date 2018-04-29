package states;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class StateUtils {
	
	public static final HashMap<String, String> codesToNames = generateHashMap(true);
	public static final HashMap<String, String> namesToCodes = generateHashMap(false);

	  private static HashMap<String, String> generateHashMap(boolean codeAsKey) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/states/states.txt"));
			String line = reader.readLine();
			while (line != null) {
				String[] state = line.split(" ");
				if (codeAsKey) {
					hashMap.put(state[1], state[0]);
				}
				else {
					hashMap.put(state[0], state[1]);
				}
				line = reader.readLine();
			}
			reader.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return hashMap;
	}
	  
	  //
	  // Generates an HTML select list that can be used to select a specific
	  // U.S. state.
	  //
	  public static String createStateSelectList()
	  {    
		  PriorityQueue<String> states = new PriorityQueue<String>(namesToCodes.keySet());
		  
		  String result = "<select name=\"state\">\n";

		  while (!states.isEmpty()) {
			  String state = states.poll();
			  result = result + "<option value=\"" + state + "\">" + state + "</option>\n";
		  }
		  result = result + "</select>\n";
		  return result;
	  }

	  //
	  // Parses the state from an HTML form submission, converting it to
	  // the two-letter abbreviation. We need to store the two-letter abbreviation
	  // in our database.
	  //
	  public static String parseSelectedState(String s)
	  {
		  return namesToCodes.get(s);
	  }

	  //
	  // Displays the full name of the state specified by the two-letter code.
	  //
	  public static String displayStateFullName(String abbr) 
	  {
		  return codesToNames.get(abbr);
	  }
	  
}
