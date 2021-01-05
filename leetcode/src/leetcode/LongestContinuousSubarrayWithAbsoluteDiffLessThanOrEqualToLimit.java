package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * 
 * 1. Double loop
 * 2. TreeMap
 * 3. Double Deque
 * 
 * i = starts from 0
 * j = from 0 to nums.length - 1
 * 
 * maxDeque: keeps max
 * 	- while new input is bigger, removeLast
 * 	- put new input into the Deque
 * 	-> peekFirst will return the max value in the subarray
 *
 * minDeque: keeps min
 * 	- while new input is smaller, removeLast
 * 	- put new input into the Deque
 * 	-> peekFirst will return the min value in the subarray
 *
 * if (max - min) > limit
 * 	remove max or min, if the value equals to nums[i]
 * 	i++
 * 	-> remove nums[i] from the subarray
 * 
 * By doing this, we can keep the longest length
 * Because we only need the length of the longest subarray,
 * we don't need to keep the exact i and j
 * @author jeesublee
 *
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

	public static int longestSubarrayTreeMap(int[] nums, int limit) {
		NavigableMap<Integer, Integer> tm = new TreeMap<>();
		int i = 0;
		for (int num : nums) {
			tm.put(num, tm.getOrDefault(num, 0) + 1);
			int max = tm.firstEntry().getKey();
			int min = tm.lastEntry().getKey();
			if (Math.abs(max - min) > limit) {
				tm.put(nums[i], tm.get(nums[i]) - 1);
				if (tm.get(nums[i]) == 0) {
					tm.remove(nums[i]);
				}
				i++;
			}
		}
		return nums.length - i;
	}

	public static int longestSubarrayDoubleDeque(int[] nums, int limit) {
		Deque<Integer> maxDeque = new ArrayDeque<>();
		Deque<Integer> minDeque = new ArrayDeque<>();
		int i = 0;
		for (int j = 0; j < nums.length; j++) {
			while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[j]) {
				maxDeque.removeLast();
			}
			while (!minDeque.isEmpty() && minDeque.peekLast() > nums[j]) {
				minDeque.removeLast();
			}
			maxDeque.addLast(nums[j]);
			minDeque.addLast(nums[j]);
			if (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
				if (maxDeque.peekFirst() == nums[i]) {
					maxDeque.removeFirst();
				}
				if (minDeque.peekFirst() == nums[i]) {
					minDeque.removeFirst();
				}
				i++;
			}
		}
		return nums.length - i;
	}

	public static void main(String[] args) {
		int[] nums = {10,1,2,4,7,2};
		int limit = 5;
		System.out.println(longestSubarrayTreeMap(nums, limit));
		System.out.println(longestSubarrayDoubleDeque(nums, limit));
		// output: 4
	}

}
