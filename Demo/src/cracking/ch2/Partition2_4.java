package cracking.ch2;
import cracking.Library.*;
public class Partition2_4 {
	@SuppressWarnings("unused")
	private static Node<Integer> Partition(Node<Integer> node, int x){
		Node<Integer> beforeStart = null;
		Node<Integer> beforeEnd = null;
		Node<Integer> afterStart = null;
		Node<Integer> afterEnd = null;

		/* Partition list */
		while (node != null) {
			Node<Integer> next = node.getNext();
			node.setNext(null);
			if (node.getElement() < x) {
				/* insert node into end of before list */
				if (beforeStart==null){
					beforeStart = node;
					beforeEnd = beforeStart;
				} else {
					beforeEnd.setNext(node);
					beforeEnd = node;
				}
			} else {
				/* Insert node into end of after list */
				if (afterStart == null) {
					afterStart = node;
					afterEnd = afterStart;
				} else {
					afterEnd.setNext(node);
					afterEnd = node;
				}
			}
			node = next;
		}

		if (beforeStart == null) return afterStart;

		/* Merge before list and after list */
		beforeEnd.setNext(afterStart);
		return beforeStart;
	}
	private static Node<Integer> PartitionNew(Node<Integer> node, int x){
		Node<Integer> head = node;
		Node<Integer> tail = node;

		while (node != null) {
			Node<Integer> next = node.getNext();
			if (node.getElement() < x){
				/* Insert node at head. */
				node.setNext(head);
				head = node;
			} else {
				/* Insert node at tail. */
				tail.setNext(node);
				tail = node;
			}
			node = next;
		}
		tail.setNext(null);
		return head;
	}
	public static void main(String[] args) {
		SinglyLinkedList<Integer> l = new SinglyLinkedList<Integer>();
		l.addLast(7);
		l.addLast(1);
		l.addLast(6);
		l.addLast(5);
		l.addLast(9);
		l.addLast(5);		
		l.addLast(2);
		l.viewList();
		Node<Integer> s = PartitionNew(l.head,5);
		while (s!=null) {
		 	System.out.println(s.getElement());
		 	s = s.getNext();
		}
	}
}
