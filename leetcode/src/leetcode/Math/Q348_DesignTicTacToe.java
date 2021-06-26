package leetcode.Math;
/**
 * Q348. Design Tic-Tac-Toe.
 * Track the checked rows, columns, and diagonals.
 * Mark a player one as 1 and two as -1.
 * If the abs(number) is n, the player is a winner.
 * TC: O(1)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q348_DesignTicTacToe {

    private static class TicTacToe {
        private static final int NOT_ENDED = 0;
        private int[] rows;
        private int[] cols;
        private int leftDiagonal;
        private int rightDiagonal;

        private TicTacToe(int n) {
            rows = new int[n];
            cols = new int[n];
        }

        public int move(int row, int col, int player) {
            if (gameEnded(row, col, player)) {
                return player;
            }
            return NOT_ENDED;
        }

        private boolean gameEnded(int row, int col, int player) {
            if (player == 2) {
                player = -1;
            }

            cols[row] += player;
            if (Math.abs(cols[row]) == cols.length) {
                return true;
            }

            rows[col] += player;
            if (Math.abs(rows[col]) == rows.length) {
                return true;
            }

            if (row + col + 1 == rows.length) {
                leftDiagonal += player;
                if (Math.abs(leftDiagonal) == rows.length) {
                    return true;
                }
            }

            if (row == col) {
                rightDiagonal += player;
                if (Math.abs(rightDiagonal) == rows.length) {
                    return true;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(2);
        System.out.print(game.move(0, 0, 1) + ", ");
        System.out.print(game.move(1, 0, 2) + ", ");
        System.out.println(game.move(1, 1, 1));
        // output: 0, 0, 1
    }

}
