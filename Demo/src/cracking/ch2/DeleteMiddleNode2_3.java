package cracking.ch2;
import cracking.Library.*;
public class DeleteMiddleNode2_3 {
	private static boolean DeleteNode(Node<Character> n){
		if (n==null || n.getNext()==null) return false;
		n.setElement(n.getNext().getElement());
		n.setNext(n.getNext().getNext());
		return true;		
	}
	public static void main(String[] args) {
		SinglyLinkedList<Character> linkedList = new SinglyLinkedList<Character>();
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
		System.out.println(DeleteNode(linkedList.head.getNext().getNext().getNext()));	
		linkedList.viewList();
	}
}
