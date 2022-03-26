package leetcode.String;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 151. Reverse Words in a String.
 * [String & Windows]
 * Backwards, append words.
 * TC: O(n)
 * SC: O(n)
 *
 * [String]
 * Use Java utils. Slow but easy to understand.
 * Trim, split, and reverse.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q151_ReverseWordsInAString {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i;
        int j = s.length() - 1;
        while (j >= 0) {
            while (j >= 0 && s.charAt(j) == ' ') {
                j--;
            }
            if (j < 0) {
                break;
            }
            i = j;
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            sb.append(s, i + 1, j + 1).append(" ");
            j = i;
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public String reverseWordsUtils(String s) {
        List<String> list = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }
}
