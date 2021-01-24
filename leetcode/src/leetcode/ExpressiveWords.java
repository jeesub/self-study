package leetcode;
/**
 * 
 * four pointers
 * get the group and compare the number
 * group(s) size should be
 *    1. >= 3 and >= group(w) size
 *    or
 *    2. equal to group(word) size
 * 
 * @author jeesublee
 *
 */
public class ExpressiveWords {

	public static int expressiveWords(String S, String[] words) {
		int cnt = 0;
		for (String word : words) {
			cnt += isStretchy(S, word) ? 1 : 0;
		}
		return cnt;
	}

	private static boolean isStretchy(String S, String W) {
		int startS = 0;
		int startW = 0;
		int endS = 0;
		int endW = 0;
		while (endS < S.length() && endW < W.length()) {
			if (S.charAt(startS) != W.charAt(startW)) {
				return false;
			}
			while (endS < S.length() && S.charAt(startS) == S.charAt(endS)) {
				endS++;
			}
			while (endW < W.length() && W.charAt(startW) == W.charAt(endW)) {
				endW++;
			}
			if (endS - startS < Math.max(3, endW - startW) && endS - startS != endW - startW) {
				return false;
			}
			startS = endS;
			startW = endW;
		}
		return endS == S.length() && endW == W.length();
	}

	public static void main(String[] args) {
		String S = "heeellooo";
		String[] words = {"hello", "hi", "helo"};
		System.out.print(expressiveWords(S, words));
		// output: 1
	}

}
