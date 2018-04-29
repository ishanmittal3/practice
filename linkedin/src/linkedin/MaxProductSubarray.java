package linkedin;

public class MaxProductSubarray {

	public int maxProduct(int[] nums) {
        int currMax = nums[0]; // max product ending at curr pos
        int currMin = nums[0]; // min product ending at curr pos
        // We need currMin because if nums[pos] < 0 and currMin < 0
        // their product could exceed currMax * nums[pos]
        int max = currMax;

        for(int pos = 1; pos < nums.length; pos++) {
        	int curr = nums[pos];
			int newMax = max(curr, curr * currMax, curr * currMin);
			int newMin = min(curr, curr * currMax, curr * currMin);
			currMax = newMax;
			currMin = newMin;
            if(currMax > max) {
            	max = currMax;
            }
        }
        return max;
    }
	
	public Pair maxProductRange(int[] nums) {
        int currMax = nums[0]; // max product ending at curr pos
        int currMin = nums[0]; // min product ending at curr pos
        // We need currMin because if nums[pos] < 0 and currMin < 0
        // their product could exceed currMax * nums[pos]
        int max = currMax;
        int startPosMax = 0;
        int startPosMin = 0;
        int startPos = 0, endPos = 0;

        for(int pos = 1; pos < nums.length; pos++) {
        	int curr = nums[pos];
			
        	int newMax = max(curr, curr * currMax, curr * currMin);
			if(curr == newMax) {
				startPosMax = pos;
			}
			if(curr * currMin == newMax) {
				startPosMax = startPosMin;
			}
			
			int newMin = min(curr, curr * currMax, curr * currMin);
			if(curr == newMin) {
				startPosMin = pos;
			}
			if(curr * currMax == newMin) {
				startPosMin = startPosMax;
			}
			
			currMax = newMax;
			currMin = newMin;
			
            if(currMax > max) {
            	max = currMax;
            	startPos = startPosMax;
            	endPos = pos;
            }
        }
        return new Pair(startPos, endPos);
    }
	
	int max(int ...nums) {
		int max = nums[0];
		for(int num : nums) {
			max = Math.max(max, num);
		}
		return max;
	}
	
	int min(int ...nums) {
		int min = nums[0];
		for(int num : nums) {
			min = Math.min(min, num);
		}
		return min;
	}
	
	static class Pair {
		int start;
		int end;
		
		Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
