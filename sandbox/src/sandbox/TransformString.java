package sandbox;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TransformString {
	
	/**
	 * Given a source word, target word and an English dictionary, 
	 * transform the source word to target by changing/adding/removing 
	 * 1 character at a time, while all intermediate words being 
	 * valid English words. Return the transformation chain which has the 
	 * smallest number of intermediate words
	 * @param start
	 * @param end
	 * @param dict
	 * @return
	 */
	List<String> transform(String start, String end, Set<String> dict) {
		Map<String, Set<String>> graph = generateGraph(dict);
		Queue<List<String>> q = new ArrayDeque<List<String>>();
		List<String> initPath = new ArrayList<String>();
		initPath.add(start);
		Set<String> visited = new HashSet<String>();
		
		while(!q.isEmpty()) {
			List<String> currPath = q.poll();
			String curr = currPath.get(currPath.size() - 1);
			visited.add(curr);
			if(end.equals(curr)) {
				return currPath;
			}
			for(String neighbor : graph.get(curr)) {
				if (!visited.contains(neighbor)) {
					List<String> newPath = new ArrayList<String>(currPath);
					newPath.add(neighbor);
					q.add(newPath);
				}
			}
		}
		return null;
	}

	private Map<String, Set<String>> generateGraph(Set<String> dict) {
		Map<String, Set<String>> graph = new HashMap<String, Set<String>>();
		for(String word : dict) {
			graph.put(word, new HashSet<String>());
			for(int pos = 0; pos < word.length(); pos++) {
				for(char c = 'a'; c <= 'z'; c++) {
					// substitute a character
					String newWord = word.substring(0, pos) + c + word.substring(pos+1);
					if(dict.contains(newWord)) {
						graph.get(word).add(newWord);
					}
					// add a character
					newWord = word.substring(0, pos) + c + word.substring(pos);
					if(dict.contains(newWord)) {
						graph.get(word).add(newWord);
					}
				}
				// delete a character
				String newWord = word.substring(0, pos) + word.substring(pos+1);
				if(dict.contains(newWord)) {
					graph.get(word).add(newWord);
				}
			}
			for(char c = 'a'; c <= 'z'; c++) {
				String newWord = c + word;
				if(dict.contains(newWord)) {
					graph.get(word).add(newWord);
				}
				newWord = word + c;
				if(dict.contains(newWord)) {
					graph.get(word).add(newWord);
				}
			}
		}
		return graph;
	}
}
