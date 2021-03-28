package leetcode;
/**
 * Q63. Unique Paths II.
 * [DP]
 * dp[i][j] = the way to get this point.
 * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
 * If the point (i, j) is obstacle, dp[i][j] = 0.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q63_UniquePathsII {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int left = j > 0 ? dp[i][j - 1] : 0;
                int top = i > 0 ? dp[i - 1][j] : 0;
                dp[i][j] = left + top;
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
        // output: 2
    }

}
