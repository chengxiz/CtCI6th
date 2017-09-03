package Q_6_10_Poison;

import java.util.ArrayList;
import java.util.Random;

public class Initialization {
	public static ArrayList<Bottle> createBottles(int nBottles, int poisoned) {
		ArrayList<Bottle> bottles = new ArrayList<Bottle>();
		for (int i = 0; i < nBottles; i++) {
			bottles.add(new Bottle(i));
		}
		
		if (poisoned == -1) {
			Random random = new Random();
			poisoned = random.nextInt(nBottles);
		}
		bottles.get(poisoned).setAsPoisoned();
		
		System.out.println("Added poison to bottle " + poisoned);
		
		return bottles;
	}
	
	public static ArrayList<TestStrip> createTestStrips(int nTestStrips) {
		ArrayList<TestStrip> testStrips = new ArrayList<TestStrip>();
		for (int i = 0; i < nTestStrips; i++) {
			testStrips.add(new TestStrip(i));
		}
		return testStrips;
	}
}
