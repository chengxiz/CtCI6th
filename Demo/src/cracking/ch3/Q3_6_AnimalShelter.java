package cracking.ch3;
import cracking.Library.*;
public class Q3_6_AnimalShelter {
	class AnimalQueue {
		SinglyLinkedList<Dog> dogs = new SinglyLinkedList<Dog>();
		SinglyLinkedList<Cat> cats = new SinglyLinkedList<Cat>();
		private int order = 0; // acts as timestamp
		public class Dog extends Animal {
			public Dog(String n) { super(n); }
		}
		public class Cat extends Animal {
			public Cat(String n) { super(n); }
		}
		public Dog dequeueDogs() {
			return dogs.removeFirst();
		}
		public Cat dequeueCats() {
			return cats.removeFirst();
		}
		public void enqueue(Animal a) {
			/* Order is used as a sort of timestamp, so that we can compare the insertion
			 * order of a dog to a cat. */
			a.setOrder(order);
			order++;

			if (a instanceof Dog) dogs.addLast((Dog) a);
			else if ( a instanceof Cat) cats.addLast((Cat) a);
		}

		public Animal dequeueAny() {
			/* Look at tops of dog and cat queues, and pop the queue with the oldest 
			 * value. */
			if (dogs.size() == 0) {
				return dequeueCats();
			} else if (cats.size() == 0) {
				return dequeueDogs();
			}

			Dog dog = dogs.last();
			Cat cat = cats.last();
			if (dog.isOlderThan(cat)) {
				return dequeueDogs();
			} else {
				return dequeueCats();
			}
		}
	}

}
