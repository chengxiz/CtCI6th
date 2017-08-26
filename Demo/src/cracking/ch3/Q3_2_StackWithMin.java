package cracking.ch3;

import java.util.Stack;

public class Q3_2_StackWithMin {
	public static class StackWithMin extends Stack<NodeWithMin>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public void push(int value){
			int newMin = Math.min(value,min());
			super.push(new NodeWithMin(value, newMin));
		}
		public int min(){
			if (this.isEmpty()){
				return Integer.MAX_VALUE;
			} else {
				return peek().min;
			}		
		}
	}
	public static class StackWithMinB extends Stack<Integer> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public static Stack<Integer> s2;
		public StackWithMinB() {
			s2 = new Stack<Integer>();
		}

		public void push(int value){
			if (value <= min()) {
				s2.push(value);
			}
			super.push(value);
		}
		public Integer pop(){
			int value = super.pop();
			if(value == min()){
				s2.pop();
			}
			return value;
		}
		public int min() {
			if (s2.isEmpty()){
				return Integer.MAX_VALUE;
			} else {
				return s2.peek();
			}
		}
	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		StackWithMin sm = new StackWithMin();

	}
}
