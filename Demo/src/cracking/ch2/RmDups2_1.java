package cracking.ch2;

import java.util.HashSet;

import cracking.Library.*;

public class RmDups2_1 {
	private static SinglyLinkedList<Character> linkedList;
	private static void removeDuplicates(Node<Character> n){
		HashSet<Character> set = new HashSet<Character>();
		Node<Character> prev = null;
		while (n!=null){
			if (set.contains(n.getElement())) {
				prev.setNext(n.getNext());
			}else {
				set.add((Character)n.getElement());
				prev = n;				
			}
			n = n.getNext();
		}
		linkedList.viewList();
	}
	private static void removeDuplicatesWithoutBuffer(Node<Character> head) {
		Node<Character> current = head;
		while (current!=null){
			Node<Character> runner = current;
			while (runner.getNext()!= null){
				if (runner.getNext().getElement()==current.getElement()){
					runner.setNext(runner.getNext().getNext());
					runner = runner.getNext();
				} else {
					runner = runner.getNext();
				}							
			}
			current = current.getNext();
		}
		linkedList.viewList();
	}
	public static void main(String[] args) {
		linkedList = new SinglyLinkedList<Character>();
		linkedList.addLast('F');
		linkedList.addLast('O');
		linkedList.addLast('L');
		linkedList.addLast('L');
		linkedList.addLast('O');
		linkedList.addLast('W');
		linkedList.addLast(' ');
		linkedList.addLast('U');
		linkedList.addLast('P');
		linkedList.viewList();
		removeDuplicates(linkedList.head);
		removeDuplicatesWithoutBuffer(linkedList.head);

	}
}