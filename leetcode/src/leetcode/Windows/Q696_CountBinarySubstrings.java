package leetcode.Windows;
/**
 * Q696. Count Binary Substrings.
 * [Windows]
 * 00001111 has count 4.
 * 01, 0011, 000111, 00001111.
 *
 * Keep the Continuous count of prev and curr.
 * Compare prev and curr. Min of them is the current count.
 * ex) 00111000011 -> [2, 3, 4, 2]
 * 2, 3 -> 2
 * 3, 4 -> 3
 * 4, 2 -> 2
 * TC: O(n), where n is the length of the input string.
 * SC: O(1).
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q696_CountBinarySubstrings {

    public static int countBinarySubstrings(String s) {
        int prev = 0;
        int curr = 1;
        int count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                curr++;
                continue;
            }
            count += Math.min(prev, curr);
            prev = curr;
            curr = 1;
        }
        count += Math.min(prev, curr);
        return count;
    }

    public static void main(String[] args) {
        String s = "00111000011";
        System.out.println(countBinarySubstrings(s));
        // output: 7
    }

}
