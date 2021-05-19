package leetcode.Heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Q347. Top K Frequent Elements.
 * [Map and Heap]
 * Build a map<num, freq>.
 * Put every element in the map into a MinHeap sorted by freq.
 * Keep the heap size k.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q347_TopKFrequentElements {

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = buildMap(nums);
        PriorityQueue<Integer> minHeap = buildMinHeap(map, k);
        return buildResult(minHeap);
    }

    private static Map<Integer, Integer> buildMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int newFreq = map.getOrDefault(num, 0) + 1;
            map.put(num, newFreq);
        }
        return map;
    }

    private static PriorityQueue<Integer> buildMinHeap(Map<Integer, Integer> map, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> (Integer.compare(map.get(a), map.get(b))));
        for (Integer key : map.keySet()) {
            minHeap.offer(key);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap;
    }

    private static int[] buildResult(PriorityQueue<Integer> minHeap) {
        int[] result = new int[minHeap.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
        // output: [2, 1]
    }

}
