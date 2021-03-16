package leetcode;
/**
 * Q45. Jump Game II.
 * [2, 3, 1, 1, 4]
 * Iterate through the given array.
 * Keep 1. the current reachable point 2. the most far reachable point for the next jump.
 * Update the most far reachable point every time.
 * When we reach the current reachable point, decide what the next jump is.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q45_JumpGameII {

    public static int jump(int[] nums) {
        int current = 0;
        int next = nums[0];
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            next = Math.max(next, i + nums[i]);
            if (i == current) {
                current = next;
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 0, 4, 5, 1, 1};
        System.out.println(jump(nums));
        // output: 3
    }

}
