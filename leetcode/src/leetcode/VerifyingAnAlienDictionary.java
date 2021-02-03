package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * VerifyingAnAlienDictionary.
 * 
 * [overall]
 * make a map of order, ex) a: 0, b: 1, c: 2 ...
 * compare every words[i] and words[i + 1]
 * 
 * [compare logic]
 * compare every character
 * if the second word is shorter, return false
 * if the order is reversed, return false
 * else, return true
 * 
 * @author jeesublee
 *
 */
public class VerifyingAnAlienDictionary {

	public static boolean isAlienSorted(String[] words, String order) {
		Map<Character, Integer> orderMap = initOrder(order);
		for (int i = 0; i < words.length - 1; i++) {
			if (!isValid(words[i], words[i + 1], orderMap)) {
				return false;
			}
		}
		return true;
	}

	private static Map<Character, Integer> initOrder(String order) {
		Map<Character, Integer> orderMap = new HashMap<>();
		for (int i = 0; i < order.length(); i++) {
			orderMap.put(order.charAt(i), i);
		}
		return orderMap;
	}

	private static boolean isValid(String s1, String s2, Map<Character, Integer> orderMap) {
		int ptr = 0;
		while (ptr < s1.length() && ptr < s2.length()) {
			if (orderMap.get(s1.charAt(ptr)) < orderMap.get(s2.charAt(ptr))) {
				return true;
			}
			if (orderMap.get(s1.charAt(ptr)) > orderMap.get(s2.charAt(ptr))) {
				return false;
			}
			ptr++;
		}
		return ptr == s1.length();
	}

	public static void main(String[] args) {
		String[] words = {"hello","leetcode"};
		String order = "hlabcdefgijkmnopqrstuvwxyz";
		System.out.print(isAlienSorted(words, order));
		// output: true
	}

}
