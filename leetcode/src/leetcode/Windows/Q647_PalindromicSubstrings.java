package leetcode.Windows;

/**
 * 647. Palindromic Substrings.
 * [String]
 * iterate through the input string
 * in the iteration, use two pointers to count the palindrome
 * case 1. odd numbers:
 *     left = i, right = i
 *     check if char[left--] == char[right++]
 * case 2. even numbers:
 *     left = i - 1, right = i
 *     check if char[left--] == char[right++]
 * TC: O(n^2)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q647_PalindromicSubstrings {
    public static int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }

            left = i - 1;
            right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }
        return count;
    }
}
