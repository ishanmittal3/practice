package sandbox;

/**
 * There are n houses built in a line, 
 * each of which contains some value in it. 
 * A thief is going to steal the maximal value 
 * in these houses, but he cannot steal in two 
 * adjacent houses because the owner of a 
 * stolen house will tell his two neighbors 
 * on the left and right side. 
 * What is the maximal stolen value?
 */
public class MaxValueHouses {

	int maxValue(int[] values) {
		if(values.length == 1) {
			return values[0];
		}
		if(values.length == 2) {
			return Math.max(values[0], values[1]);
		}
		
		int prePrev = values[0];
		int prev = Math.max(prePrev, values[1]);
		int curr = 0;
		for(int pos = 2; pos < values.length; pos++) {
			curr = Math.max(values[pos] + prePrev, prev);
		}
		return curr;
	}
}
