package leetcode.Windows;
/**
 * Q915. Partition Array into Disjoint Intervals.
 * Iterate through the array.
 * Keeps left max, total max and a partition index.
 * curr <= left max ? partition = i + 1, left max = total max, continue
 * [3, 0, 5, 2, 6]
 *  ^ leftMax = 3, totalMax = 3, partition = 1
 *     ^ leftMax = 3, totalMax = 3, partition = 2
 *        ^ leftMax = 3, totalMax = 5, partition = 2
 *            ^ leftMax = 5, totalMax = 5, partition = 4
 *               ^ leftMax = 5, totalMax = 6, partition = 4
 * [3, 0, 5, 2], [6]
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q915_PartitionArrayIntoDisjointIntervals {

    public static int partitionDisjoint(int[] nums) {
        int leftMax = nums[0];
        int totalMax = nums[0];
        int partition = 1;
        for (int i = 1; i < nums.length; i++) {
            totalMax = Math.max(totalMax, nums[i]);
            if (nums[i] < leftMax) {
                partition = i + 1;
                leftMax = totalMax;
            }
        }
        return partition;
    }

    public static void main(String[] args) {
        int[] nums = {3, 0, 5, 2, 6};
        System.out.println(partitionDisjoint(nums));
        // output: 4
    }

}
