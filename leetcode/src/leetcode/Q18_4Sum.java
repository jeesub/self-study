package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q18. 4Sum.
 * fourSum: iterate through the array and find threeSum on the right side
 * threeSum: iterate through the array and find twoSum on the right side
 * twoSum: use two pointers to find a target value
 * 
 * nums = [1, 0, -1, 0, -2, 2], target = 0
 * sorted nums = [-2, -1, 0, 0, 1, 2]
 * 
 * @author jeesublee
 */
public class Q18_4Sum {

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.parallelSort(nums);
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < nums.length - 3; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			threeSum(nums, target, result,  i);
		}
		return result;
	}

	private static void threeSum(int[] nums, int target, List<List<Integer>> result, int i) {
		for (int j = i + 1; j < nums.length - 2; j++) {
			if (j != i + 1 && nums[j] == nums[j - 1]) {
				continue;
			}
			twoSum(nums, target, result, i, j);
		}
	}

	private static void twoSum(int[] nums, int target, List<List<Integer>> result, int i, int j) {
		int newTarget = target - nums[i] - nums[j];
		int left = j + 1;
		int right = nums.length - 1;
		while (left < right) {
			int sum = nums[left] + nums[right];
			if (sum < newTarget) {
				left++;
			} else if (sum > newTarget) {
				right--;
			} else {
				List<Integer> path = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
				result.add(path);
				while (left < right && nums[left] == nums[left + 1]) {
					left++;
				}
				while (left < right && nums[right] == nums[right - 1]) {
					right--;
				}
				left++;
				right--;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = {1, 0, -1, 0, -2, 2};
		int target = 0;
		System.out.print(fourSum(nums, target));
		// output: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
	}

}
