package cracking.ch2;
import cracking.Library.*;
public class SumList2_5 {
	private static Node<Integer> addLists( Node<Integer> l1, Node<Integer> l2, int carry){
	 	if (l1 == null&&l2==null&&carry == 0) {
	 		return null;
	 	}
	 	Node<Integer> result = new Node<Integer>();
	 	int value = carry;
	 	if (l1 != null) value += l1.getElement();
	 	if (l2 != null) value += l2.getElement();
	 	/**
	 	 *Set the value of second digit of number. 
	 	 */
	 	result.setElement( value%10 );

	 	/** 
	 	 *Recurse
	 	 */
	 	if (l1 != null || l2 != null) {
	 		Node<Integer> more = addLists(
	 			l1==null?null:l1.getNext(),
				l2==null?null:l2.getNext(),
				value >= 10? 1: 0);
	 		result.setNext(more);
	 	}
	 	return result;
	}
	public static class PartialSum {
		public Node<Integer> sum = null;
		public int carry = 0;
	}
	static Node<Integer> addListsWrapper(SinglyLinkedList<Integer> ls1, SinglyLinkedList<Integer> ls2) {
		Node<Integer> l1 = ls1.head;
		Node<Integer> l2 = ls2.head;
		int len1 = ls1.size();
		int len2 = ls2.size();
		/* Pad the shorter list with zeros */
		if (len1 < len2) {
			l1 = padList(l1, len2 - len1);
		} else {
			l2 = padList(l2, len1 - len2);
		}
		/* Add lists */
		PartialSum sum = addListsHelper(l1,l2);
		/* if there was a carry value left over, insert this at the front of the list.
		 * otherwise, just return the linked list. */
		if (sum.carry == 0) {
			return sum.sum;
		} else {
			Node<Integer> result = insertBefore(sum.sum, sum.carry);
			return result;
		}
	}
	static PartialSum addListsHelper(Node<Integer> l1, Node<Integer> l2) {
		if (l1==null&&l2==null) {
			PartialSum sum = new PartialSum();
			return sum;
		}
		/* Add smaller digits recursively */
		PartialSum sum = addListsHelper(l1.getNext(), l2.getNext());

		/* Add carry to current data */
		int val = sum.carry + l1.getElement() +l2.getElement();

		/* Insert sum of current digits */
		Node<Integer> full_result = insertBefore(sum.sum, val % 10);

		/* Return sum so far, and the carry value */
		sum.sum = full_result;
		sum.carry = val / 10;
		return sum;
	}

	/* Pad the list with zeros */
	static Node<Integer> padList(Node<Integer> l, int padding) {
		Node<Integer> head = l;
		for (int i=0;i < padding; i++){
			head = insertBefore(head,0);
		}
		return head;
	}

	/* Helper function to insert node in the front of a linked list. */
	static Node<Integer> insertBefore(Node<Integer> list, int data) {
		Node<Integer> node = new Node<Integer>(data);
		if (list != null) {
			node.setNext(list);
		}
		return node;
	}
	public static void main(String[] args) {
		SinglyLinkedList<Integer> l1 = new SinglyLinkedList<Integer>();
		SinglyLinkedList<Integer> l2 = new SinglyLinkedList<Integer>();
		l1.addLast(7);
		l1.addLast(1);
		l1.addLast(6);
		l1.viewList();
		l2.addLast(5);
		l2.addLast(9);
		l2.addLast(2);
		l2.viewList();
		Node<Integer> n1 = addLists(l1.head,l2.head,0);
		System.out.println(n1.getElement());
		l2.addLast(7);
		l2.viewList();
		Node<Integer> n2 = addListsWrapper(l1,l2);
		System.out.println(n2.getElement());
	}
}
