package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a set of people and their parents, 
 * find if two given people have a common ancestor.
 * Assume people's names are unique 
 * and each person's DoB is also known.
 * 
 * Follow up:
 * If there are a lot of nodes, we need not find all the ancestors.
 * We can compute a few generations at a time (based on DoB).
 * The graph for the younger (of the two inputs) would need 
 * to be explored more (till more generations)
 * than the older one - to reach the common ancestor.
 */
public class StudyDNA {

	static Map<String, Node> persons;
	
	static Map<String, Node> getPersons(List<Person> persons) {
		Map<String, Node> map = new HashMap<>();
		for (Person person : persons) {
			String name = person.name;
			Node node = map.containsKey(name)
						? map.get(name)
						: new Node(name);
			Node parent1 = map.containsKey(person.parent1)
						   ? map.get(person.parent1)
						   : new Node(person.parent1);
		    Node parent2 = map.containsKey(person.parent2)
						   ? map.get(person.parent2)
						   : new Node(person.parent2);
			node.parent1 = parent1;
			node.parent2 = parent2;
			node.dob = person.dob;
			map.put(name, node);
			/* If there is a loop in the graph, 
			 * there will be at least one edge
			 * where the parent will be younger than the child
			 * And that parent would've been initialized (with its dob) already
			 * */
			if (((parent1.dob > 0) && (parent1.dob <= person.dob))
					|| ((parent2.dob > 0) && (parent2.dob <= person.dob))) {
				// loop
			}
		}
		return map;
	}
	
	Set<Node> getAncestors(Node node) {
		Set<Node> ancestors = new HashSet<>();
		Node parent1 = node.parent1;
		if (parent1 != null) {
			ancestors.add(parent1);
			ancestors.addAll(getAncestors(parent1));
		}
		Node parent2 = node.parent2;
		if (parent2 != null) {
			ancestors.add(parent2);
			ancestors.addAll(getAncestors(parent2));
		}
		return ancestors;
	}
	
	boolean hasCommonAncestor(String name1, String name2) {
		Node node1 = persons.get(name1);
		Node node2 = persons.get(name2);
		Set<Node> ancestors1 = getAncestors(node1);
		Set<Node> ancestors2 = getAncestors(node2);
		for (Node ancestor1 : ancestors1) {
			if (ancestors2.contains(ancestor1)) {
				return true;
			}
		}
		return false;
	}
	
	static class Node {
		String name;
		long dob;
		Node parent1;
		Node parent2;
		
		public Node(String name) {
			this.name = name;
		}
	}
	
	class Person {
		String name;
		String parent1;
		String parent2;
		long dob;
	}
}
