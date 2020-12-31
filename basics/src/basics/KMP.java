package basics;

import java.util.Arrays;

/***
 * 
 * Knuth-Morris-Pratt algorithm
 * 
 * Longest Prefix Suffix Array example
 * 	 a b c d a b c u 
 * 	=================
 * 	 0 0 0 0 1 2 3 0 
 * 
 * @author jeesublee
 *
 */
public class KMP {

	public boolean partialMatch(String hay, String needle) {
		int[] lps = getLPS(needle);
		int i = 0;
		int j = 0;

		while (j < hay.length()) {
			if (needle.charAt(i) == hay.charAt(j)) {
				i++;
				j++;
				if (i == needle.length()) {
					break;
				}
			} else if (i > 0) {
				i = lps[i - 1];
			} else {
				j++;
			}
		}

		return i == needle.length();
	}

	private int[] getLPS(String needle) {
		// get Longest Prefix Suffix
		int[] lps = new int[needle.length()];
		int i = 0;
		int j = 1;

		while (j < lps.length) {
			if (needle.charAt(i) == needle.charAt(j)) {
				lps[j++] = ++i;
			} else if (i > 0) {
				i = lps[i - 1];
			} else {
				lps[j++] = 0;
			}
		}

		return lps;
	}

	public static void main(String[] args) {
		KMP solution = new KMP();
		String hay1 = "abcdabcxabcdabcyabcdabcuabcabcdabcx";
		String hay2 = "abcdabcxabcdabcyabcdabcxabcabcdabcx";
		String needle = "abcdabcu";
		System.out.println(solution.partialMatch(hay1, needle));
		// output: true
		System.out.println(solution.partialMatch(hay2, needle));
		// output: false
	}

}
