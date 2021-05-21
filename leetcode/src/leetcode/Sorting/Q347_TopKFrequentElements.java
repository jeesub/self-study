package leetcode.Sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q347. Top K Frequent Elements.
 * [Quickselect]
 * Build a map<num, freq>.
 * Put every element in the map into an array and do quickselect.
 * TC: O(n) in the average case and O(n^2) in the worst case, where n is the length of the input array.
 * SC: O(n), where n is the length of the input array.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q347_TopKFrequentElements {

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = buildMap(nums);
        int[] keyArray = buildArray(map);

        quickselect(keyArray, map, k, 0, keyArray.length - 1);

        int[] result = new int[k];
        System.arraycopy(keyArray, 0, result, 0, k);
        return result;
    }

    private static Map<Integer, Integer> buildMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int newFreq = map.getOrDefault(num, 0) + 1;
            map.put(num, newFreq);
        }
        return map;
    }

    private static int[] buildArray(Map<Integer, Integer> map) {
        int[] keyArray = new int[map.size()];
        int i = 0;
        for (int key : map.keySet()) {
            keyArray[i++] = key;
        }
        return keyArray;
    }

    private static void quickselect(int[] keyArray, Map<Integer, Integer> map, int k, int left, int right) {
        if (right == k - 1) {
            return;
        }

        int partition = partition(keyArray, map, left, right);

        if (partition > k - 1) {
            quickselect(keyArray, map, k, left, partition - 1);
        }
        if (partition < k - 1) {
            quickselect(keyArray, map, k, partition + 1, right);
        }
    }

    private static int partition(int[] keyArray, Map<Integer, Integer> map, int left, int right) {
        int pivot = map.get(keyArray[right]);
        int leftPointer = left - 1;
        int rightPointer = right;
        while (leftPointer < rightPointer) {
            while (map.get(keyArray[++leftPointer]) > pivot)
                ;
            while (rightPointer > 0 && map.get(keyArray[--rightPointer]) < pivot)
                ;
            if (leftPointer < rightPointer) {
                swap(keyArray, leftPointer, rightPointer);
            }
        }
        swap(keyArray, leftPointer, right);
        return leftPointer;
    }

    private static void swap(int[] keyArray, int first, int second) {
        int tmp = keyArray[first];
        keyArray[first] = keyArray[second];
        keyArray[second] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
        // output: [1, 2]
    }

}
