package leetcode.String;

/**
 * 387. First Unique Character in a String.
 * Count the characters by iterating the string.
 * In the second iteration, check if the current character is unique.
 * If it is, return the index.
 * If we cannot find an unique character, return -1.
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q387_FirstUniqueCharacterInAString {
    public static int firstUniqChar(String s) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (counts[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "lovelydog";
        System.out.println(firstUniqChar(s));
        // output: 2
    }
}
