package leetcode.Greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * 1647. Minimum Deletions to Make Character Frequencies Unique.
 * [Greedy & HashMap]
 * 1. Count freq using an array.
 * 2. Build freq map <freq, num of characters>
 * 3. Iterate through the keys and modify the freq if it's not unique.
 *    Best case: remove one character makes the freq unique.
 *    Else ? remove more characters while freq is unique until freq > 0
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1647_MinimumDeletionsToMakeCharacterFrequenciesUnique {
    public int minDeletions(String s) {
        int[] freqs = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freqs[s.charAt(i) - 'a']++;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] == 0) {
                continue;
            }
            if (!map.containsKey(freqs[i])) {
                map.put(freqs[i], 1);
            } else {
                map.put(freqs[i], map.get(freqs[i]) + 1);
            }
        }

        int deletes = 0;
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] == 0 || map.get(freqs[i]) <= 1) {
                continue;
            }

            int nextFreq = freqs[i] - 1;
            while (nextFreq > 0 && map.containsKey(nextFreq)) {
                nextFreq--;
            }
            map.put(freqs[i], map.get(freqs[i]) - 1);
            map.put(nextFreq, 1);
            deletes += freqs[i] - nextFreq;
        }
        return deletes;
    }
}
