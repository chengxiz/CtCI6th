package cracking.ch1;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class Palin_Perm1_4 {

	private static boolean Palindrome(String s){
		char[] charset = s.toCharArray();
		Hashtable<Character,Integer> h = new Hashtable<Character,Integer>();
		for (int i = 0; i < charset.length ; i++){
			Character ch = Character.toLowerCase(charset[i]);
			if(ch != ' '){
				if (!h.containsKey(ch)){
					h.put(ch, 1);		// the key has not been put into hashtable
				} else{
					int num =  h.get(ch);
					h.remove(ch);
					h.put(ch, num +1 );	// add the value by one
				}
			}
		}
		Iterator<Map.Entry<Character,Integer>> it = h.entrySet().iterator();
		int num = 0;
		while (it.hasNext()){
			Map.Entry<Character,Integer> entry = it.next();
			System.out.println(entry.getValue());
			System.out.println(entry.getKey());
			
			if (entry.getValue()%2==1) num++;
		}
		if (num == 0 || num == 1) return true;
		return false;
 	}
 	public static void main(String[] args) {
 		String str1 = new String("Tact Coa");
 		System.out.println(Palindrome(str1));

 	}
}