package search;

public class BinarySearch {
	public static int binarySearch(int[] elements, int key) {
	    return binarySearch(elements, key, 0, elements.length - 1);
	}

	public static int binarySearch(int[] elements, int key, int start, int end) {
	    
		if (start > end) {
	        return -1;
	    }
	    
	    int middle = (start + end)/2;
	    
	    if (key == elements[middle]) {
	        return middle;
	    }

	    if (key < elements[middle]) {
	        return binarySearch(elements, key, start, middle-1);
	    }
	    else {
	        return binarySearch(elements, key, middle+1, end);
	    }
	}
}
