package cracking.ch2;
import cracking.Library.*;

public class FindBeginning2_8 {
	@SuppressWarnings("unused")
	private static Node<?> FindBeginning(Node<?> head){
		Node<?> slow = head;
		Node<?> fast = head;

		/* Find meeting point. This will be LOOP_SIZE - k steps into the linked list. */
		while (fast != null && fast.getNext() != null) {
			slow = slow.getNext();
			fast = fast.getNext().getNext();
			if (slow == fast) {	// Collision
				break;
			}
		}

		/* In case there is no loop */
		if (fast == null || fast.getNext() == null) {
			return null;
		}

		slow = head;
		while (slow != fast) {
			slow = slow.getNext();
			fast = fast.getNext();
		}

		return fast;
	}
}
