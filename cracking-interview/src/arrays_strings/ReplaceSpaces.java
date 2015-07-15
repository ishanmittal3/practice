package arrays_strings;

public class ReplaceSpaces {

	/**
	 * Replaces spaces in a given string with '%20'
	 * @param input
	 * @return
	 */
	public static String replaceSpaces(String input) {
		if (input.isEmpty()) {
			return input;
		}
		
		String[] subStrings = input.split(" ");
		int numSubStrings = subStrings.length;
		StringBuffer output = new StringBuffer();
		
		for (int index = 0; index < numSubStrings; index++) {
			output.append(subStrings[index]);
			if (index != numSubStrings -1) {
				output.append("%20");
			}
		}
		
		if (input.charAt(input.length() -1) == ' ') {
			output.append("%20");
		}
		
		return output.toString();
	}
	
}
