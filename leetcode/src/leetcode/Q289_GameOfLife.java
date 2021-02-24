package leetcode;

import java.util.Arrays;

/**
 * Q 289. Game of Life.
 * Update each cell with new number.
 * case 1. curr = 0, next = 0 -> 0
 * case 2. curr = 1, next = 1 -> 1
 * case 3. curr = 0, next = 1 -> 2
 * case 4. curr = 1, next = 0 -> 3
 *
 * Iterate again and update every cell.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q289_GameOfLife {

    public static void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                updateCell(board, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }
    }

    private static void updateCell(int[][] board, int row, int col) {
        int cnt = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
                    continue;
                }
                if (i == row && j == col) {
                    continue;
                }
                if (board[i][j] % 2 == 1) {
                    cnt++;
                }
            }
        }
        if (board[row][col] == 0 && cnt == 3) {
            board[row][col] = 2;
            return;
        }
        if (board[row][col] == 1 && (cnt < 2 || cnt > 3)) {
            board[row][col] = 3;
            return;
        }
    }

    public static void main(String[] args) {
        int[][] input = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        gameOfLife(input);
        for (int[] each : input) {
            System.out.println(Arrays.toString(each));
        }
        // output: 
        // [0, 0, 0]
        // [1, 0, 1]
        // [0, 1, 1]
        // [0, 1, 0]
    }

}
