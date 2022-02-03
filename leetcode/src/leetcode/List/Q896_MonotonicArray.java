package leetcode.List;

/**
 * 896. Monotonic Array.
 * [List]
 * While iterating, detect increasing and decreasing.
 * increased and decreased ? return false.
 * else, return true.
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q896_MonotonicArray {
    public static boolean isMonotonic(int[] nums) {
        boolean increased = false;
        boolean decreased = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                increased = true;
            } else if (nums[i - 1] > nums[i]) {
                decreased = true;
            }
        }
        if (increased && decreased) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 4, 4, 5};
        System.out.println(isMonotonic(nums));
        // output: true
    }
}
