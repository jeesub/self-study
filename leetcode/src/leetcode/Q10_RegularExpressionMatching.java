package leetcode;
/**
 * Q10. Regular Expression Matching.
 * DP
 * row: string & col: pattern
 * 
 * 1. s(i) == p(j) || p(j) == '.' ?
 * want to look at the previous one
 * -> dp[i][j] = dp[i - 1][j - 1]
 * 
 * 2. p(j) == '*' ?
 * 2-1. * is zero character
 * want to look at the case where p(j-1) doesn't exist
 * -> dp[i][j] = dp[i][j - 2]
 * 2-2. * is non-zero
 * need to look at the previous characters, s(i) and p(j-1)
 * s(i) == p(j-1) || p(j-1) == '.' ?
 * then, want to look at the previous case where we don't have s(i)
 * -> dp[i][j] = dp[i - 1][j]
 * 
 *   | 0 c * a * b
 * --+------------
 * 0 | t f t f t f
 * a | f f f t t f
 * a | f f f f t f
 * b | f f f f f t
 * 
 * @author jeesublee
 */
public class Q10_RegularExpressionMatching {

	public static boolean isMatch(String s, String p) {
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		for (int j = 1; j < p.length() + 1; j++) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j - 2];
			}
		}
		for (int i = 1; i < s.length() + 1; i++) {
			for (int j = 1; j < p.length() + 1; j++) {
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
					dp[i][j] = dp[i - 1][j - 1];
				}
				if (p.charAt(j - 1) == '*') {
					if (dp[i][j - 2]) {
						dp[i][j] = dp[i][j - 2];
						continue;
					}
					if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
		}
		return dp[s.length()][p.length()];
	}

	public static void main(String[] args) {
		String s = "aab";
		String p = "c*a*b";
		System.out.print(isMatch(s, p));
		// output: true
	}

}
