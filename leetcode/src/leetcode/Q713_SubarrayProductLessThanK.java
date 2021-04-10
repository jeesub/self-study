package leetcode;
/**
 * Q713. Subarray Product Less Than K.
 * [Two Pointers]
 * i starts at 0.
 * Iterate through the array with pointer j.
 * If accumulate product > k (invalid), i increases until the product < k.
 * count += j - i + 1
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q713_SubarrayProductLessThanK {

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int i = 0;
        int product = 1;
        for (int j = 0; j < nums.length; j++) {
            product *= nums[j];
            while (i <= j && product >= k) {
                product /= nums[i++];
            }
            count += j - i + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        System.out.println(numSubarrayProductLessThanK(nums, k));
        // output: 8
    }

}
