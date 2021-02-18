package leetcode;
/**
 * Q14. Longest Common Prefix.
 * 1. linear search
 * i = 0 ~ the shortest word's length
 * compare every string's character at i
 * if every character is same, append
 * else, break and return
 * 
 * 2. binary search
 * find the shortest word
 * find the longest common prefix's end index using binary search
 * compare subString(left, mid + 1)
 * if valid, we want to expand possible range, left = mid + 1
 * else, we want to reduce possible range, right = mid
 * ["flower", "flow", "flight"]
 * left = 0, right = 4, mid = 2 // flo, fli //not same
 * left = 0, right = 2, mid = 1 // fl, fl // same
 * left = 2, right = 2 // end
 * @author jeesublee
 */
public class Q14_LongestCommonPrefix {

	public static String longestCommonPrefix(String[] strs) {
		if (strs.length < 1) {
			return "";
		}

		int shortestLength = Integer.MAX_VALUE;
		for (String s : strs) {
			shortestLength = Math.min(shortestLength, s.length());
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < shortestLength; i++) {
			for (String s : strs) {
				if (strs[0].charAt(i) != s.charAt(i)) {
					return result.toString();
				}
			}
			result.append(strs[0].charAt(i));
		}
		return result.toString();
	}

	public static String longestCommonPrefix2(String[] strs) {
		if (strs.length < 1) {
			return "";
		}

		String shortest = strs[0];
		for (String s : strs) {
			if (shortest.length() > s.length()) {
				shortest = s;
			}
		}

		int left = 0;
		int right = shortest.length();
		while (left < right) {
			int mid = left + (right - left) / 2;
			boolean isCommon = true;
			for (String s : strs) {
				if (!shortest.substring(left, mid + 1).equals(s.substring(left, mid + 1))) {
					isCommon = false;
					break;
				}
			}
			if (isCommon) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return shortest.substring(0, right);
	}

	public static void main(String[] args) {
		String[] strs = {"flower","flow","flight"};
		System.out.println(longestCommonPrefix(strs));
		System.out.println(longestCommonPrefix2(strs));
		// output: fl
	}

}
