package cracking.ch1;

public class StrRotat1_9 {
	protected static boolean isSubstring(String strSub, String strAll){
		if(strAll.indexOf(strSub)<0) return false;
		else return true;
	}
	private static boolean isRotation(String str1, String str2){
		if (str1.length()!=str2.length()) return false;
		if (str1.length() > 0) {
			String str1str1 = str1 +str1;
			return isSubstring(str2, str1str1);
		}
		return false;
	}
	public static void main(String[] args) {
		String str1 = "Ali";
		String str2 = "Ail";
		String str3 = "ilA";		
		System.out.println(isRotation(str1,str2));
		System.out.println(isRotation(str3,str2));
	}
}
