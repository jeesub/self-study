package leetcode.DFS;

/**
 * 130. Surrounded Regions.
 * [DFS]
 * step 1. from the edges, dfs and find the connected points
 *     they are alived, mark 'V' on them
 * step 2. change all 'O' to 'X'
 * step 3. change all 'V' to 'O'
 * TC: O(m * n)
 * SC: O(m * n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q130_SurroundedRegions {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final char O = 'O';
    private static final char X = 'X';
    private static final char V = 'V';

    public static void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == O) {
                dfs(board, i, 0);
            }
            if (board[i][board[0].length - 1] == O) {
                dfs(board, i, board[0].length - 1);
            }
        }
        for (int j = 1; j < board[0].length - 1; j++) {
            if (board[0][j] == O) {
                dfs(board, 0, j);
            }
            if (board[board.length - 1][j] == O) {
                dfs(board, board.length - 1, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == O) {
                    board[i][j] = X;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == V) {
                    board[i][j] = O;
                }
            }
        }
    }

    private static void dfs(char[][] board, int row, int col) {
        board[row][col] = V;

        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow < 0 || newCol < 0 || newRow >= board.length || newCol >= board[0].length) {
                continue;
            }
            if (board[newRow][newCol] != O) {
                continue;
            }
            dfs(board, newRow, newCol);
        }
    }
}
