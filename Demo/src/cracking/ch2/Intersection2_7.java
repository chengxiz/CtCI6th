package cracking.ch2;


import cracking.Library.*;

public class Intersection2_7 {
	public static class Result {
		public Node<Integer> tail;
		public int size;
		Result(Node<Integer> tail, int size) {
			this.tail = tail;
			this.size = size;
		}
	}
	private static Result getTailandResult(Node<Integer> head){
		if (head==null) return null;

		Node<Integer> current = head;
		int size = 0;
		while (current.getNext()!=null){
			size++;
			current = current.getNext();
		}
		return new Result(current, size);
	}
	private static Node<?> getKthNode(Node<?> head, int k){
		Node<?> current = head;
		for (int i=0;i<k;i++) current = current.getNext();
			return current;
	}
	private static Node<?> findIntersection(Node<Integer> n1, Node<Integer> n2) {

		if ( n1 == null || n2 == null) return null;

		/* Get tail and sizes. */
		Result r1 = getTailandResult(n1);
		Result r2 = getTailandResult(n2);

		/* If different tail nodes, then there's no intersection. */
		if (r1.tail != r2.tail) return null;

		/* Set pointers to the start of each linked list. */
		Node<?> longer = r1.size > r2.size? n1 : n2;
		Node<?> shorter = r2.size < r1.size? n2 : n1;

		longer = getKthNode(longer, Math.abs(r1.size - r2.size));
		while (longer!=shorter) {
			longer = longer.getNext();
			shorter = shorter.getNext();
		}
		/* return either one. */
		return longer;
	} 
	public static void main(String[] args){
		SinglyLinkedList<Integer> l1 = new SinglyLinkedList<Integer>();
		l1.addLast(7);
		l1.addLast(1);
		l1.addLast(6);
		l1.addLast(5);
		l1.addLast(6);
		l1.addLast(1);		
		l1.addLast(7);
		l1.viewList();
		SinglyLinkedList<Integer> l2 = new SinglyLinkedList<Integer>();		
		l2.addLast(9);
		l2.addLast(9);
		l2.addLast(9);
		l2.head.getNext().getNext().setNext((l1.head.getNext()));

		System.out.println(findIntersection(l1.head,l2.head).getElement());
	}
}
