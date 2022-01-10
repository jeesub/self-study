package leetcode.List;

/**
 * 921. Minimum Add to Make Parentheses Valid.
 * [List]
 * Iterate through the input string.
 * Keep track of the number of
 *   1. previous unpaired close parentheses
 *   2. open parentheses that we can close
 * If curr is open parenthesis, # of open parentheses++
 * Now curr is close parenthesis.
 * If # of open parentheses > 0, # of open parentheses--
 * Else, # of unpaired close parentheses++
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q921_MinimumAddToMakeParenthesesValid {
    public static int minAddToMakeValid(String s) {
        int unpairedClose = 0;
        int unpairedOpen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                unpairedOpen++;
                continue;
            }

            if (unpairedOpen > 0) {
                unpairedOpen--;
            } else {
                unpairedClose++;
            }
        }
        return unpairedClose + unpairedOpen;
    }

    public static void main(String[] args) {
        String s = ")())";
        System.out.println(minAddToMakeValid(s));
        // output: 2
    }
}
