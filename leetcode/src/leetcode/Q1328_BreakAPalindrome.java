package leetcode;
/**
 * Q1328. Break a Palindrome.
 * If the length of palindrome is 1, we cannot convert it.
 * Return empty string.
 * Case 1.
 * a b c c b a
 * ^ ^ ^
 * Before half, if we have non-a, change it to 'a'.
 * Case 2.
 * We cannot do anything with the middle one if the word has odd length.
 * Case 3.
 * If we don't have non-a except for the middle one, change the last char to 'b'.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1328_BreakAPalindrome {

    public static String breakPalindrome(String palindrome) {
        if (palindrome.length() == 1) {
            return "";
        }
        char[] word = palindrome.toCharArray();
        for (int i = 0; i < word.length / 2; i++) {
            if (word[i] != 'a') {
                word[i] = 'a';
                return new String(word);
            }
        }
        word[word.length - 1] = 'b';
        return new String(word);
    }

    public static void main(String[] args) {
        String palindrome = "aabaa";
        System.out.println(breakPalindrome(palindrome));
        // output: aabab
    }

}
