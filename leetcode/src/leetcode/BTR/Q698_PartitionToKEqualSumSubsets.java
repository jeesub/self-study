package leetcode.BTR;

import java.util.Arrays;

/**
 * Q698. Partition to K Equal Sum Subsets.
 * [BTR]
 * Make every possible combination.
 *
 * [Pruning]
 * 1. totalSum % k != 0 ? return false
 * 2. max num > targetSum ? return false
 * 3. currentSum == targetSum ? restart to check everything
 *    currentSum < targetSum ? check smaller numbers
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q698_PartitionToKEqualSumSubsets {

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = 0;
        int maxNum = 0;
        for (int num : nums) {
            totalSum += num;
            maxNum = Math.max(maxNum, num);
        }

        int targetSum = totalSum / k;
        if (totalSum % k != 0 || maxNum > targetSum) {
            return false;
        }

        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        return canPartitionKSubsets(nums, targetSum, visited, nums.length - 1, 0, k);
    }

    private static boolean canPartitionKSubsets(int[] nums, int targetSum, boolean[] visited, int pointer, int partSum,
            int remainParts) {
        if (remainParts == 0) {
            return true;
        }

        for (int i = pointer; i >= 0; i--) {
            if (visited[i]) {
                continue;
            }
            if (partSum + nums[i] == targetSum) {
                visited[i] = true;
                if (canPartitionKSubsets(nums, targetSum, visited, nums.length - 1, 0, remainParts - 1)) {
                    return true;
                }
                visited[i] = false;
            }
            if (partSum + nums[i] < targetSum) {
                visited[i] = true;
                if (canPartitionKSubsets(nums, targetSum, visited, i - 1, partSum + nums[i], remainParts)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(canPartitionKSubsets(nums, k));
        // output: true
    }

}
