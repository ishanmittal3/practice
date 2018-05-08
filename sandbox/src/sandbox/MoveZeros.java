package sandbox;

import java.util.Arrays;
import java.util.stream.Collectors;

class MoveZeros {

    public static void main(String[] args) {
        int[] nums = new int[] {0, -1, 4, 0, 5, 7};
        moveZeros(nums);
        System.out.println(
            Arrays.stream(nums)
            .boxed()
            .collect(Collectors.toList()));
    }

	private static void moveZeros(int[] nums) {
		int nonZero = 0;
		for (int pos = 0; pos < nums.length; pos++) {
		    if (nums[pos] != 0) {
		        nums[nonZero++] = nums[pos];
            }
        }
        int pos = nonZero;
		while (pos < nums.length) {
		    nums[pos++] = 0;
        }
	}
}