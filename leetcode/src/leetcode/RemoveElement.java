package leetcode;

import java.util.Arrays;

/**
 * 
 * two pointers
 * runner iterate through the nums
 * if runner's value is same as val, continue
 * else, set walker's value as runner's value
 * 
 * @author jeesublee
 *
 */
public class RemoveElement {

	public static int removeElement(int[] nums, int val) {
		int walker = 0;
		for (int runner = 0; runner < nums.length; runner++) {
			if (nums[runner] == val) {
				continue;
			}
			nums[walker] = nums[runner];
			walker++;
		}
		return walker;
	}

	public static void main(String[] args) {
		int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
		int val = 2;
		System.out.println(removeElement(nums, val));
		System.out.println(Arrays.toString(nums));
		// output: 5, [0, 1, 3, 0 , 4, 0, 4, 2]
	}

}
