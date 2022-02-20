package leetcode.DP;

/**
 * 1216. Valid Palindrome III.
 * [BTR & DP]
 * abcdeca
 * ^     ^
 *  ^   ^ -> 1. check requiredK(i + 1, j)
 *           2. check requiredK(i, j - 1)
 *           return min(result of 1, result of 2) + 1
 *
 * base case: start == end, return 0
 * base case: we have seen dp[start][end], return dp[star][end]
 * while i < j,
 *   if s[i] == s[j], i++, j--
 *   else return dp[start][end] = min(recursion(i + 1, j), recursion(i, j - 1)) + 1
 * after the while loop,
 *   return dp[start][end] = 0
 *
 * memoization with dp[start][end]
 * TC: O(n^2)
 * SC: O(n^2)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1216_ValidPalindromeIII {
    public static boolean isValidPalindrome(String s, int k) {
        int[][] dp = new int[s.length()][s.length()];
        return requiredK(dp, s, 0, s.length() - 1) <= k;
    }

    private static int requiredK(int[][] dp, String s, int start, int end) {
        if (start == end) {
            return 0;
        }
        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        int i = start;
        int j = end;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return dp[start][end] = Math.min(requiredK(dp, s, i + 1, j), requiredK(dp, s, i, j - 1)) + 1;
            }
        }
        return dp[start][end] = 0;
    }
}
