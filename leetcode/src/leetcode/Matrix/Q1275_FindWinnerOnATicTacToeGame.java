package leetcode.Matrix;

/**
 * 1275. Find Winner on a Tic Tac Toe Game.
 * [Matrix]
 * Keep track of rows[], columns[], diagonal, and two diagonals.
 * X: [0, 0] -> row = [1, 0, 0], col = [1, 0, 0], left diagonal = 1, right diagonal = 0
 * O: [1, 1] -> row = [1, -1, 0], col = [1, -1, 0], left diagonal = 0, right diagonal = 0
 * X: [2, 0] -> row = [1, -1, 1], col = [2, -1, 0], left diagonal = 0, right diagonal = 1
 * if one of the numbers == 3, X wins.
 * if one of the numbers == -3, O wins.
 * if moves.length() < 9, it's pending.
 * if moves.length() == 9, it's draw.
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1275_FindWinnerOnATicTacToeGame {
    public String tictactoe(int[][] moves) {
        int[] rows = new int[3];
        int[] cols = new int[3];
        int leftDiag = 0;
        int rightDiag = 0;
        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            int curr = i % 2 == 0 ? 1 : -1;
            rows[move[0]] += curr;
            cols[move[1]] += curr;
            if (move[0] == move[1]) {
                leftDiag += curr;
            }
            if (move[0] + move[1] == 2) {
                rightDiag += curr;
            }
        }

        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == 3 || cols[i] == 3) {
                return "A";
            }
            if (rows[i] == -3 || cols[i] == -3) {
                return "B";
            }
        }
        if (leftDiag == 3 || rightDiag == 3) {
            return "A";
        }
        if (leftDiag == -3 || rightDiag == -3) {
            return "B";
        }
        if (moves.length < 9) {
            return "Pending";
        }
        return "Draw";
    }
}
