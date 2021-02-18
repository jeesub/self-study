package leetcode;
/**
 * Q727. Minimum Window Subsequence.
 * [DP]
 * keep track of making subsequence chain
 * if T.charAt(i) == S.charAt(j),
 * we need to know about the previous one
 * we can get the information about the previous one from dp[i - 1][j - 1]
 * (we will keep the best and valid start index)
 * if we have found the previous one, the information is on dp[i - 1][j - 1]
 * else, dp[i - 1][j - 1] should be the initial value, -1
 * after making dp table, we can get the length from the last line
 * iterate through the array, dp[the last line], and keep the best pair
 *
 *   | 0 a d b c d e b d d e 
 * --+-----------------------
 * 0 | 0 1 2 3 4 5 6 7 8 9 10
 * b | x x x 2 2 2 2 6 6 6 6 
 * d | x x x x x 2 2 2 6 6 6 (dp[2][2] is x because dp[1][1] is x)
 * e | x x x x x x 2 2 2 2 6 
 *                 ^ (from dp[i-1][j-1]) -> the best pair so far is (2, 5)
 *                   It means the best answer so far is bcde
 * 
 * @author jeesublee
 *
 */
public class Q727_MinimumWindowSubsequence {

	public static String minWindow(String S, String T) {
		int[][] dp = init(S, T);
		for (int i = 1; i <= T.length(); i++) {
			for (int j = 1; j <= S.length(); j++) {
				if (T.charAt(i - 1) == S.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = dp[i][j - 1];
				}
			}
		}

		int start = 0;
		int end = Integer.MAX_VALUE;
		for (int j = 1; j <= S.length(); j++) {
			int newStart = dp[T.length()][j];
			int newEnd = j - 1;
			if (newStart != -1 && newEnd - newStart < end - start) {
				start = newStart;
				end = newEnd;
			}
		}
		return end == Integer.MAX_VALUE ? "" : S.substring(start, end + 1);
	}

	private static int[][] init(String S, String T) {
		int[][] dp = new int[T.length() + 1][S.length() + 1];
		for (int j = 0; j <= S.length(); j++) {
			dp[0][j] = j;
		}
		for (int i = 1; i <= T.length(); i++) {
			dp[i][0] = -1;
		}
		return dp;
	}

	public static void main(String[] args) {
		String S = "adbcdebdde";
		String T = "bde";
		System.out.println(minWindow(S, T));
		// output: bcde
	}

}
