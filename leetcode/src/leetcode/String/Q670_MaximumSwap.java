package leetcode.String;

import java.util.ArrayList;
import java.util.List;

/**
 * 670. Maximum Swap.
 * Iterate through the numbers
 * Build an index table that stores the last index of the numbers.
 * idx[i] = the last index of number i.
 * From start (the biggest digit), check the biggest numbers to swap
 *     where the number is bigger than the current number.
 * If we don't have such a number, move on to the next biggest digit.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q670_MaximumSwap {
    public static int maximumSwap(int num) {
        int[] nums = buildNums(num);
        int[] idx = new int[10];
        for (int i = 0; i < nums.length; i++) {
            idx[nums[i]] = i;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = idx.length - 1; j > nums[i]; j--) {
                if (idx[j] <= i) {
                    continue;
                }
                nums[idx[j]] = nums[i];
                nums[i] = j;
                return buildNum(nums);
            }
        }

        return num;
    }

    private static int[] buildNums(int num) {
        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 10);
            num /= 10;
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[nums.length - i - 1] = list.get(i);
        }
        return nums;
    }

    private static int buildNum(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result *= 10;
            result += nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int num = 1024;
        System.out.println(maximumSwap(num));
        // output: 4021
    }
}
