package DP;

/**
 * Bracket Match.
 *
 * numOfUnmatchedOpen
 * numOfUnmatchedClose
 *
 * case 1. open bracket
 *     numOfUnmatchedOpen++
 * case 2. close bracket
 *     case 2 - 1. numOfUnmatchedOpen > 0
 *         numOfUnmatchedOpen--
 *     case 2 - 2. numOfUnmatchedOpen <= 0
 *         numOfUnmatchedClose++
 * return numOfUnmatchedOpen + numOfUnmatchedClose
 *
 * ( ) ) ( // ()()()
 * ^ ^ ^ ^
 * 1 0 0 1 // numOfUnmatchedOpen
 * 0 0 1 1 // numOfUnmatchedClose
 *
 * ) ) ( ( ) ) ( ) ) // () () ( ( ) ) ( ) ()
 * 0 0 1 2 1 0 1 0 0 // numOfUnmatchedOpen
 * 1 2 2 2 2 2 2 2 3 // numOfUnmatchedClose
 *
 * ) ( // () ()
 * 0 1 // numOfUnmatchedOpen
 * 1 1 // numOfUnmatchedClose
 *
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class BracketMatch {
    private static final char OPEN = '(';
    private static final char CLOSE = ')';

    static int bracketMatch(String text) {
        int numOfOpen = 0;
        int numOfClose = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == OPEN) {
                numOfOpen++;
                continue;
            }

            if (numOfOpen > 0) {
                numOfOpen--;
            } else {
                numOfClose++;
            }
        }

        return numOfOpen + numOfClose;
    }

    public static void main(String[] args) {
        String text = "))(())())";
        System.out.println(bracketMatch(text));
        // output: 3
    }
}
