package leetcode.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249. Group Shifted Strings.
 * [String]
 * Iterate through the strings.
 * Shift the current string and make it starts with 'a'.
 *     new curr char = curr char - 'the first char' + 'a'
 *     if (new curr char < 'a'), new curr char += 26
 * Track the strings have the same key in a map<String, List<String>>.
 * Build a list and return it.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q249_GroupShiftedStrings {
    public static List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : strings) {
            String converted = convert(word);
            if (!map.containsKey(converted)) {
                map.put(converted, new ArrayList<>());
            }
            map.get(converted).add(word);
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> list : map.values()) {
            result.add(list);
        }

        return result;
    }

    private static String convert(String word) {
        StringBuilder sb = new StringBuilder();
        char start = word.charAt(0);
        for (int i = 0; i < word.length(); i++) {
            char c = (char) (word.charAt(i) - start + 'a');
            if (c < 'a') {
                c = (char) (c + 26);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strings = {"abc", "bcd", "zab", "yza", "eat", "thx"};
        System.out.println(groupStrings(strings));
        // output: [[abc, bcd, zab, yza], [thx], [eat]]
    }
}
