package string;
/**
 * Q11. String Conversion Counter.
 * Memoization.
 * Input: Original String, Target String
 * lps -> abcd // output: 4
 * 
 * [DP]
 * dp table int[original string length][target string length]
 * Row: original String, col: target string
 * dp[i][j]: needed conversion number for originl(0..i) -> target(0..j)
 * 
 * original char == target char ? get previous conversion num
 *     dp[i][j] = dp[i - 1][j - 1]
 * else,
 *     we need to add one more conversion
 *     dp[i][j] = previous minimum conversion + 1
 *     case 1) dp[i - 1][j - 1] + 1 -> dp[i -1][j - 1] is min
 *     case 2) dp[i - 1][j] + 1
 *     case 3) dp [i][j - 1] + 1
 *     
 *     i = 2, j = 2
 *     abc -> acd 
 *     case 1) dp[i - 1][j - 1] + 1 -> ab vs ac
 *     case 2) dp[i - 1][j] + 1 -> ab vs acd
 *     case 3) dp [i][j - 1] + 1 -> abc vs ac
 * return the last element
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q11_StringConversionCounter {
    public static int findMinimumConversion(String original, String target) {
        int[][] dp = new int[original.length() + 1][target.length() + 1];
        for (int i = 0; i <= original.length(); i++) {
            for(int j = 0; j<= target.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = Math.max(i, j);
                    continue;
                }
                if (original.charAt(i - 1) == target.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i -1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[original.length()][target.length()];
    }

    public static void main(String[] args) {
        String original = "lps";
        String target = "abcd";
        System.out.println(findMinimumConversion(original, target));
        // output: 4
    }
}
