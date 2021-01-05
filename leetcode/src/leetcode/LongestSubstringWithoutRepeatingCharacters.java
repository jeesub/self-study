package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * HashMap (Character, Index)
 * Iterate using two pointers (i, j)
 * if new character is in the Map, update a position of i
 * In this case, we need to use max(i, new candidate of i)
 * always update max length
 * @author jeesublee
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

	public static int lengthOfLongestSubstring(String s) {
		Map<Character, Integer> hm = new HashMap<>();
		int i = 0;
		int max = 0;
		for (int j = 0; j < s.length(); j++) {
			char c = s.charAt(j);
			if (hm.containsKey(c)) {
				i = Math.max(i, hm.get(c) + 1);
			}
			hm.put(c, j);
			max = Math.max(max, j - i + 1);
		}
		return max;
	}
	public static void main(String[] args) {
		String s = "abcabdeac";
		System.out.print(lengthOfLongestSubstring(s));
		// output: 5
	}

}
