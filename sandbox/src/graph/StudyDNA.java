package graph;

import java.util.*;

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

	List<Person> persons;

	public StudyDNA() {
		// A - B,C
		// B - D,E;   C - F,G
		// X - Y,Z
		// Y - P,Q;   Z - R,G
		Person A = new Person("A", "B", "C", 100L);
		Person B = new Person("B", "D", "E", 200L);
		Person C = new Person("C", "F", "G", 250L);
		Person D = new Person("D", null, null, 310L);
		Person E = new Person("E", null, null, 320L);
		Person F = new Person("F", null, null, 330L);
		Person G = new Person("G", null, null, 340L);

		Person X = new Person("X", "Y", "Z", 105L);
		Person Y = new Person("Y", "P", "Q", 205L);
		Person Z = new Person("Z", "R", "G", 255L);
		Person P = new Person("P", null, null, 350L);
		Person Q = new Person("Q", null, null, 360L);
		Person R = new Person("R", null, null, 370L);
		Person S = new Person("S", null, null, 380L);

		this.persons = Arrays.asList(A, B, C, D, E, F, G, X, Y, Z, P, Q, R, S);
	}

	public static void main(String[] args) {
		StudyDNA sol = new StudyDNA();
		System.out.println(sol.hasCommonAncestor("A", "X"));
		System.out.println(sol.hasCommonAncestor("X", "G"));
		System.out.println(sol.hasCommonAncestor("G", "G"));
		System.out.println(sol.hasCommonAncestor("B", "X"));
	}
	
	private Map<String, Node> getPersonsMap(List<Person> persons) {
		Map<String, Node> map = new HashMap<>();
		for (Person person : persons) {
			String name = person.name;
			Node node = map.containsKey(name)
						? map.get(name)
						: new Node(name);
			Node parent1 = null;
			Node parent2 = null;
			if (person.parent1 != null) {
				parent1 = map.getOrDefault(person.parent1, new Node(person.parent1));
				map.put(person.parent1, parent1);
			}
			if (person.parent2 != null) {
				parent2 = map.getOrDefault(person.parent2, new Node(person.parent2));
				map.put(person.parent2, parent2);
			}
			node.parent1 = parent1;
			node.parent2 = parent2;
			node.dob = person.dob;
			map.put(name, node);
			/* If there is a loop in the graph, 
			 * there will be at least one edge
			 * where the parent will be younger than the child
			 * And that parent would've been initialized (with its dob) already
			 * */
			if (((parent1 != null) && (parent1.dob <= person.dob))
					|| ((parent2 != null) && (parent2.dob <= person.dob))) {
				// loop
			}
		}
		return map;
	}
	
	Set<Node> getAncestors(Node node) {
		Set<Node> ancestors = new HashSet<>();
		ancestors.add(node);
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
		Map<String, Node> personsMap = getPersonsMap(persons);
		Node node1 = personsMap.get(name1);
		Node node2 = personsMap.get(name2);
		Set<Node> ancestors1 = getAncestors(node1);
		Set<Node> ancestors2 = getAncestors(node2);
		for (Node ancestor1 : ancestors1) {
			if (ancestors2.contains(ancestor1)) {
				return true;
			}
		}
		return false;
	}

	boolean hasCommonAncestor2(String name1, String name2) {
		Map<String, Node> personsMap = getPersonsMap(persons);
		Node node1 = personsMap.get(name1);
		Node node2 = personsMap.get(name2);
		PriorityQueue<Node> pq1 = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				return (int) (node1.dob - node2.dob);
			}
		});
		PriorityQueue<Node> pq2 = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				return (int) (node1.dob - node2.dob);
			}
		});
		pq1.add(node1);
		pq2.add(node2);
		while (!pq1.isEmpty() && !pq2.isEmpty()) {
			Node curr1 = pq1.peek();
			Node curr2 = pq2.peek();
			if (curr1.name.equals(curr2.name)) {
				return true;
			}
			if (curr1.dob < curr2.dob) {
				pq1.poll();
				if (curr1.parent1 != null) {
					pq1.add(curr1.parent1);
				}
				if (curr1.parent2 != null) {
					pq1.add(curr1.parent2);
				}
			} else {
				pq2.poll();
				if (curr2.parent1 != null) {
					pq2.add(curr2.parent1);
				}
				if (curr2.parent2 != null) {
					pq2.add(curr2.parent2);
				}
			}
		}
		return false;
	}
	
	class Node {
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

		public Person(String name, String parent1, String parent2, long dob) {
			this.name = name;
			this.parent1 = parent1;
			this.parent2 = parent2;
			this.dob = dob;
		}
	}
}
