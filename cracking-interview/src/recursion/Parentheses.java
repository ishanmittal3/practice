package recursion;

import java.util.HashSet;
import java.util.Set;

public class Parentheses {

	public static Set<String> getCombinations(int numParens) {
		return getCombinations(numParens, numParens);
	}
	
	private static Set<String> getCombinations(int numOpen, int numClosed) {
		
		HashSet<String> combinations = new HashSet<String>();
		
		if(numOpen < 0 || numClosed < 0 || numOpen > numClosed) {
			return combinations;
		}
		if(numOpen == 0 && numClosed == 0) {
			combinations.add("");
			return combinations;
		}
		
		for(String combination : getCombinations(numOpen-1, numClosed)) {
			combinations.add('(' + combination);
		}
		for(String combination : getCombinations(numOpen, numClosed-1)) {
			combinations.add(')' + combination);
		}
		return combinations;
	}
	
	public static void main(String[] args) {
		Set<String> combinations = getCombinations(3);
	}
}
