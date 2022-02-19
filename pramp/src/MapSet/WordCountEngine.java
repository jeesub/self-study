package MapSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Word Count Engine.
 * 1. split document using " " -> String[]
 * 2. word -> only letters & lower cases
 *    Map<clear word, count> freqMap
 *    Map<clear word, first appearance> appearanceMap
 * 3. String[][] result contains [clear word][count]
 * 4. Sort the result array by
 *    1. count
 *    2. first appearance
 * TC: O(nlogn)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class WordCountEngine {
    static String[][] wordCountEngine(String document) {
        String[] words = document.split(" ");

        Map<String, Integer> freqMap = new HashMap<>();
        Map<String, Integer> appearanceMap = new HashMap<>();

        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                if (Character.isLetter(word.charAt(j))) {
                    sb.append(Character.toLowerCase(word.charAt(j)));
                }
            }
            if (sb.length() == 0) {
                continue;
            }
            String curr = sb.toString();
            if (!freqMap.containsKey(curr)) {
                freqMap.put(curr, 1);
                appearanceMap.put(curr, i);
            } else {
                freqMap.put(curr, freqMap.get(curr) + 1);
            }
        }

        String[][] result = new String[freqMap.size()][2];
        int index = 0;
        for (String key : freqMap.keySet()) {
            result[index][0] = key;
            result[index][1] = freqMap.get(key).toString();
            index++;
        }

        Arrays.sort(result, (a, b) -> {
            if (freqMap.get(a[0]) != freqMap.get(b[0])) {
                return Integer.compare(freqMap.get(b[0]), freqMap.get(a[0]));
            }
            return Integer.compare(appearanceMap.get(a[0]), appearanceMap.get(b[0]));
        });

        return result;
    }
}
