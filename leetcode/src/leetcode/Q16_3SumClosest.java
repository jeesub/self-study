package leetcode;

import java.util.Arrays;

/**
 * Q16. 3SumClosest.
 * sort the array
 * find the 3sum using target
 * keep and update of the information if diff is the smallest
 * 
 * @author jeesublee
 */
public class Q16_3SumClosest {

	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int diff = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length - 2; i++) {
			if (diff == 0) {
				return diff + target;
			}
			diff = findClosest(nums, target, diff, i);
		}
		return diff + target;
	}

	private static int findClosest(int[] nums, int target, int diff, int i) {
		int left = i + 1;
		int right = nums.length - 1;
		while (left < right) {
			int sum = nums[i] + nums[left] + nums[right];
			if (Math.abs(diff) > Math.abs(sum - target)) {
				diff = sum - target;
			}
			if (sum < target) {
				left++;
			} else if (sum > target) {
				right--;
			} else {
				return diff;
			}
		}
		return diff;
	}

	public static void main(String[] args) {
		int[] nums = {-1,2,1,-4};
		int target = 1;
		System.out.print(threeSumClosest(nums, target));
		// output: 2
	}

}
