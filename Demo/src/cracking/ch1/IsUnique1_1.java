package cracking.ch1;

public class IsUnique1_1 {
	static boolean isUniqueChar(String str) {
		if (str.length() > 128) return false; // the size of ascii table is 128
		boolean[] charSet = new boolean[128]; // default values are false
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);		// transfer from char to int, the index in ascii table
			if (charSet[val]) return false;	// if the element is true, which means it has shown up once
			charSet[val] = true;			// the elements shows up
		}
		return true;						// the loop is over and there is no duplicated elements		
	}
	public static void main(String[] args) {
		String str1 = new String("abcccde");
		String str2 = new String("Aa");
		System.out.println(isUniqueChar(str1));
		System.out.println(isUniqueChar(str2));
	}
	
}
