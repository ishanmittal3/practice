package sandbox;

public class QuickSort {
	
	public static void main(String[] args) {
		int[] values = new int[] {2, -1, 5, 9, 4, 6, 5};
		sort(values);
	}
	
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
