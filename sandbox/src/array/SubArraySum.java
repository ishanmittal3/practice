package array;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {

    /**
     * Given an array of integers, find a sub-array having the given sum in it
     */
    int[] getSubArray(int[] nums, int sum) {
        Map<Integer, Integer> seenSums = new HashMap<>();
        int currSum = 0;
        int start = 0;
        int end = 0;
        for (int pos = 0; pos < nums.length; pos++) {
            currSum += nums[pos];
            int prevSum = currSum - sum;
            // If for some previous position,
            // prevSum = currSum - sum
            // => currSum - prevSum = sum, for that position
            if (seenSums.containsKey(prevSum)) {
                start = 1 + seenSums.get(prevSum);
                end = pos;
                break;
            }
            seenSums.put(currSum, pos);
        }
        return getSubArray(nums, start, end);
    }

    private int[] getSubArray(int[] nums, int start, int end) {
        int[] subArray = new int[end - start + 1];
        for (int pos = 0; pos < subArray.length; pos++) {
            subArray[pos] = nums[pos + start];
        }
        return subArray;
    }
}
