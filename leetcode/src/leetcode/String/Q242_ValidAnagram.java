package leetcode.String;

/**
 * 242. Valid Anagram.
 * [String]
 * iterate through the string s, and build a letter-count array
 * iterate through the string t, and subtract the count of letters from the array
 * if all of the elements in the array is zeros, it's valid
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q242_ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            counts[t.charAt(i) - 'a']--;
        }
        for (int count : counts) {
            if (count == 0) {
                continue;
            }
            return false;
        }
        return true;
    }
}
