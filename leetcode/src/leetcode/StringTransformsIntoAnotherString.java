package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 
 * 1. make a hashmap. str1 is a key and str2 is a value
 *    if one key has two different values, return false
 * 2. if every key has an unique value,
 *    we need to see if we have an enough space to convert
 *    abc...xyz -> bcd... yza cannot be done because it doesn't have any space for a tmp
 *    if the (hashset of values).size < 26, we have an extra space
 * 
 * @author jeesublee
 *
 */
public class StringTransformsIntoAnotherString {

	public static boolean canConvert(String str1, String str2) {
		if (str1.equals(str2)) {
			return true;
		}
		Map<Character, Character> map = new HashMap<>();
		for (int i = 0; i < str1.length(); i++) {
			char c1 = str1.charAt(i);
			char c2 = str2.charAt(i);
			if (!map.containsKey(c1)) {
				map.put(c1, c2);
			} else if (!map.get(c1).equals(c2)) {
				return false;
			}
		}
		return new HashSet<Character>(map.values()).size() < 26;
	}

	public static void main(String[] args) {
		String str1 = "aabcc";
		String str2 = "ccdee";
		System.out.print(canConvert(str1, str2));
		// output: true
	}

}
