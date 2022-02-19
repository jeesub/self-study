package leetcode.String;

/**
 * 344. Reverse String.
 * [String]
 * left = 0, right = s.length() - 1
 * while left < right,
 *     swap(s[left], s[right])
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q344_ReverseString {
    public static void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }
}
