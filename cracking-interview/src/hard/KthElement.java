package hard;

// 20.6
public class KthElement {
	
	Integer find(int[] nums, int rank) {
		return find(nums, rank, 0, nums.length);
	}
	
	Integer find(int[] nums, int rank, int start, int end) {
		if(start > end) {
			return null;
		}
		if(start == end) {
			if(rank == 1) {
				return nums[start];
			} else {
				return null;
			}
		}
		int pivot = start;
		int endPos = end;
		int currPos = start + 1;
		while(currPos < endPos) {
			if(nums[currPos] > nums[start]) {
				swap(nums, currPos, endPos--);
			} else {
				currPos++;
			}
		}
		if(nums[start] > nums[currPos]) {
			pivot = currPos;
		} else {
			pivot = currPos - 1;
		}
		swap(nums, start, pivot);
		
		int pivotRank = pivot - start + 1;
		if(pivotRank == rank) {
			return nums[pivot];
		}
		if(pivotRank < rank) {
			return find(nums, rank-pivotRank, pivot+1, end);
		} else {
			return find(nums, rank, start, pivot-1);
		}
	}

	private void swap(int[] nums, int pos1, int pos2) {
		int temp = nums[pos1];
		nums[pos1] = nums[pos2];
		nums[pos2] = temp;
	}
}
