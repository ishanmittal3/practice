package sandbox;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Integer.parseInt;

import java.util.HashMap;

public class ChemicalFormula {

	public static void main(String[] args) {
//		HashMap<String, Integer> map = parseMolecule("Mg(OH)2");
		HashMap<String, Integer> map = parseMolecule("K4[ON(SO3)2]2");
//		HashMap<String, Integer> map = parseMolecule("H2O");
		for (String element : map.keySet()) {
			System.out.println(element + "->" + map.get(element));
		}
	}
	
	public static HashMap<String, Integer> parseMolecule(String formula) {
		return parseMolecule(formula, 0, formula.length() - 1, 1, new HashMap<String, Integer>()); 
	}

	private static HashMap<String, Integer> parseMolecule(
			String formula, int start, int end, int count, HashMap<String, Integer> hashMap) {
		int pos = start;
		while (pos <= end) {
			int currCount = 1;
			if (isOpeningBracket(formula.charAt(pos))) {
				int endPos = findMatchingBracket(formula, pos, end);
				if (endPos < end && isDigit(formula.charAt(endPos+1))) {
					currCount = extractNumber(formula, endPos+1);
				}
				parseMolecule(formula, pos+1, endPos-1, count * currCount, hashMap);
				if (currCount == 1) {
					pos = endPos + 1;
				} else {
					pos = endPos + 1 + numDigits(currCount);
				}
			} else {
				String element = extractElement(formula, pos, end);
				pos += element.length();
				if (pos <= end && isDigit(formula.charAt(pos))) {
					currCount = extractNumber(formula, pos);
					pos += numDigits(currCount);
				}
				addToMap(hashMap, element, count * currCount);
			}
		}
		return hashMap;
	}

	private static void addToMap(HashMap<String, Integer> hashMap, String element, int count) {
		int currCount = 0;
		if (hashMap.containsKey(element)) {
			currCount = hashMap.get(element);
		}
		hashMap.put(element, currCount + count);
	}

	private static int numDigits(int num) {
		if (num == 0) {
			return 1;
		}
		int numDigits = 0;
		while (num > 0) {
			numDigits++;
			num = num/10;
		}
		return numDigits;
	}

	private static String extractElement(String formula, int pos, int end) {
		int endPos = pos + 1;
		while (endPos <= end && isLowerCase(formula.charAt(endPos))) {
			endPos++;
		}
		return formula.substring(pos, endPos);
	}

	private static int extractNumber(String formula, int pos) {
		int endPos = pos;
		while (endPos < formula.length() && isDigit(formula.charAt(endPos))) {
			endPos++;
		}
		return parseInt(formula.substring(pos, endPos));
	}

	private static int findMatchingBracket(String formula, int pos, int end) {
		char closingBracket = matchingClosingBracket(formula.charAt(pos));
		while (end > pos) {
			if (closingBracket == formula.charAt(end)) {
				return end;
			}
			end--;
		}
		return -1;
	}

	private static char matchingClosingBracket(char openingBracket) {
		char closingBracket;
		
		switch(openingBracket) {
		case '(':
			closingBracket = ')';
			break;
		case '{':
			closingBracket = '}';
			break;
		case '[':
			closingBracket = ']';
			break;
		default:
			closingBracket = ')';
		}
		
		return closingBracket;
	}
	
	private static boolean isOpeningBracket(char c) {
		return c == '(' || 
			   c == '{' ||
			   c == '[';
	}
	
}
