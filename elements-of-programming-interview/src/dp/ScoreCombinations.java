package dp;

public class ScoreCombinations {

	int countCombinations(int score, int[] points) {
		int[] counts = new int[score];
		counts[0] = 1;
		for(int point : points) {
			for(int pos = point; pos < counts.length; pos++) {
				counts[pos] += counts[pos - point];
			}
		}
		return counts[score-1];
	}
}
