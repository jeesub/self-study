package leetcode.Windows;
/**
 * Q680. Valid Palindrome II.
 * [Two Pointers]
 * Check if the input string is palindrome
 * using two pointers, left pointer and right pointer.
 * If left char != right char, check
 *   1. if it is a palindrome (left pointer + 1, right pointer)
 *   2. if it is a palindrome (left pointer, right pointer - 1)
 *   If one is palindrome, return true.
 * TC: O(n), where n is the length of input string.
 * SC: O(1).
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q680_ValidPalindromeII {

    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);

            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(validPalindrome(s));
        // output: false
    }

}
