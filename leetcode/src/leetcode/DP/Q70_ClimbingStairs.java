package leetcode.DP;
/**
 * [DP]
 * dp[i] = dp[i - 2] + dp[i - 1]
 * TC: O(n)
 * SC: O(n)
 *
 * [DP; Fibonacci Matrix]
 * [[Fn+1, Fn], [Fn, Fn-1]] = [[1, 1], [1, 0]]^n
 * Ladder(n) = Fibonacci(n - 1)
 * Fn+1 = the answer we are looking for.
 * TC: O(logn)
 * SC: O(logn)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q70_ClimbingStairs {
    private static final int[][] ROOT_MATRIX = { { 1, 1 }, { 1, 0 } };

    public static int climbStairs(int n) {
        return fibonacci(n)[0][0];
    }

    private static int[][] fibonacci(int n) {
        if (n == 1) {
            return ROOT_MATRIX;
        }

        int[][] result = fibonacci(n / 2);
        result = multiply(result, result);

        if (n % 2 == 0) {
            return result;
        }
        return multiply(result, ROOT_MATRIX);
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[a.length][b[0].length];
        result[0][0] = a[0][0] * b[0][0] + a[1][0] * b[0][1];
        result[0][1] = a[0][0] * b[0][1] + a[1][0] * b[1][1];
        result[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        result[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
        return result;
    }

    public static int climbStairsDP(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 9;
        System.out.println(climbStairs(n));
        System.out.println(climbStairsDP(n));
        // output: 55
    }

}
