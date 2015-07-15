package sort;

public class BubbleSort {
	public static void bubbleSort(int[] array) {

	    int len = array.length;

	    for (int index = 1; index < len; index++) {
	        int currIndex = index;
	        while (currIndex > 0)   {
	            if (array[currIndex] < array[currIndex-1]) {
	                swap(array, currIndex, currIndex-1);
	            }
	            currIndex--;
	        }
	    }
	}

	public static void swap(int[] array, int pos1, int pos2) {

	    if (pos1 >= array.length || pos2 >= array.length) {
	        return;
	    }

	    int tmp = array[pos1];
	    array[pos1] = array[pos2];
	    array[pos2] = tmp;
	}

}
