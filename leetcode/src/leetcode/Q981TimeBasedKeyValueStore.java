package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Q981. Time Based Key-Value Store.
 * Map<String key, TreeMap<int timestamp, String value>>
 * set: stores input data
 * get: finds treemap data from the map and then find a result from the treemap
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q981TimeBasedKeyValueStore {

    public static final class TimeMap {
        Map<String, TreeMap<Integer, String>> map;

        /** Initialize your data structure here. */
        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> treeMap = map.getOrDefault(key, new TreeMap<Integer, String>());
            treeMap.put(timestamp, value);
            map.put(key, treeMap);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }
            TreeMap<Integer, String> treeMap = map.get(key);
            Integer floorKey = treeMap.floorKey(timestamp);
            return floorKey == null ? "" : treeMap.get(floorKey);
        }
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("mint", "icecream", 0);
        timeMap.set("mint", "chocolate", 4);
        timeMap.set("mint", "strawberry", 3);
        timeMap.set("mint", "coffee", 2);
        timeMap.set("mint", "cookie", 1);
        timeMap.set("kimchi", "jjigae", 2);
        System.out.println(timeMap.get("mint", 2));
        // output: coffee
    }
}
