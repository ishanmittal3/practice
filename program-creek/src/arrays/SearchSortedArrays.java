package arrays;

public class SearchSortedArrays {

	// Given two sorted arrays, find the element that
	// would occur at Kth position in the (sorted) union of these arrays
	Integer findKth(int[] nums1, int[] nums2, int k) {
		if(k >= nums1.length + nums2.length) {
			return null;
		}
		return findKth(nums1, nums2, k, 0, nums1.length - 1,
				0, nums2.length - 1);
	}
	
	// Explanation: 
	// http://algorithmsandme.in/2014/12/find-kth-smallest-element-in-two-sorted-arrays/
	int findKth(int[] nums1, int[] nums2, int k, int start1, int end1,
			int start2, int end2) {
		if(start1 > end1) {
			return nums2[k];
		}
		if(start2 > end2) {
			return nums1[k];
		}
		if(k == 0) {
			return nums1[start1] < nums2[start2] ? 
					nums1[start1] : nums2[start2];
		}
		
		int mid1 = Math.min(start1 + k/2, nums1.length - 1);
		int mid2 = Math.min(start2 + k/2, nums2.length - 1);
		
		if(nums1[mid1] > nums2[mid2]) {
			// We've moved too far in nums1 and not far enough in nums2
			return findKth(nums1, nums2, k - (mid2 - start2), start1, mid1-1, 
					mid2, end2);
		} else {
			return findKth(nums1, nums2, k - (mid1 - start1), mid1, end1, 
					start2, mid2-1);
		}
	}
}
