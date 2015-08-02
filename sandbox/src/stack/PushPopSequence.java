package stack;

import java.util.List;
import java.util.Stack;

public class PushPopSequence {

	/**
	 * Given a push sequence into a stack, determine 
	 * whether a pop sequence is valid
	 * @param <T>
	 */
	<T> boolean isValid(List<T> push, List<T> pop) {
		if(push.size() != pop.size()) {
			return false;
		}
		Stack<T> stack = new Stack<T>();
		int pushPos = 0;
		while(pushPos < push.size()) {
			stack.push(push.get(pushPos++));
			if(stack.peek().equals(pop.get(0))) {
				break;
			}
		}
		int popPos = 0;
		while(popPos < pop.size()) {
			if(stack.peek().equals(pop.get(popPos))) {
				stack.pop();
				popPos++;
			} else {
				if(pushPos == push.size()) {
					return false;
				}
				while(pushPos < push.size()) {
					stack.push(push.get(pushPos++));
					if(stack.peek().equals(pop.get(0))) {
						break;
					}
				}
			}
		}
		return true;
	}
}
