package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Q1348. Tweet Counts Per Frequency.
 * Map<String name, TreeMap<int time, int freq>>
 * [Get]
 * Calculate time duration. ex) [10-69], [70-129], ...
 * Iterate the TreeMap and build a result list.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1348_TweetCountsPerFrequency {
    public static class TweetCounts {
        private Map<String, TreeMap<Integer, Integer>> map;

        public TweetCounts() {
            map = new HashMap<>();
        }

        public void recordTweet(String tweetName, int time) {
            if (!map.containsKey(tweetName)) {
                map.put(tweetName, new TreeMap<>());
            }
            TreeMap<Integer, Integer> treeMap = map.get(tweetName);
            treeMap.put(time, treeMap.getOrDefault(time, 0) + 1);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            if (!map.containsKey(tweetName)) {
                return new ArrayList<>();
            }
            int duration = getDuration(freq);
            int length = (endTime - startTime) / duration + 1;
            int[] result = new int[length];
            TreeMap<Integer, Integer> treeMap = map.get(tweetName);

            for (Map.Entry<Integer, Integer> entry : treeMap.subMap(startTime, true, endTime, true).entrySet()) {
                int timeSlot = (entry.getKey() - startTime) / duration;
                int num = entry.getValue();
                result[timeSlot] += num;
            }
            List<Integer> resultList = new ArrayList<>();
            for (int each : result) {
                resultList.add(each);
            }
            return resultList;
        }

        private static int getDuration(String freq) {
            switch (freq) {
            case "minute":
                return 60;
            case "hour":
                return 3600;
            case "day":
                return 86400;
            default:
                throw new RuntimeException("Wrong freq input");
            }
        }
    }

    public static void main(String[] args) {
        TweetCounts tc = new TweetCounts();
        for (int i = 1; i < 120; i++) {
            tc.recordTweet("twt1", i);
        }
        for (int i = 1; i < 120; i = i + 2) {
            tc.recordTweet("twt1", i);
        }
        for (int i = 1; i < 120; i = i + 3) {
            tc.recordTweet("twt1", i);
        }
        for (int i = 1; i < 120; i = i + 7) {
            tc.recordTweet("twt1", i);
        }
        tc.recordTweet("twt1", 150);
        System.out.println(tc.getTweetCountsPerFrequency("minute", "twt1", 1, 180));
        // output: [119, 117, 1]
    }
}
