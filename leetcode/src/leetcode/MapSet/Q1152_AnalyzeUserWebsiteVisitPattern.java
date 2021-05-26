package leetcode.MapSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Q1152. Analyze User Website Visit Pattern.
 * [Map]
 * HashMap 1 <username, List sorted by timestamp>
 * HashMap 2 <username, Set of 3-sequence>
 * HashMap 3 <3-sequence, frequency>
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1152_AnalyzeUserWebsiteVisitPattern {

    private static final String DELIMITER = ",";
    
    public static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> userMap = buildUserMap(username, timestamp, website);
        Map<String, Set<String>> usersSequenceMap = buildUsersSequenceMap(userMap);
        Map<String, Integer> freqMap = buildFreqMap(usersSequenceMap);
        return getMostVisitedPattern(freqMap);
    }
    
    private static Map<String, List<Pair>> buildUserMap(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> userMap = new HashMap<>();
        for (int i = 0; i < username.length; i++) {
            Pair currPair = new Pair(timestamp[i], website[i]);
            if (!userMap.containsKey(username[i])) {
                List<Pair> list = new ArrayList<Pair>();
                list.add(currPair);
                userMap.put(username[i], list);
                continue;
            }
            userMap.get(username[i]).add(currPair);
        }
    
        for (List<Pair> list : userMap.values()) {
            Collections.sort(list);
        }
    
        return userMap;
    }
    
    private static Map<String, Set<String>> buildUsersSequenceMap(Map<String, List<Pair>> userMap) {
        Map<String, Set<String>> usersSequenceMap = new HashMap<>();
        for (String user : userMap.keySet()) {
            List<Pair> list = userMap.get(user);
            if (list.size() < 3) {
                continue;
            }
            Set<String> set = new HashSet<>();
    
            Set<String> twoSequenceSet = new HashSet<>();
            String twoSequenceSeed = new StringBuilder().append(list.get(0).site).append(DELIMITER).append(list.get(1).site)
                    .append(DELIMITER).toString();
            twoSequenceSet.add(twoSequenceSeed);
    
            for (int i = 2; i < list.size(); i++) {
                String curr = list.get(i).site;
    
                for (String each : twoSequenceSet) {
                    String threeSequence = new StringBuilder().append(each).append(curr).toString();
                    set.add(threeSequence);
                }
                for (int j = 0; j < i; j++) {
                    String twoSequence = new StringBuilder().append(list.get(j).site).append(DELIMITER).append(curr)
                            .append(DELIMITER).toString();
                    twoSequenceSet.add(twoSequence);
                }
            }
            usersSequenceMap.put(user, set);
        }
        return usersSequenceMap;
    }
    
    private static Map<String, Integer> buildFreqMap(Map<String, Set<String>> usersSequenceMap) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (Set<String> set : usersSequenceMap.values()) {
            for (String sequence : set) {
                int newFreq = freqMap.getOrDefault(sequence, 0) + 1;
                freqMap.put(sequence, newFreq);
            }
        }
        return freqMap;
    }
    
    private static List<String> getMostVisitedPattern(Map<String, Integer> freqMap) {
        String mostVisitedKey = null;
        int mostVisitedFreq = 0;
        for (String key : freqMap.keySet()) {
            if (freqMap.get(key) < mostVisitedFreq) {
                continue;
            }
            if (freqMap.get(key) == mostVisitedFreq && mostVisitedKey != null && mostVisitedKey.compareTo(key) < 0) {
                continue;
            }
            mostVisitedKey = key;
            mostVisitedFreq = freqMap.get(key);
        }
    
        String[] array = mostVisitedKey.split(DELIMITER);
        return List.of(array);
    }
    
    private static class Pair implements Comparable<Pair> {
        int time;
        String site;
    
        private Pair(int newTime, String newSite) {
            time = newTime;
            site = newSite;
        }
    
        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public static void main(String[] args) {
        String[] username = {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
        int[] timestamp = {1,2,3,4,5,6,7,8,9,10};
        String[] website = {"home","about","career","home","cart","maps","home","home","about","career"};
        System.out.println(mostVisitedPattern(username, timestamp, website));
        // output: [home, about, career]
    }

}
