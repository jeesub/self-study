package DP;

/**
 * Decode Variations
 * [DP]
 *
 *   | X 1 2 6 2
 * --+-----------
 *   | 1 1 2 3 3
 *
 *    | X 2 7 1 0
 *  --+-----------
 *    | 1 1 1 1 1
 *
 * if the one-digit number is valid(num > 0), dp[n] = dp[n - 1]
 * if the two-digit number is valid(num >= 10 && num <= 26), dp[n] += decode[n - 2]
 *
 * TC: O(n)
 * SC: O(n) and we can improve it to O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class DecodeVariations {
    static int decodeVariations(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        if (s.charAt(0) != '0') {
            dp[1] = 1;
        }

        for (int i = 2; i < dp.length; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }
            int num = Integer.parseInt(s.substring(i - 2, i));
            if (num >= 10 && num <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        String s = "2710";
        System.out.println(decodeVariations(s));
        // output: 1
    }
}
