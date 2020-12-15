package leetcode;

public class ProductArrayExceptSelf {

	public int[] productArrayExceptSelf(int[] nums) {
		int[] result = new int[nums.length];
		int val = 1;

		for (int i = 0; i < nums.length; i++) {
			result[i] = val;
			val *= nums[i];
		}

		val = 1;

		for (int i = nums.length - 1; i >= 0; i--) {
			result[i] *= val;
			val *= nums[i];
		}

		return result;
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4};
		int[] result = new int[4];
		ProductArrayExceptSelf solution = new ProductArrayExceptSelf();
		result = solution.productArrayExceptSelf(nums);
		for (int num: result) {
			System.out.print(num + " ");
		}
		// output: 24 12 8 6
	}

}
