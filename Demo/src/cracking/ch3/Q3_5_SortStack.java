package cracking.ch3;

import java.util.Comparator;
import java.util.Stack;

public class Q3_5_SortStack {
	public static void sort(Stack<Integer> s){
		Stack<Integer> r = new Stack<Integer>();
		while(!s.isEmpty()){
			int tmp = s.pop();
			/* if the tmp element is smaller than the r.pop(), 
			 * run the loop until all tmp is larger than any other elements,
			 * and move those larger elements back to s, next loop they will be pushed back
			 */
			while(!r.isEmpty()&&r.peek() > tmp) { 
				s.push(r.pop());
			}
			r.push(tmp);
		}

		/* Copy the elements from r back into s. */
		while(!r.isEmpty()) {
			s.push(r.pop());
		}
	}
	public static <K> void merge(Stack<K> Sa, Stack<K> Sb, Stack<K> S, Comparator<K> comp){
		/* reverse Sa and Sb */
		// this is the tricky part. Let's assume we have two sorted stacks:
		// Sa: 3,5
		// Sb: 4,8,9
		// we add small value first into the stack
		// now we want to merge them to get a stack with ordered elements:
		// S: 3,4,5,8,9
		// we need to reverse them to 
		// S1: 5,3
		// S2: 9,8,4
		// to make sure that the small value can be poped out first,
		// then we push them into S by comparison first.
		// if we use the other order , just vice versa
		Stack<K> S1= new Stack<K>();
		while(Sa.size() > 0){
			S1.push(Sa.pop());
		}
		Stack<K> S2= new Stack<K>();
		while(Sb.size() > 0){
			S2.push(Sb.pop());
		}
		
		while(S1.size() + S2.size() > 0){
			if (S2.size()==0 || (S1.size()>0)&&comp.compare(S1.peek(), S2.peek()) < 0){
				S.push(S1.pop());
			} else {
				S.push(S2.pop());				
			} 
		}

	}
	public static <K> void mergeSort(Stack<K> S, Comparator<K> comp){
		int n = S.size();
		if (n < 2) return; 
		int mid = n/2;
		Stack<K> S1 = new Stack<K>();
		Stack<K> S2 = new Stack<K>();
		int num = 0;
		while (S.size() != 0){		
			
			if (num< mid){
				S1.push(S.pop());
			} else {
				S2.push(S.pop());
			}
			num++;
		}
		// conquer with recursion
		mergeSort(S1, comp);
		mergeSort(S2, comp);
		// merge results
		merge(S1,S2,S,comp);
	}
	public static class DefaultComparator<E> implements Comparator<E> {
		@SuppressWarnings("unchecked")
		public int compare(E a, E b) throws ClassCastException {
	    	return ((Comparable<E>) a).compareTo(b);
	    }
	}
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(8);
		s.push(3);
		s.push(6);
		s.push(9);
		s.push(2);
		//sort(s);
		DefaultComparator<Integer> comp = new DefaultComparator<Integer>();	
		mergeSort(s,comp);
		while (s.size()>0){
			System.out.println(s.pop());
		}
		
		
	}
}
