package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortPeople {

	static class Person implements Comparable<Person>{
		int height;
		int weight;
		
		Person(int height, int weight) {
			this.height = height;
			this.weight = weight;
		}

		@Override
		public int compareTo(Person p) {
			if(height == p.height) {
				return weight - p.weight;
			} else {
				return height - p.height;
			}
		}
	}
	
	/**
	 * A circus is designing a tower routine consisting of people standing 
	 * atop one another’s shoulders. For practical and aesthetic reasons, 
	 * each person must be both shorter and lighter than the person below him or her. 
	 * Given the heights and weights of each person in the circus, 
	 * write a method to compute the largest possible number of people in such a tower.
	 */
	public static int maxNumPeople(List<Person> persons) {
		Collections.sort(persons);
		int start = 0;
		int end = 1;
		int maxNumPeople = 1;
		while(end < persons.size()) {
			if(persons.get(end).height > persons.get(end-1).height &&
			   persons.get(end).weight > persons.get(end-1).weight) {
				maxNumPeople = Math.max(maxNumPeople, end-start+1);
			} else {
				start = end;
			}
			end++;
		}
		return maxNumPeople;
	}
	
	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(65, 100));
		persons.add(new Person(70, 150));
		persons.add(new Person(56, 90));
		persons.add(new Person(75, 190));
		persons.add(new Person(60, 95));
		persons.add(new Person(68, 110));
		
		int maxNumPeople = maxNumPeople(persons);
	}
}
