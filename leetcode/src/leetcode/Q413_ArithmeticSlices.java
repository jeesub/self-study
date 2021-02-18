package leetcode;
/**
 * Q413. Arithmetic Slides.
 * nums [0, 1, 2, 3]
 *             ^
 * dp   [0, 0, 1, 2] (numer of slices)
 * slice (0, 1, 2) is an arithmetic slice (diff = 1)
 * slice (1, 2, 3) is an arithmetic slice (diff = 1)
 * and (1, 2, 3, 4) is an arithmetic slice (diff = 1)
 *
 * slice(i - 2 .. i) and slice(i - 3 .. i - 1) has a same diff,
 * it means we have one more slice, slice (i - 3 .. i)
 * -> dp[i] = dp[i - 1] + 1
 * cnt += dp[i]
 * 
 * @author jeesublee
 *
 */
public class Q413_ArithmeticSlices {

	public static int numberOfArithmeticSlices(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = 0;
		dp[1] = 0;
		int cnt = 0;
		for (int i = 2; i < nums.length; i++) {
			if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
				dp[i] = dp[i - 1] + 1;
				cnt += dp[i];
			}
		}
		return cnt;
	}

	public static void main(String[] args) {
		int[] nums = {0, 1, 2, 3, 4, 3};
		System.out.print(numberOfArithmeticSlices(nums));
		// output: 6
	}

}
