package arrays;

public class RotateArray {

	// O(1) space, O(n) time
	<T> void rotate(T[] array, int order) {
		reverse(array, array.length - order, array.length - 1);
		reverse(array, 0, array.length - order - 1);
		reverse(array, 0, array.length - 1);
	}
	
	<T> void reverse(T[] array, int start, int end) {
		while(start < end) {
			swap(array, start++, end--);
		}
	}
	
	<T> void swap(T[] array, int pos1, int pos2) {
		T temp = array[pos1];
		array[pos1] = array[pos2];
		array[pos2] = temp;
	}
}
