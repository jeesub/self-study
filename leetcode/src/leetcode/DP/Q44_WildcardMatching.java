package leetcode.DP;
/**
 * Q44. Wildcard Matching.
 * [DP]
 *   | 0 a c d c b
 * --+------------
 * 0 | t f f f f f
 * a | f t f f f f
 * * | f t t t t t
 * c | f f t f t f
 * ? | f f f t f t
 * b | f f f f f f
 * Let's say row = i, col = j
 * 1) p[i] == s[j]
 *    get value from dp[i - 1][j - 1]
 * 2) p[i] == ?
 *    get value from dp[i - 1][j - 1]
 * 3) p[i] == *
 *    value can be (dp[i][j - 1] | dp[i - 1][j])
 *    dp[i][j - 1] : * is multiple characters
 *    dp[i - 1][j] : * is empty sequence
 * return dp[last][last]
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q44_WildcardMatching {

    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (p.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                if (p.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] | dp[i][j - 1];
                    continue;
                }
            }
        }

        return dp[p.length()][s.length()];
    }

    public static void main(String[] args) {
        String s = "acdcb";
        String p = "*a*c?b";
        System.out.println(isMatch(s, p));
        // output: false
    }

}
