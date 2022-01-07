package leetcode.Windows;

/**
 * 58. Length of Last Word
 * [Windows]
 * Backwards, get the first non-space character's index.
 * Start from the index, get the first space's index or 0 backwards.
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q58_LengthOfLastWord {
    public static int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }

        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }

        return end - start;
    }

    public static void main(String[] args) {
        String s = "  hello  world  ";
        System.out.println(lengthOfLastWord(s));
        // output: 5
    }
}
