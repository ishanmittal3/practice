package sandbox;

public class MaxSurpassers {

	/**
	 * For an array element, a surpasser for an element that is 
	 * to the right of it and larger than it.
	 * Given an array, find the maximum number of surpassers that an 
	 * element has
	 */
	static int maxSurpassers(int[] nums) {
		int maxSurpassers = 0;
		Node root = new Node(nums[nums.length - 1], 0, 0);
		for(int pos = nums.length - 2; pos >= 0; pos--) {
			int num = nums[pos];
			int rightCount = 0;
			Node curr = root;
			while(curr != null) {
				if(num < curr.value) {
					rightCount += curr.rightBranchLength;
					if(curr.left == null) {
						curr.left = new Node(num, 1 + curr.leftMoves, 0);
						maxSurpassers = Math.max(maxSurpassers, curr.leftMoves + 1 + rightCount);
						break;
					} else {
						curr = curr.left;
					}
				} else {
					curr.rightBranchLength++;
					if(curr.right == null) {
						curr.right = new Node(num, curr.leftMoves, 0);
						maxSurpassers = Math.max(maxSurpassers, curr.leftMoves + rightCount);
						break;
					} else {
						curr = curr.right;
					}
				}
			}
		}
		return maxSurpassers;
	}
	
	static class Node {
		int value;
		Node left;
		Node right;
		int leftMoves;
		int rightBranchLength;
		
		Node(int value, int leftMoves, int rightBranchLength) {
			this.value = value;
			this.leftMoves = leftMoves;
			this.rightBranchLength = rightBranchLength;
		}
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {82, 74, 17, 93, 96, 20, 25, 55, 15, 24, 25, 56};
		System.out.println(maxSurpassers(nums));
	}
}
