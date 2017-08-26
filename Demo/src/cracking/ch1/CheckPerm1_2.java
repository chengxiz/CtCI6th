package cracking.ch1;

public class CheckPerm1_2 {
	private static String Sort(String str){
		char[] array = str.toCharArray();
		//System.out.println(array);
		java.util.Arrays.sort(array);
		//System.out.println(new String(array));
		return new String(array);
	}
	/**
	 * By using this method, the big 0 complexity is Nlog(N) in terms of sort
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean CheckPermutation_1(String str1, String str2){
		if (str1.length() != str2.length()) return false;
		return Sort(str1).equals(Sort(str2));				
	}
	
	public static boolean CheckPermutation_2(String str1, String str2){
		int[] letterset = new int[128];
		char[] charset1 = str1.toCharArray();
		char[] charset2 = str2.toCharArray();
		if (charset1.length != charset2.length ) return false;
		for (int i=0; i < charset1.length; i++ ){
			letterset[(int)(charset1[i])]++;
		}
		for (int j=0; j < charset2.length; j++) {
			int element = --letterset[(int)(charset1[j])];
			if (element < 0) return false;
		}
		return true;
	}
	/**
	 * By using this method, the big 0 complexity is N but we need more space
	 * @param str1
	 * @param str2
	 * @return 
	 */
	public static void main(String[] args) {
		String str1 = "Hello Tom";
		String str2_1 = "Tom Hello ";
		String str2_2 = "Tom Hello";	
		
		System.out.println(CheckPermutation_1(str1, str2_1));
		System.out.println(CheckPermutation_1(str1, str2_2));	
		
		System.out.println(CheckPermutation_2(str1, str2_1));
		System.out.println(CheckPermutation_2(str1, str2_2));		
		}
}
