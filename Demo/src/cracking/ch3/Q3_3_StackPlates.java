package cracking.ch3;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Q3_3_StackPlates {
	public static class SetOfStacks{
		public ArrayList<Stack> stacks = new ArrayList<Stack>();
		public int capacity;
		public SetOfStacks(int capacity){
			this.capacity = capacity;
		}
		public Stack getLastStack(){
			if (stacks.size() == 0) {
				return null;
			}
			return stacks.get(stacks.size() - 1);
		}
		public void push(int v) {
			Stack last = getLastStack();
			if (last!=null && !last.isFull()){
				last.push(v);
			} else {
				Stack newone = new Stack(capacity);
				newone.push(v);
				stacks.add(newone);
			}
		}
		public int pop() throws EmptyStackException{
			Stack last = getLastStack();
			if( last != null ){
				int v = last.pop();
				if (last.size()==0){
					stacks.remove(stacks.size()-1);
				}
				return v;
			} else {
				throw new EmptyStackException();
			}
		}
	}
	public static class SetOfStacksRollOver {
		ArrayList<Stack> stacks = new ArrayList<Stack>();
		public int capacity;
		public SetOfStacksRollOver(int capacity) {
			this.capacity = capacity;
		}

		public Stack getLastStack() {
			if (stacks.size() == 0) return null;
			return stacks.get(stacks.size() - 1);
		}
		public void push(int v){
			Stack last = getLastStack();
			if (last!=null && !last.isFull()){
				last.push(v);
			} else {
				Stack newone = new Stack(capacity);
				newone.push(v);
				stacks.add(newone);
			}
		}
		public int pop() throws EmptyStackException{
			Stack last = getLastStack();
			if( last != null ){
				int v = last.pop();
				if (last.size==0){
					stacks.remove(stacks.size()-1);
				}
				return v;
			} else {
				throw new EmptyStackException();
			}
		}
		public int popAt(int index) {
			return leftShift(index, true);
		}

		public int leftShift(int index, boolean removeTop) {
			Stack stack = stacks.get(index);
			int removed_item;
			if (removeTop) {
				removed_item = stack.pop();
			} else {
				removed_item = stack.removeBottom();
			}
			if(stack.isEmpty()) {
				stacks.remove(index);					// if only happens when the stack is the top stack, otherwise during the last leftshift the top stack has push the element(line90) to the below one
			} else if (stacks.size() > index + 1) {		// if the variable stack is not the top stack among the variable stacks
				int v = leftShift(index + 1, false);	// remove bottom from the above stack and then push it to the below stack
				stack.push(v);							// for example, the StackSet has two stacks whose capacities are 3, [{1,2,3},{4,5,6}]. when we implement popAt(0), 
			}											// it implements leftShift(0, true) first, then it becomes[{1,2},{4,5,6}], then it realizes that element at index 0 is not the top one, 
														// then it implements (1, false), it makes the array becomes [{1,2},{5,6}], the it returns 4 and then push it back , as [{1,2,4},{5,6}]			
			return removed_item;						// in the recursion, this is the value return and push to the below stack
		}
	}
	public static class Stack {
		private int capacity;
		public Node top, bottom;
		private int size = 0;

		public Stack(int capacity) { this.capacity = capacity; }
		public boolean isFull() { return capacity == size; }
		public int size() { return size;}
		public void join (Node above, Node below) {
			if (below != null) below.above = above;
			if (above != null) above.below = below;
		}

		public boolean push(int v) {
			if (size >= capacity) return false;
			size++;
			Node n = new Node(v);
			if (size == 1) bottom = n;
			join(n, top);
			top = n;
			return true;
		}
		public int pop() {
			Node t = top;
			top = top.below;
			size--;
			return t.value;
		}
		public boolean isEmpty() {
			return size == 0;
		}
		public int removeBottom() {
			Node b = bottom;
			bottom = bottom.above;
			if (bottom != null) bottom.below = null;
			size--;
			return b.value;
		}
	}
	public static class Node {
		public Node above;
		public Node below;
		public int value;
		public Node(int value) {
			this.value = value;
		}
	}
	public static void main(String[] args) {
		SetOfStacksRollOver ssr = new SetOfStacksRollOver(3);
		ssr.push(1);
		ssr.push(2);
		ssr.push(3);
		ssr.push(4);
		ssr.push(5);
		ssr.popAt(0);
	}
}
