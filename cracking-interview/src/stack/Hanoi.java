package stack;

import java.util.Stack;

public class Hanoi<T> {
	void move(Stack<T> from, Stack<T> to, Stack<T> using, int numPlates) {
		if (numPlates <= 0) {
			return;
		}
		move(from, using, to, numPlates - 1);
		to.push(from.pop());
		move(using, to, from, numPlates - 1);
	}
}
