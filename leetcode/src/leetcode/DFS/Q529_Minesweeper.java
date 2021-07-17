package leetcode.DFS;

import java.util.Arrays;

/**
 * Q529. Minesweeper.
 * [DFS]
 * If curr == Mine, change it to 'X' and return.
 * If curr is digit, update it and return.
 * Else, change it to 'B' and DFS to 8 neighbor cells.
 * TC: O(m * n)
 * SC: O(m * n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q529_Minesweeper {
    private static final int[][] DIRS = { { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { -1, 0 },
            { -1, -1 } };

    public static char[][] updateBoard(char[][] board, int[] click) {
        char[][] result = new char[board.length][board[0].length];
        for (int i = 0; i < result.length; i++) {
            System.arraycopy(board[i], 0, result[i], 0, result[i].length);
        }

        int row = click[0];
        int col = click[1];

        if (board[row][col] == 'M') {
            result[row][col] = 'X';
            return result;
        }

        int numOfMines = countMines(board, row, col);
        if (numOfMines != 0) {
            result[row][col] = (char) ('0' + numOfMines);
            return result;
        }

        dfs(board, result, row, col);
        return result;
    }

    private static int countMines(char[][] board, int row, int col) {
        int numOfMines = 0;

        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (!isValid(board, newRow, newCol)) {
                continue;
            }
            if (board[newRow][newCol] == 'M') {
                numOfMines++;
            }
        }

        return numOfMines;
    }

    private static void dfs(char[][] board, char[][] result, int row, int col) {
        if (!isValid(board, row, col) || result[row][col] != 'E') {
            return;
        }

        int numOfMines = countMines(board, row, col);
        if (numOfMines != 0) {
            result[row][col] = (char) ('0' + numOfMines);
            return;
        }

        result[row][col] = 'B';

        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            dfs(board, result, newRow, newCol);
        }
    }

    private static boolean isValid(char[][] board, int row, int col) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
        int[] click = {3, 0};
        char[][] result = updateBoard(board, click);
        for (char[] each : result) {
            System.out.println(Arrays.toString(each));
        }
        // output:
        // [B, 1, E, 1, B]
        // [B, 1, M, 1, B]
        // [B, 1, 1, 1, B]
        // [B, B, B, B, B]
    }

}
