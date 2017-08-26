package cracking.ch1;

public class Zipper1_6 {
	public static String zipString(String str){
		StringBuilder compressed =new StringBuilder();
		int countConsecutive = 0;
		for (int i = 0; i < str.length(); i++) {
			countConsecutive++;
			if (i+1 >= str.length()|| str.charAt(i)!= str.charAt(i+1)) {
				compressed.append(str.charAt(i));
				compressed.append(countConsecutive);
				countConsecutive++;
			}
		}
		return compressed.length() < str.length() ? compressed.toString() : str;
	}
}
