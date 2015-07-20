package searching;

public class CyclicallySortedArray {
	int findMin(int[] nums) {
		return findMin(nums, 0, nums.length-1);
	}
	
	// Assuming no duplicates
	int findMin(int[] nums, int start, int end) {
		if(start == end) {
			return start;
		}
		int mid = start + (end - start)/2;
		if(nums[mid] > nums[end]) {
			return findMin(nums, mid+1, end);
		} else {
			return findMin(nums, start, mid);
		}
	}
	
	int find(int[] nums, int key) {
		return find(nums, key, 0, nums.length-1);
	}
	
	int find(int[] nums, int key, int start, int end) {
		if(start == end) {
			return nums[start] == key ? start : -1;
		}
		int mid = start + (end - start)/2;
		if(nums[mid] == key) {
			return mid;
		}
		if(nums[mid] > nums[end]) { // left half is sorted
			if(key > nums[mid]) {
				// key cannot be in the left half
				// because it has all elements <= nums[mid]
				return find(nums, key, mid+1, end);
			} else {
				int leftPos = binarySearch(nums, key, start, mid);
				return leftPos >= 0 ? leftPos: 
					find(nums, key, mid+1, end);
			}
		} else { // right half is sorted
			if(key < nums[mid]) {
				// key cannot be in the right half
				// because it has all elements >= nums[mid]
				return find(nums, key, mid, end);
			} else {
				int rightPos = binarySearch(nums, key, mid+1, end);
				return rightPos >= 0 ? rightPos :
					find(nums, key, start, mid);
			}
		}
	}
	
	int binarySearch(int[] nums, int key, int start, int end) {
		if (start > end) {
	        return -1;
	    }
	    int mid = (start + end)/2;
	    if (key == nums[mid]) {
	        return mid;
	    }
	    if (key < nums[mid]) {
	        return binarySearch(nums, key, start, mid-1);
	    }
	    else {
	        return binarySearch(nums, key, mid+1, end);
	    }
	}
}
