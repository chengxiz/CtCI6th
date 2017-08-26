package cracking.ch3;

import java.util.Stack;

public class Q3_4_MyQueue {
	public class MyQueue<T> {
		Stack<T> stackNewest, stackOldest;

		public MyQueue() {
			stackNewest = new Stack<T>();
			stackOldest = new Stack<T>();
		}	

		public int size() {
			return stackNewest.size() + stackOldest.size();
		}

		public void add(T value) {
			/* Push onto stackNewest, which always has the newest elements on top */
			stackNewest.push(value);
		}

		/* Move elements from stackNewest into stackOldest. This is usually done so that
		 * we can do operations on stackOldest. */
		private void shiftStacks() {
			if (stackOldest.isEmpty()) {
				while (!stackNewest.isEmpty()) {
					stackOldest.push(stackNewest.pop());
				}
			}
		}

		public T peek() {
			shiftStacks();	// Ensure stackOldest has the current elements
			return stackOldest.peek(); 	// retrieve te oldest item
		}

		public T remove() {
			shiftStacks();	// Ensure stackOldest has the current elements
			return stackOldest.pop();	// pop the oldest item.
		}
	}
}
