package linkedin;

import java.util.ArrayList;
import java.util.List;

public class RomanNumbers {

	/**
	 * @param num < 5000
	 * @return Equivalent Roman number
	 */
	String convertToRoman(int num) {
		String result = "";
		List<RomanValue> romans = romans();

		for(int pos = 0; pos < romans.size(); pos++) {
			while(num > romans.get(pos).value) {
				result += romans.get(pos).roman;
				num -= romans.get(pos).value;
			}
			if(romans.get(pos).value == 1) {
				break;
			}
			if(num >= romans.get(pos).value / 10) {
				result += romans.get(pos+2).roman;
				result += romans.get(pos).roman;
				num -= romans.get(pos).value;
			}
			pos++;
			if(num > romans.get(pos).value) {
				result += romans.get(pos).roman;
				num -= romans.get(pos).value;
			}
		}
		return result;
	}

	int convertFromRoman(String str) {
		int result = 0;
		List<RomanValue> romans = romans();

		for(int pos = 0; pos < str.length(); pos++) {
			char curr = str.charAt(pos);
			if(pos < str.length() - 1) {
				char next = str.charAt(pos + 1);
				if(romans.get(curr).value < romans.get(next).value) {
					result += romans.get(next).value - romans.get(curr).value;
					pos++;
				} else {
					result += romans.get(curr).value;
				}
			} else {
				result += romans.get(curr).value;
			}
		}
		return result;
	}

	List<RomanValue> romans() {
		List<RomanValue> romans = new ArrayList<RomanValue>();
		romans.add(new RomanValue('M', 1000));
		romans.add(new RomanValue('D', 500));
		romans.add(new RomanValue('C', 100));
		romans.add(new RomanValue('L', 50));
		romans.add(new RomanValue('X', 10));
		romans.add(new RomanValue('V', 5));
		romans.add(new RomanValue('I', 1));
		return romans;
	}

	static class RomanValue {
		char roman;
		int value;
		
		RomanValue(char roman, int value) {
			this.roman = roman;
			this.value = value;
		}
	}
}
