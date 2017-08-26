package cracking.ch2;
import java.util.Stack;

import cracking.Library.*;
public class Palindrome2_6 {
	private static boolean isPalindrome_Rev(Node<Integer> head){
		Node<Integer> head_rev = Reverse(head);
		return isEqual(head, head_rev);
	}
	private static Node<Integer> Reverse(Node<Integer> head) {
		Node<Integer> head_rev = null;
		while (head != null){
			Node<Integer> temp = new Node<Integer>(head.getElement(), head_rev);
			head_rev = temp;
			head = head.getNext();
		}
		return head_rev;
	}
	private static boolean isEqual(Node<Integer> n1, Node<Integer> n2){
		while (n1 != null && n2 != null) {
			if (n1.getElement() != n2.getElement()) return false;
			n1 = n1.getNext();
			n2 = n2.getNext();
		}
		return true;
	}
	private static boolean isPalindrome_Iter(Node<Integer> head){
		Node<Integer> fast = head;
		Node<Integer> slow= head;
		Stack<Integer> stack = new Stack<Integer>();
		while (fast!= null && fast.getNext()!= null){
			stack.push(slow.getElement());
			fast = fast.getNext().getNext();
			slow = slow.getNext();
		}

		if (fast!=null) slow = slow.getNext();

		while (slow!= null) {
			if (slow.getElement() != stack.pop()) return false;
			slow = slow.getNext();
		}
		return true;
	}
	public static void main(String[] args) {
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
		l2.addLast(7);
		l2.addLast(1);
		l2.addLast(6);
		l2.addLast(6);
		l2.addLast(1);		
		l2.addLast(7);
		l2.viewList();
		System.out.print(isPalindrome_Iter(l1.head));
		System.out.print(isPalindrome_Iter(l2.head));
		System.out.print(isPalindrome_Rev(l1.head));
		System.out.print(isPalindrome_Rev(l2.head));
		l2.addLast(0);
		l1.addLast(3);
		System.out.print(isPalindrome_Iter(l1.head));
		System.out.print(isPalindrome_Iter(l2.head));
		System.out.print(isPalindrome_Rev(l1.head));
		System.out.print(isPalindrome_Rev(l2.head));
	}
}