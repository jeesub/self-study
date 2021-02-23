package leetcode;

import java.util.Arrays;

/**
 * Q37. Sudoku Solver.
 * [BTR, DFS]
 * Check the validation before assigning a number (from 1 to 9).
 * If we can put the number, put it and call a next DFS.
 * If we find out we cannot go further, go back and try the other numbers.
 * If we reach the end, it means we found a solution.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q37_Sudoku_Solver {
    private static final char[] NUMS = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public static void solveSudoku(char[][] board) {
        dfsSudoku(board, 0);
    }

    private static boolean dfsSudoku(char[][] board, int index) {
        if (index == 81) {
            return true;
        }
        int row = index / 9;
        int col = index % 9;
        if (board[row][col] != '.') {
            return dfsSudoku(board, index + 1);
        }
        for (char num : NUMS) {
            board[row][col] = num;
            if (isValid(board, row, col)) {
                if (dfsSudoku(board, index + 1)) {
                    return true;
                }
            }
        }
        board[row][col] = '.';
        return false;
    }

    private static boolean isValid(char[][] board, int row, int col) {
        return rowIsValid(board, row, col) && columnIsValid(board, row, col) && subBoxIsValid(board, row, col);
    }

    private static boolean rowIsValid(char[][] board, int row, int col) {
        char num = board[row][col];
        for (int i = 0; i < 9; i++) {
            if (i == col) {
                continue;
            }
            if (board[row][i] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean columnIsValid(char[][] board, int row, int col) {
        char num = board[row][col];
        for (int i = 0; i < 9; i++) {
            if (i == row) {
                continue;
            }
            if (board[i][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean subBoxIsValid(char[][] board, int row, int col) {
        char num = board[row][col];
        for (int i = row - row % 3; i < row - row % 3 + 3; i++) {
            for (int j = col - col % 3; j < col - col % 3 + 3; j++) {
                if (i == row && j == col) {
                    continue;
                }
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
                };
        solveSudoku(board);
        for (char[] each : board) {
            System.out.println(Arrays.toString(each));
        }
        /* 
         * output: 
         * [5, 3, 4, 6, 7, 8, 9, 1, 2]
         * [6, 7, 2, 1, 9, 5, 3, 4, 8]
         * [1, 9, 8, 3, 4, 2, 5, 6, 7]
         * [8, 5, 9, 7, 6, 1, 4, 2, 3]
         * [4, 2, 6, 8, 5, 3, 7, 9, 1]
         * [7, 1, 3, 9, 2, 4, 8, 5, 6]
         * [9, 6, 1, 5, 3, 7, 2, 8, 4]
         * [2, 8, 7, 4, 1, 9, 6, 3, 5]
         * [3, 4, 5, 2, 8, 6, 1, 7, 9]
         */
    }

}
