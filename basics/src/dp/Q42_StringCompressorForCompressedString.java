package dp;
/**
 * Q42. String Compressor for Compressed String.
 * [DP]
 * Main logic
 * dp[i][j] should be
 * 1. s.substring(i, j + 1)
 * 2. combination of dp[i][k] + dp[k + 1][j], where k = (i + 1 .. j - 1)
 * 3. if s.substring(i, j + 1) can be compressed, and it is shorter than current one,
 *     if repeated string is s.substring(i, pos + 1),
 *     dp[i][j] should be "repeated number" + "[" + s.substring(i, pos + 1) + "]"
 * 
 * starts iteration
 * 1. from i = 0 and length = 1 to i = 0 and length = s.length() - 1
 * 2. to i = s.length() - 1 and length = 1
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q42_StringCompressorForCompressedString {
    private static String[][] dp;
    public static String compress(String s) {
        dp = new String[s.length()][s.length()];
        for (int len = 1; len <= s.length(); len++) {
            for (int start = 0; start + len <= s.length(); start++) {
                int end = start + len - 1;
                String curr = s.substring(start, end + 1);
                dp[start][end] = curr;
                for (int mid = start + 1; mid <= end - 1; mid++) {
                    String newString = dp[start][mid] + dp[mid + 1][end];
                    if (newString.length() < dp[start][end].length()) {
                        dp[start][end] = newString;
                    }
                }
                String newString = deepCompress(curr, start);
                if (newString.length() < dp[start][end].length()) {
                    dp[start][end] = newString;
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    private static String deepCompress(String s, int start) {
        int pos = s.repeat(2).indexOf(s, 1);
        if (pos >= s.length()) {
            return s;
        }
        return String.valueOf(s.length() / pos) + "[" + dp[start][start + pos - 1] + "]";
    }

    public static void main(String[] args) {
        String s = "abbbabbbcabbbabbbc";
        System.out.println(compress(s));
    }
}
