package leetcode;
/**
 * Q1594. Maximum Non Negative Product in a Matrix.
 * [DP]
 * 4^29 > 2^31. Use long.
 * Stores and tracks min and max in 2d arrays respectively.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1594_MaximumNonNegativeProductInAMatrix {

    public static int maxProductPath(int[][] grid) {
        long[][] maxDp = new long[grid.length][grid[0].length];
        long[][] minDp = new long[grid.length][grid[0].length];
        init(grid, maxDp, minDp);

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                long upMax = maxDp[i - 1][j] * grid[i][j];
                long leftMax = maxDp[i][j - 1] * grid[i][j];
                long upMin = minDp[i - 1][j] * grid[i][j];
                long leftMin = minDp[i][j - 1] * grid[i][j];
                long max = Math.max(Math.max(upMax, leftMax), Math.max(upMin, leftMin));
                long min = Math.min(Math.min(upMax, leftMax), Math.min(upMin, leftMin));
                maxDp[i][j] = max;
                minDp[i][j] = min;
            }
        }

        long moduler = (long) Math.pow(10, 9) + 7;
        long result = maxDp[grid.length - 1][grid[0].length - 1];
        result %= moduler;
        return result < 0 ? -1 : (int) result;
    }

    private static void init(int[][] grid, long[][] maxDp, long[][] minDp) {
        maxDp[0][0] = grid[0][0];
        minDp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            long product = maxDp[i - 1][0] * grid[i][0];
            maxDp[i][0] = product;
            minDp[i][0] = product;
        }
        for (int i = 1; i < grid[0].length; i++) {
            long product = maxDp[0][i - 1] * grid[0][i];
            maxDp[0][i] = product;
            minDp[0][i] = product;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1, -2, 1}, {1, -2, 1}, {3, -4, 1}};
        System.out.println(maxProductPath(grid));
        // output: 8
    }

}
