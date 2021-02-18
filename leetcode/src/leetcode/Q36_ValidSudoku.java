package leetcode;
/**
 * Q36. Valid Sudoku.
 * 
 * [Validation check logic with 9 numbers]
 * if it has duplicate, return false
 *
 * [Main logic]
 * Check validation with every row, column, and 3x3 sub box.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q36_ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        return rowsAreValid(board) && columnsAreValid(board) && subBoxesAreValid(board);
    }

    private static boolean rowsAreValid(char[][] board) {
        for (int row = 0; row < 9; row++) {
            boolean[] cache = new boolean[9];
            for (int col = 0; col < 9; col++) {
                if (Character.isDigit(board[row][col])) {
                    int num = Character.getNumericValue(board[row][col]);
                    if (cache[num - 1]) {
                        return false;
                    }
                    cache[num - 1] = true;
                }
            }
        }
        return true;
    }

    private static boolean columnsAreValid(char[][] board) {
        for (int col = 0; col < 9; col++) {
            boolean[] cache = new boolean[9];
            for (int row = 0; row < 9; row++) {
                if (Character.isDigit(board[row][col])) {
                    int num = Character.getNumericValue(board[row][col]);
                    if (cache[num - 1]) {
                        return false;
                    }
                    cache[num - 1] = true;
                }
            }
        }
        return true;
    }

    private static boolean subBoxesAreValid(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean[] cache = new boolean[9];
                for (int row = i * 3; row < i * 3 + 3; row++) {
                    for (int col = j * 3; col < j * 3 + 3; col++) {
                        if (Character.isDigit(board[row][col])) {
                            int num = Character.getNumericValue(board[row][col]);
                            if (cache[num - 1]) {
                                return false;
                            }
                            cache[num - 1] = true;
                        }
                    }
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
                {'.','.','.','.','8','.','.','7','9'}};
        System.out.println(isValidSudoku(board));
        // output: true
    }
}
