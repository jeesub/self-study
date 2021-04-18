package leetcode;
/**
 * Q55. Jump Game.
 * Iterate through the nums.
 * Keeps the maximum reachable index.
 * The maximum reachable index >= nums.length - 1 ? return true.
 * Curr point is not reachable ? return false.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q55_JumpGame {

    public static boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max < i) {
                return false;
            }
            max = Math.max(max, i + nums[i]);
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(canJump(nums));
        // output: true
    }

}
