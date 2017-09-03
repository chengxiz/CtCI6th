package Q_6_10_Poison;

import java.util.ArrayList;

public class Question_Naive {
	public static int findPoisonedBottle(ArrayList<Bottle> bottles, ArrayList<TestStrip> strips) {
		int today = 0;
		
		// it is a loop by narrowing down the range of 'bottles' and -- the size of strips( which signs positive should be thrown away)
		while (bottles.size() > 1 && strips.size() > 0) {
			/* Run tests. */
			/* for each strip, for example if the first round on the 7th day doesn't include the poisoned drop, 
			 * then the ArrayList will extend to length of 7, and the 8th element will record the result of second round on the 14th day
			 * and then round by round so on/
			runTestSet(bottles, strips, today);
			
			/* Wait for results. */
			today += TestStrip.DAYS_FOR_RESULT;
			
			/* Check results. */
			for (TestStrip strip : strips) {
				if (strip.isPositiveOnDay(today)) {
					/* return the dropsByDay( for first loop, day should be the 1st day) */
					bottles = strip.getLastWeeksBottles(today);
					strips.remove(strip);// now strips size --;
					break;
				}
			}
		}
	
		if (bottles.size() == 1) {
			System.out.println("Suspected bottle is " + bottles.get(0).getId() + " on day " + today);
			return bottles.get(0).getId();
		}
		return -1;	
	}	
	
	public static void runTestSet(ArrayList<Bottle> bottles, ArrayList<TestStrip> strips, int day) {
		int index = 0;
		for (Bottle bottle : bottles) {
			TestStrip strip = strips.get(index);
			strip.addDropOnDay(day, bottle);
			index = (index + 1) % strips.size();
		}
	}

	
	public static void main(String[] args) {
		int nBottles = 1000;
		int nTestStrips = 10;
		// for (int poisoned = 0; poisoned < nBottles; poisoned++) {
		// 	ArrayList<Bottle> bottles = createBottles(nBottles, poisoned);
		// 	ArrayList<TestStrip> testStrips = createTestStrips(nTestStrips);
		// 	int poisonedId = findPoisonedBottle(bottles, testStrips);
		// 	System.out.println("Suspected Bottle: " + poisonedId);
		// 	if (poisonedId != poisoned) {
		// 		System.out.println("ERROR");
		// 		break;
		// 	}
		// }
		ArrayList<Bottle> bottles = Initialization.createBottles(nBottles, -1);
		ArrayList<TestStrip> testStrips = Initialization.createTestStrips(nTestStrips);
		int poisonedId = findPoisonedBottle(bottles, testStrips);
		System.out.println("Suspected Bottle: " + poisonedId);
	}
}
