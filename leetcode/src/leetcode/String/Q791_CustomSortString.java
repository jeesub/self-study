package leetcode.String;

/**
 * 791. Custom Sort String.
 * [String]
 * Build a character counter of input string s.
 * Iterate through the order and build an ordered string.
 * Append the unused characters.
 * TC: O(m + n), where m = order's length, n = s's length
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q791_CustomSortString {
    public static String customSortString(String order, String s) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }

        char[] result = new char[s.length()];
        int index = 0;
        for (int i = 0; i < order.length(); i++) {
            while (counts[order.charAt(i) - 'a'] > 0) {
                result[index++] = order.charAt(i);
                counts[order.charAt(i) - 'a']--;
            }
        }

        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                result[index++] = (char)('a' + i);
                counts[i]--;
            }
        }

        return new String(result);
    }

    public static void main(String[] args) {
        String order = "zyx";
        String s = "axbyz";
        System.out.println(customSortString(order, s));
        // output: zyxab
    }
}
