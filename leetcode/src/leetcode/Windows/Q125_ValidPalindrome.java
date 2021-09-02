package leetcode.Windows;
/**
 * Q125. Valid Palindrome.
 * [Two Pointers]
 * i starts from the start, and j starts from the end.
 * i++ while s[i] is not alphanumeric, and j-- while s[j] is not alphanumeric.
 * Make them lower cases and compare.
 * If they are same, continue.
 * Else, return false.
 * TC: O(n), where n is the length of input string
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q125_ValidPalindrome {

    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
        // output: true
    }

}
