package sandbox;

import java.util.ArrayList;
import java.util.List;

public class Combination {

	<T> List<List<T>> choose(T[] array, int count, int start) {
		List<List<T>> result = new ArrayList<List<T>>();
		if(start >= array.length || count <= 0 ||
				start + count - 1 >= array.length) {
			return result;
		}
		result.addAll(choose(array, count, start + 1));
		List<List<T>> lists = choose(array, count - 1, start + 1);
		for(List<T> list : lists) {
			list.add(0, array[start]);
			result.add(list);
		}
		return result;
	}
}
