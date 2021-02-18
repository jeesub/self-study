package leetcode;

import java.util.Arrays;

/**
 * Q167. Two Sum II - Input array is sorted.
 * use two pointers
 * one starts from the start, another one starts from the end
 * if sum < target, left pointer++
 * else if sum > target, right pointer++
 * else, we found it
 * @author jeesublee
 */
public class Q167_TwoSumII {

	public static int[] twoSum(int[] numbers, int target) {
		int left = 0;
		int right = numbers.length - 1;
		while (left < right) {
			int sum = numbers[left] + numbers[right];
			if (sum < target) {
				left++;
			} else if (sum > target) {
				right--;
			} else {
				return new int[] { left + 1, right + 1 };
			}
		}
		return new int[] { -1, -1 };
	}

	public static void main(String[] args) {
		int[] numbers = {2, 7, 11, 15};
		int target = 9;
		System.out.print(Arrays.toString(twoSum(numbers, target)));
		// output: [1, 2]
	}
}
