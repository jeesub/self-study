package leetcode;

import java.util.Arrays;

/**
 * Q26. Remove Duplicates from Sorted Array.
 * two pointers
 * runner runs when the value is same as walker's
 * if the value of runner is different from walker's,
 * it's time to record it
 * at the end, we can return walker + 1
 * 
 * @author jeesublee
 */
public class Q26_RemoveDuplicatesFromSortedArray {

	public static int removeDuplicates(int[] nums) {
		int walker = 0;
		for (int runner = 0; runner < nums.length; runner++) {
			if (nums[walker] == nums[runner]) {
				continue;
			}
			walker++;
			nums[walker] = nums[runner];
		}
		return walker + 1;
	}

	public static void main(String[] args) {
		int[] nums = {0,0,1,1,1,2,2,3,3,4};
		System.out.println(removeDuplicates(nums));
		System.out.println(Arrays.toString(nums));
		// output: 5, [0, 1, 2, 3, 4, 2, 2, 3, 3, 4]
	}

}
