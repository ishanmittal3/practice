package arrays;

public class MajorityElement {

	/**
	 * Given an array of size n, find the majority element. 
	 * The majority element is the element that appears 
	 * more than ⌊ n/2 ⌋ times. 
	 * (assume that the array is non-empty and 
	 * the majority element always exist in the array.)
	 */
	<T> T getMajorityElement(T[] array) {
		int count = 0;
		T result = null;
		for(T curr : array) {
			if(count == 0) {
				result = curr;
				count++;
			} else if(curr == result) {
				count++;
			} else {
				count--;
			}
		}
		// count will remain > 0 for the majority element
		return result;
	}
}
