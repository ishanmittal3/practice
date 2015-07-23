package sort;

public class MergeSort {
	public static int[] mergeSort(int[] values) {
		return mergeSort(values, 0, values.length-1);
	}
	
	private static int[] mergeSort(int[] values, int start, int end) {
		if(start > end) {
			return new int[] {};
		}
		
		if (start == end) {
			return new int[] {values[start]};
		}
		int mid = (start+end)/2;
		return merge(mergeSort(values, start, mid), mergeSort(values, mid+1, end));
	}
	
	public static int[] merge(int[] array1, int[] array2) {
	    int length1 = array1.length;
	    int length2 = array2.length;

	    int[] result = new int[length1 + length2] ;
	    
	    int curr1 = 0;
	    int curr2 = 0;
	    int currResult = 0;

	    while (curr1 < length1 && curr2 < length2) {
	        if (array1[curr1] < array2[curr2]) {
	            result[currResult++] = array1[curr1++];
	        }
	        else {
	            result[currResult++] = array2[curr2++];
	        }
	    }

	    while (curr1 < length1) {
	        result[currResult++] = array1[curr1++];
	    }

	    while (curr2 < length2) {
	        result[currResult++] = array2[curr2++];
	    }
	    return result;
	}
	
	public static int[] mergeSort (int[] array1, int[] array2) {
	    int length1 = array1.length;
	    int length2 = array2.length;
	    
	    int curr1 = length1 - 1;
	    int curr2 = length2 - 1;
	    int currResult = length1 + length2 - 1;

	    while (curr1 > 0 && curr2 > 0) {
	        if (array1[curr1] > array2[curr2]) {
	            array1[currResult] = array1[curr1];
	            curr1--;
	        }
	        else {
	            array1[currResult] = array2[curr2];
	            curr2--;
	        }
	        currResult--;
	    }

	    while (curr2 > 0) {
	        array1[currResult] = array2[curr2];
	        curr2--;
	        currResult--;
	    }

	    return array1;
	}
}
