package stack;

import java.util.Stack;

public class QueueUsingStacks<T> {

	Stack<T> main;
	Stack<T> aux;
	
	void add(T val) {
		while(!main.isEmpty()) {
			aux.push(main.pop());
		}
		aux.push(val);
	}
	
	T remove() {
		while(!aux.isEmpty()) {
			main.push(aux.pop());
		}
		return main.pop();
	}
	
	T peek() {
		while(!aux.isEmpty()) {
			main.push(aux.pop());
		}
		return main.peek();
	}
}
