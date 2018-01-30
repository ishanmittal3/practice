package sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

	// In-place sort
	public static void quickSort(int[] values) {
		quickSort(values, 0, values.length - 1);
	}

	public static void quickSort(int[] values, int start, int end) {
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
			quickSort(values, start, swapPos - 1);
			quickSort(values, swapPos + 1, end);
		} else {
			swap(values, swapPos + 1, end);
			quickSort(values, start, swapPos);
			quickSort(values, swapPos + 2, end);
		}
	}
	
	public static void quickSort2(int[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		int pivot = array[start];
		int curr = start;
		int more = end;
		
		while (curr <= more) {
			if (array[curr] <= pivot) {
				curr++;
			} else {
				swap(array, curr, more--);
			}
		}
		swap(array, start, more);
		quickSort2(array, start, more - 1);
		quickSort2(array, more + 1, end);
	}

	public static void swap(int[] values, int swapPos, int pos) {
		int temp = values[pos];
		values[pos] = values[swapPos];
		values[swapPos] = temp;
	}

	// Not in-place (in-place would be more efficient)
	public static void quickSortNotInPlace(List<Integer> nums) {
		if (nums.isEmpty() || nums.size() == 1) {
			return;
		}

		Integer pivot = nums.get(0);
		List<Integer> left  = new ArrayList<Integer> ();
		List<Integer> right = new ArrayList<Integer> ();

		for (Integer i : nums) {
			if (i < pivot) {
				left.add(i);
			}
			else {
				right.add(i);
			}
		}

		quickSortNotInPlace(left);
		quickSortNotInPlace(right);

		nums.clear();
		nums.addAll(left);
		nums.add(pivot);
		nums.addAll(right);
	}
}
