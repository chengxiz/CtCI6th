package cracking.ch2;

import cracking.Library.*;

public class ReturnKtoLast2_2 {
	private static SinglyLinkedList<Character> linkedList;
	private static int ReturnKLast_Rec(Node<Character> node, Integer k){
		if (node==null) return 0;
		int index = ReturnKLast_Rec(node.getNext(), k) + 1;
		if (index ==k) {
			System.out.println(node.getElement());
		}
		return index;
	}

	private static class Index {
		public int value = 0;
	}
	private static Node<Character> ReturnKLast_WrapperRec(Node<Character> node, Integer k){
		Index idx = new Index();
		return ReturnKLast_WrapperRec(node, k, idx);
	}
	private static Node<Character> ReturnKLast_WrapperRec(Node<Character> node, Integer k, Index idx){
		if (node==null) return null;
		Node<Character> n = ReturnKLast_WrapperRec(node.getNext(), k, idx);
		idx.value++;
		if (idx.value == k){
			return node;
		}
		return n;
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
		int answer_Rec = ReturnKLast_Rec(linkedList.head,4);	
		System.out.println(answer_Rec);

		Node<Character> answer_WrapperRec = ReturnKLast_WrapperRec(linkedList.head,6);
		System.out.println(answer_WrapperRec.getElement());

	}
}
