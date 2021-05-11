package leetcode.DP;

public class Q64_MinimumPathSum {

    public static int minPathSum(int[][] grid) {
        int[] dp = init(grid);
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int top = dp[j];
                int left = j > 0 ? dp[j - 1] : Integer.MAX_VALUE;
                dp[j] = grid[i][j] + Math.min(top, left);
            }
        }
        return dp[dp.length - 1];
    }
    
    private static int[] init(int[][] grid) {
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        return dp;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
        // output: 7
    }

}
