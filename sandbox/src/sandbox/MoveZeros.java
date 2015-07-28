package sandbox;

class MoveZeros {

	void moveZeros(int[] nums) {
		int pos1 = 0;
		int pos2 = 0;

		while(pos2 < nums.length) {
			if(nums[pos1] == 0) {
				if(nums[pos2] == 0) {
					pos2++;
				} else {
					nums[pos1++] = nums[pos2++];
				}
			} else {
				pos1++;
				pos2++;
			}
		}

		for(pos2 = 0; pos2 < nums.length; pos2++) {
			if(nums[pos1] == 0) {
				if(nums[pos2] != 0) {
					nums[pos1++] = nums[pos2];
				}
			} else {
				pos1++;
			}
		}
	}
}