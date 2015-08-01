package sandbox;

public class NumbersWithSum {

	/**
	 * @param nums - an array sorted in increasing order
	 * @return Two numbers with the given sum
	 */
	Pair getNumbersWithSum(int[] nums, int sum) {
		int first = 0;
		int second = nums.length - 1;
		int currSum = nums[first] + nums[second];
		while(currSum != sum && first < second) {
			if(currSum < sum) {
				first++;
			} else {
				second--;
			}
			currSum = nums[first] + nums[second];
		}
		if(currSum == sum) {
			return new Pair(first, second);
		} else {
			return null;
		}
	}
	
	static class Pair {
		int first;
		int second;
		
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
		
	}
}
