package DP;

/**
 * Basic Regex Parser.
 * [Dynamic Programming]
 *
 *    |   a . * a *
 * ---+-------------
 *    | t f f f f f
 *  a | f t f t f t
 *  b | f f t t f t
 *  b | f f f t
 *  a | f
 *  a | f
 *
 * if pattern[j] == text[i] || pattern[j] == '.', dp[i][j] = dp[i - 1][j - 1]
 * if pattern[j] == '*', (pattern[j - 1] + *) can be zero character or repeated characters
 *   for zero character case,
 *     check dp[i][j - 2]
 *   for repeated characters case,
 *     chech (pattern[j - 1] == text[i] || pattern[j - 1] == '.') && dp[i - 1][j]
 *     // length 1 case: we should look dp[i - 2][j - 1]
 *     //                and dp[i - 2][j - 1] == dp[i][j - 1]
 *     //                because we updated dp[i][j - 1] before
 *     // length 2+ case: we should look dp[i][j - 1]
 *   if one of the cases is true, we can say it's true
 * else, dp[i][j] = false
 *
 * TC: O(m * n)
 * SC: O(m * n) & can improve
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class BasicRegexParser {
    private static final char DOT = '.';
    private static final char STAR = '*';

    static boolean isMatch(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[0][0] = true;

        for (int j = 2; j < dp[0].length; j++) {
            if (pattern.charAt(j - 1) == STAR) {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                char t = text.charAt(i - 1);
                char p = pattern.charAt(j - 1);
                if (t == p || p == DOT) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p == STAR) {
                    boolean zeroRepeat = dp[i][j - 2];
                    boolean multiRepeat =
                            (pattern.charAt(j - 2) == t || pattern.charAt(j - 2) == DOT) && dp[i - 1][j];
                    dp[i][j] = zeroRepeat || multiRepeat;
                }
            }
        }
        return dp[text.length()][pattern.length()];
    }

    public static void main(String[] args) {
        String text = "abbaa";
        String pattern = "a.*a*";
        System.out.println(isMatch(text, pattern));
        // output: true
    }
}
