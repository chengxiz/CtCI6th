package cracking.bigO;
public class GenerateAndSort {
    int numChars = 26;
	void printSortedStrings(int remaining) {
		printSortedStrings(remaining, "");
		}
	void printSortedStrings(int remaining, String prefix) {
		if (remaining == 1) {
			if (islnOrder(prefix)) {
				System.out.println(prefix);
			}
		} else {
			for (int i = 1; i < numChars ; i++){	
				char c = ithLetter(i);
				printSortedStrings(remaining - 1, prefix + c);
			}
		}
	}
	boolean islnOrder(String s){
		for (int i = 1; i < s.length(); i++) {
			int prev = ithLetter(s.charAt(i - 1));
			int curr = ithLetter(s.charAt(i));
			if (prev > curr) {
				return false;
			}
		}
		return true;	
	}
	char ithLetter(int i) {
		return (char) (((int)'a') + i);
	}
	public static void main(String[] args) {
		int a =2;
		GenerateAndSort g = new GenerateAndSort(); 		
		g.printSortedStrings(a);
	}
}

