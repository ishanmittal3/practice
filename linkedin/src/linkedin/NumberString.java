package linkedin;

public class NumberString {

	/*
	 * Returns true if the input string is a number and false otherwise
	 */
	 
	public boolean isNumber(String str)
	{
	    boolean foundDecimal = false;
	    boolean foundDigit = false;
	    for(int pos = 0; pos < str.length(); pos++) {
	        char c = str.charAt(pos);
	        if(c == '-') {
	            if(pos == 0) {
	                continue;
	            } else {
	                return false;
	            }
	        }
	        if(c == '.') {
	            if(foundDecimal) {
	                return false;
	            } else {
	                foundDecimal = true;
	            }
	            continue;
	        }
	        if(c < '0' || c > '9') {
	            return false;
	        } else {
	            foundDigit = true;
	        }
	    }
	    return foundDigit;
	}
}
