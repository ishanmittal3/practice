package sandbox;

public class IntersectSortedArrays {

	void intersect(String[] array1, String[] array2) {
		int pos1 = 0;
		int pos2 = 0;
		
		while(pos1 < array1.length && pos2 < array2.length) {
			String str1 = array1[pos1];
			String str2 = array2[pos2];
			int diff = str1.compareTo(str2);
			if(diff == 0) {
				System.out.println(str1);
				pos1++;
				pos2++;
			}
			if(diff < 0) {
				pos1++;
			} else {
				pos2++;
			}
		}
	}
}
