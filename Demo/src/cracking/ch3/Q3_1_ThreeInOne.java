package cracking.ch3;
import java.util.EmptyStackException;

import cracking.ch3.FullStackException;
public class Q3_1_ThreeInOne {
	public static class FixedMultiStack {
		/* Capacity of each stack */
		private int StackCapacity;
		private int NumStack = 3;
		private int[] values;
		private int[] sizes;

		public FixedMultiStack(int StackSize) {
			StackCapacity = StackSize;
			values = new int[StackSize * NumStack];
			sizes = new int[NumStack];
		}

		/* push an element into a stack */
		public void push(int stackNum, int value) throws FullStackException{
			/* Check whether we have space for the pushed element. */
			if (isFull(stackNum)) throw new FullStackException();

			values[indexOfTop(stackNum)+1] = value;
			sizes[stackNum]++;
		}

		/* pop an element out of a stack */
		public int pop(int stackNum) throws EmptyStackException{
			/* Check whether we have remained element. */
			if (isEmpty(stackNum)) throw new EmptyStackException();
			int pop = values[indexOfTop(stackNum)];
			values[indexOfTop(stackNum)] = 0;
			sizes[stackNum]--;		
			return pop;	
		}

		/* return an element at the top of a stack */
		public int peek(int stackNum) throws FullStackException{
			/* Check whether we have remained element. */
			if (isEmpty(stackNum)) throw new FullStackException();
			return values[indexOfTop(stackNum)];
		}

		/* return whether a stack is empty */
		public boolean isEmpty(int stackNum){
			return sizes[stackNum] == 0;			
		}

		/* return whether a stack is full */
		public boolean isFull(int stackNum){
			return sizes[stackNum] == StackCapacity;
		}

		/* return the index of the top element in a stack */
		public int indexOfTop(int stackNum) {
			int offset = stackNum *StackCapacity;
			return (int)(sizes[stackNum] + offset - 1);
		}
	}
	public static class MultiStack {

		/* StackInfo is a simple class that holds a set of data about each stack. It
		 * does not hold the actual items in the stack. we could have done this with 
		 * just a bunch of individual variables, but that's messy and doesn't gain us 
		 * much. */
		private class StackInfo {
			public int start, size, capacity;
			public StackInfo(int start, int capacity) {
				this.start = start;
				this.capacity = capacity;
			}

			/* Check if an index on the full array is within the stack boundaries. The
			 * stack can wrap around to the start of the array. */
			public boolean isWithinStackCapacity(int index) {
				/* If outside of bounds of array, return false. */
				if (index < 0 || index >= values.length) return false;

				/* If index wraps around, adjust it. */
				int contiguousIndex = index < start ? index + values.length : index;
				int end = start + capacity;
				return start <= contiguousIndex && contiguousIndex < end;
			}

			public int lastCapacityIndex() {
				return adjustIndex(start + capacity - 1);
			}

			public int lastElementIndex() {
				return adjustIndex(start + capacity -1);
			}

			public boolean isFull() { return size == capacity; }
			public boolean isEmpty() { return size == 0; }
		}
		private StackInfo[] info;
		private int[] values;
		public MultiStack(int numberOfStacks, int defaultSize) {
			/* Create metadata for all the stacks */
			info = new StackInfo[numberOfStacks];
			for (int i = 0; i < numberOfStacks; i++ ) {
				info[i] = new StackInfo(defaultSize * i, defaultSize);
			}
			values = new int[numberOfStacks * defaultSize];
		}
		public void push(int stackNum, int value) throws FullStackException {
			if (allStacksAreFull()) throw new FullStackException();

			StackInfo stack = info[stackNum];
			if (stack.isFull()) {
				expand(stackNum);
			}

			stack.size++;
			values[stack.lastElementIndex()] = value;
		}
		public int pop(int stackNum) throws EmptyStackException {
			StackInfo stack = info[stackNum];
			if (stack.isEmpty()) throw new EmptyStackException();

			int value = values[stack.lastElementIndex()];
			values[stack.lastElementIndex()] = 0;
			stack.size--;
			return value;
		}
		public int peek(int stackNum) {
			StackInfo stack = info[stackNum];
			return values[stack.lastElementIndex()];
		}
		private void shift(int stackNum) {
			System.out.println("/// Shifting " + stackNum);
			StackInfo stack = info[stackNum];

			if (stack.size >= stack.capacity) {
				int nextStack = (stackNum + 1)% info.length;
				shift(nextStack);
				stack.capacity++;
			}

			/* Shift all elements in stack over by one */
			int index = stack.lastCapacityIndex();
			while(stack.isWithinStackCapacity(index)) {
				values[index] = values[previousIndex(index)];
				index = previousIndex(index);
			}

			/* Adjust stack data */
			values[stack.start] = 0;
			stack.start = nextIndex(stack.start);
			stack.capacity--;
		}
		private void expand(int stackNum) {
			shift((stackNum + 1) % info.length);
			info[stackNum].capacity++;
		}

		/* Return the number of items actually present in stack */
		public int numberOfElements() {
			int size = 0;
			for(StackInfo sd: info) {
				size += sd.size;
			}
			return size;
		}

		/* Return true if all the stacks are full */
		public boolean allStacksAreFull() {
			return numberOfElements() == values.length;
		}

		/* Adjust index to be within the range of 0 -> length -1 */
		private int adjustIndex(int index) {
			/* Java's mod operator can return neg values. 
			 * For example, (-11 % 5) will 140 * return -1, not 4. 
			 * We actually want the value to be 4 (since we're wrapping
			 * around the index). 
			 */	
			int max = values.length;
			return ((index % max) + max )% max;
		}

		/* Get index after this index, adjusted for wrap around. */
		private int nextIndex(int index) {
			return adjustIndex(index + 1);
		}

		/* Get index before this index, adjusted for wrap around. */
		private int previousIndex(int index) {
			return adjustIndex(index - 1);
		}

	}
	public static void main(String[] args) throws FullStackException {
		FixedMultiStack fms = new FixedMultiStack(10);
		fms.push(0,12);	
		fms.push(0,13);
		fms.push(0,14);
		fms.push(1,18);
		fms.push(1,22);
		fms.push(2,44);	
		System.out.println(fms.peek(2));
		System.out.println(fms.peek(1));
		System.out.println(fms.peek(0));
		System.out.println(fms.pop(1));
		System.out.println(fms.isEmpty(1));
		System.out.println(fms.pop(1));
		System.out.println(fms.isEmpty(1));
		
		MultiStack ms = new MultiStack(4,3);
		ms.push(0, 24);
		ms.push(0, 22);
		ms.push(0, 13);
		ms.push(0, 8);
		ms.push(0, 81);
		System.out.println("hahah");
		ms.push(0, 71);
		ms.push(0, 61);
		System.out.println("hahah");
	}
}
