package leetcode;

import java.util.Arrays;

/**
 * ProductOfArrayExceptSelf.
 * make two products array
 * left product array keeps data produced from left to right
 * right product array keeps data produced from right to left
 * output[i] = left[i - 1] * right[i + 1]
 * 
 * example
 * array [1, 2, 3, 4]
 * left  [1, 2, 6, 24]
 * right [24, 24, 12, 4]
 * output[0] = right[1]
 * output[1] = left[0] * right[2]
 * output[2] = left[1] * right[3]
 * 
 * simplify by using one array and two for loops
 * product = 1
 * for (i = 0..n)
 *    output[i] = product
 *    product *= array[i]
 * for (i = n..0)
 *    output[i] = product
 *    product *= array[i]
 * 
 * @author jeesublee
 *
 */
public class ProductArrayExceptSelf {

	public static int[] productArrayExceptSelf(int[] nums) {
		int[] result = new int[nums.length];

		int product = 1;
		for (int i = 0; i < nums.length; i++) {
			result[i] = product;
			product *= nums[i];
		}

		product = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			result[i] *= product;
			product *= nums[i];
		}

		return result;
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4};
		System.out.print(Arrays.toString(productArrayExceptSelf(nums)));
		// output: [24, 12, 8, 6]
	}

}
