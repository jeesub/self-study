package leetcode;
/**
 * Q28. Implement strStr().
 * a pointer iterate through the haystack
 * if the char at the pointer is same as needle's start character,
 * investigate
 * if we find the needle, return the pointer
 * 
 * @author jeesublee
 */
public class Q28_ImplementStrStr {

	public static int strStr(String haystack, String needle) {
		if (needle.length() == 0) {
			return 0;
		}
		for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
			if (haystack.charAt(i) != needle.charAt(0)) {
				continue;
			}
			if (haystack.substring(i, i + needle.length()).equals(needle)) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String haystack = "hello";
		String needle = "ll";
		System.out.print(strStr(haystack, needle));
		// output: 2
	}

}
