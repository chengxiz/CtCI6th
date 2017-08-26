package cracking.Library;


public class SinglyLinkedList<E> {
	
	// instance variables of the SinglyLinkedList
	public Node<E> head = null;				// head node of the list (or null if empty)
	public Node<E> tail = null;				// tail node of the list (or null if empty)
	private int size = 0;						// number of nodes in the list
	public SinglyLinkedList() {}				// constructs an initially empty list
	// access methods
	public int size() { return size; }
	public boolean isEmpty() { return size==0; }
	public E first() {					// returns (but does not remove) the first element
		if(isEmpty()) return null;
		return head.getElement();
	}
	public E last() {					// returns (but does not remove) the last element
		if(isEmpty()) return null;
		return tail.getElement();
	}
	public void addFirst(E e) {			// adds element e to the front of the list
		head = new Node<E>(e,head);		// create and link a new node
		if (size==0){
			tail = head;				// special case: no elements and new node becomes tail also
		}
		size++;
	}
	public void addLast(E e) {
		Node<E> newest = new Node<E>(e,null);
		if (size==0){
			head = newest;				// special case: previous empty list
		} else{
			tail.setNext(newest);
		}
		tail = newest;
		size++;		
	}
	public E  removeFirst() {
		if (isEmpty()) return null;
		E  answer = head.getElement();
		head = head.getNext();
		size--;
		if (size==0)
			tail = null;
		return answer;
	}
	public void viewList(){
		if (head==null){
			System.out.println("List is empty!");
		} else {
			Node<E> root = head;
			while (root != null) {
				//System.out.print(" --> " + root.element);
				System.out.print(root.getElement());
				root = root.getNext();
			}
		}
		System.out.println("");
		System.out.println("Here is the List");
	}
	
}