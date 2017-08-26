package cracking.ch1;


public class OneWay1_5 {
	public static boolean oneWay(String s1, String s2){
		if (s1.length() == s2.length()) {
			return oneReplace(s1 , s2);
		} else if (s1.length() == s2.length() - 1) {
			return oneInsert(s1 , s2);
		} else if (s1.length() == s2.length() + 1) {
			return oneInsert(s2 , s1);
		}
		return false;
	}

	public static boolean oneReplace(String s1, String s2){
		boolean isOneReplace = false;
		for (int i = 0; i < s1.length() ; i++) {
			if (s1.charAt(i) != s2.charAt(i)){
				if (!isOneReplace) {
					isOneReplace = true;
				} else {
					return false;
				}
			}			
		}
		return isOneReplace;
	}
	public static boolean oneInsert(String s1, String s2){
		boolean isOneInsert = false;
		int index1 = 0;
		int index2 = 0;
		while (index1 < s1.length() && index2 < s2.length()){
			if (s1.charAt(index1) != s2.charAt(index2)){
				if (!isOneInsert){
					index1--; 
					isOneInsert = true;
				} else {
					return false;
				}
			}
			index1++;
			index2++;
		}
		return true;
	}
	public static void main(String[] args) {
		String s1 = "pale";
		String s2 = "ple";
		String s3 = "pales";
		String s4 = "bale";
		System.out.println(oneWay(s1,s2));
		System.out.println(oneWay(s2,s1));
		System.out.println(oneWay(s2,s3));
		System.out.println(oneWay(s1,s3));
		System.out.println(oneWay(s1,s4));
		System.out.println(oneWay(s3,s4));
	}
}