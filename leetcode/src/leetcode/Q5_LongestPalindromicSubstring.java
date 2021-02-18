package leetcode;
/**
 * Q5. Longest Palindromic Substring.
 * babad
 *   ^
 * from one (or two) char,expand to left and right if they are same (and in a valid range)
 *
 * iterate through the given string
 * see the left and right characters
 * 
 * @author jeesublee
 *
 */
public class Q5_LongestPalindromicSubstring {

	public static String longestPalindrome(String s) {
		String longest = "";
		for (int i = 0; i < s.length(); i++) {
			longest = buildPalindrome(s, longest, i, i);
		}
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				longest = buildPalindrome(s, longest, i, i + 1);
			}
		}
		return longest;
	}

	private static String buildPalindrome(String s, String longest, int start, int end) {
		while (start - 1 >= 0 && end + 1 < s.length() && s.charAt(start - 1) == s.charAt(end + 1)) {
			start--;
			end++;
		}
		if (end - start + 1 > longest.length()) {
			return s.substring(start, end + 1);
		}
		return longest;
	}

	public static void main(String[] args) {
		String s = "babad";
		System.out.println(longestPalindrome(s));
		// output: bab or aba
	}

}
