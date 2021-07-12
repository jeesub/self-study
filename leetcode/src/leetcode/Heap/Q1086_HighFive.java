package leetcode.Heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Q1086. High Five.
 * [Map & Heap]
 * Build a map<ID, Min Heap of scores>
 * If the size of min heap is larger than 5, poll.
 * Iterate through the map and build a result array.
 * Sort the result array and return it.
 * TC: O(nlogk), where n is the number of items and k is the number of items in min heap
 * SC: O(m * k), where m is the number of ids and k is the number of items in min heap
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1086_HighFive {
    private static final int K = 5;

    public static int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> map = buildMap(items);
        return buildResult(map);
    }

    private static Map<Integer, PriorityQueue<Integer>> buildMap(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int[] item : items) {
            if (!map.containsKey(item[0])) {
                map.put(item[0], new PriorityQueue<>());
            }
            PriorityQueue<Integer> minHeap = map.get(item[0]);
            minHeap.offer(item[1]);
            while (minHeap.size() > K) {
                minHeap.poll();
            }
        }
        return map;
    }

    private static int[][] buildResult(Map<Integer, PriorityQueue<Integer>> map) {
        int[][] result = new int[map.size()][2];
        int index = 0;
        for (int id : map.keySet()) {
            PriorityQueue<Integer> minHeap = map.get(id);
            int sum = 0;
            while (!minHeap.isEmpty()) {
                sum += minHeap.poll();
            }
            result[index][0] = id;
            result[index][1] = sum / K;
            index++;
        }
        Arrays.sort(result, (a, b) -> (Integer.compare(a[0], b[0])));
        return result;
    }

    public static void main(String[] args) {
        int[][] items = {{1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100}, {2, 100}, {2, 76}};
        for (int[] each : highFive(items)) {
            System.out.print(Arrays.toString(each));
            System.out.print(" ");
        }
        System.out.println();
        // [1, 87] [2, 88]
    }

}
