package sort;

import java.util.ArrayList;

public class QuickSort {
	
	// Not in-place (in-place would be more efficient)
	public static void quickSort(ArrayList<Integer> nums) {
	    if (nums.isEmpty() || nums.size() == 1) {
	        return;
	    }

	    Integer pivot = nums.get(0);
	    ArrayList<Integer> left  = new ArrayList<Integer> ();
	    ArrayList<Integer> right = new ArrayList<Integer> ();

	    for (Integer i : nums) {
	        if (i < pivot) {
	            left.add(i);
	        }
	        else {
	            right.add(i);
	        }
	    }
	    
	    quickSort(left);
	    quickSort(right);

	    nums.clear();
	    nums.addAll(left);
	    nums.add(pivot);
	    nums.addAll(right);
	}
	
	// In-place sort
	public static void sort(int[] values) {
		sort(values, 0, values.length - 1);
	}

	public static void sort(int[] values, int start, int end) {
		if (start > end) {
			return;
		}
		int pivot = values[end];
		int swapPos = end - 1;
		
		for (int pos = start; pos < swapPos; pos++) {
			if (values[pos] > pivot) {
				swap(values, swapPos, pos);
				swapPos--;
				pos--;
			}
		}
		if (values[swapPos] >= pivot) {
			swap(values, swapPos, end);
			sort(values, start, swapPos - 1);
			sort(values, swapPos + 1, end);
		} else {
			swap(values, swapPos + 1, end);
			sort(values, start, swapPos);
			sort(values, swapPos + 2, end);
		}
	}

	public static void swap(int[] values, int swapPos, int pos) {
		int temp = values[pos];
		values[pos] = values[swapPos];
		values[swapPos] = temp;
	}
}
