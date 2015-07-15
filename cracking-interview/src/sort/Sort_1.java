package sort;

public class Sort_1 {
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
