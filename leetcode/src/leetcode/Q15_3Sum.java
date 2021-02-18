package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q15. 3Sum.
 * 1. sort the array
 * 2. iterate through the sorted array
 * 3. find two sum from the right side
 * 
 * [-1, 0, 1, 2, -1, -4]
 * 
 * 1. sort
 * [-4, -1, -1, 0, 1, 2]
 * 
 * 2. iterate
 * [-4, -1, -1, 0, 1, 2]
 *   ^
 * the first one is -4
 * we want to find two sum with target 4 from the right side using two pointers
 * [-4, -1, -1, 0, 1, 2]
 *       ^            ^
 * cannot find the target
 * 
 * 
 * [-4, -1, -1, 0, 1, 2]
 *       ^
 * the second one is -1
 * we want to find two sum with target 1 from the right side
 * [-4, -1, -1, 0, 1, 2]
 *              ^  ^
 * find it
 * add it into the list and continue
 * because we have duplication, we need to handle it
 * if the pointer meets duplication, need to skip it
 * 
 * @author jeesublee
 */
public class Q15_3Sum {

	public static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			twoSum(nums, i, result);
		}
		return result;
	}

	private static void twoSum(int[] nums, int i, List<List<Integer>> result) {
		int target = -nums[i];
		int left = i + 1;
		int right = nums.length - 1;
		while (left < right) {
			int sum = nums[left] + nums[right];
			if (sum < target) {
				left++;
			} else if (sum > target) {
				right--;
			} else {
				result.add(Arrays.asList(nums[i], nums[left], nums[right]));
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
		int[] nums = {-1, 0, 1, 2, -1, -4};
		System.out.print(threeSum(nums));
		// output: [[-1, -1, 2], [-1, 0, 1]]
	}

}
