package leetcode.BTR;

import java.util.ArrayList;
import java.util.List;

/**
 * Q51. N-Queens.
 * [BTR & DFS]
 * DFS row by row.
 * If the current point is invalid, continue.
 * Else, update the invalid points and move on to the next row.
 * [Validation]
 * Mark invalid points.
 * 1. Row should be different.
 * 2. Column should be different.
 * 3. Diagonal, (row++, col++) and (row--, col--) should be empty.
 *    We only need to mark (row++, col++) because we are going downward.
 * 4. Diagonal, (row++, col--) and (row--, col++) should be empty.
 *    We only need to mark (row++, col--) because we are going downward.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q51_NQueens {

    public static List<List<String>> solveNQueens(int n) {
        boolean[][] path = new boolean[n][n];
        List<List<String>> result = new ArrayList<>();
        dfs(n, path, new boolean[n][n], result, 0);
        return result;
    }

    private static void dfs(int n, boolean[][] path, boolean[][] occupied, List<List<String>> result, int row) {
        if (row >= n) {
            result.add(buildResult(n, path));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (occupied[row][i]) {
                continue;
            }
            path[row][i] = true;
            boolean[][] newOccupied = new boolean[n][n];
            for (int j = 0; j < n; j++) {
                System.arraycopy(occupied[j], 0, newOccupied[j], 0, n);
            }
            updateOccupied(n, row, i, newOccupied);
            dfs(n, path, newOccupied, result, row + 1);
            path[row][i] = false;
        }
    }

    private static List<String> buildResult(int n, boolean[][] path) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (path[i][j]) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    private static void updateOccupied(int n, int row, int col, boolean[][] newOccupied) {
        for (int i = 0; i < n; i++) {
            newOccupied[row][i] = true;
        }
        for (int i = row; i < n; i++) {
            newOccupied[i][col] = true;
        }

        int i = row + 1;
        int j = col + 1;
        while (i >= 0 && j >= 0 && i < n && j < n) {
            newOccupied[i][j] = true;
            i++;
            j++;
        }
        i = row + 1;
        j = col - 1;
        while (i >= 0 && j >= 0 && i < n && j < n) {
            newOccupied[i][j] = true;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(solveNQueens(n));
        // output: [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
    }

}
