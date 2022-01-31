package leetcode.String;

/**
 * 266. Palindrome Permutation.
 * [String]
 * If we have one odd character or none, it's valid.
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q266_PalindromePermutation {
    public static boolean canPermutePalindrome(String s) {
        boolean[] odd = new boolean[26];
        int numOfOdd = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (odd[index]) {
                odd[index] = false;
                numOfOdd--;
            } else {
                odd[index] = true;
                numOfOdd++;
            }
        }
        return numOfOdd <= 1;
    }

    public static void main(String[] args) {
        String s = "abcab";
        System.out.println(canPermutePalindrome(s));
        // output: true
    }
}
