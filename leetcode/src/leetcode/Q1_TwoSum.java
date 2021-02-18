package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Q1. Two Sum.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1_TwoSum {

	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int[] result = new int[2];

		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				result[0] = map.get(target - nums[i]);
				result[1] = i;
				break;
			} else {
				map.put(nums[i], i);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = {2, 7, 11, 15};
		int target = 9;
		int[] result = twoSum(nums, target);
		System.out.println(Arrays.toString(result));
	}

}
