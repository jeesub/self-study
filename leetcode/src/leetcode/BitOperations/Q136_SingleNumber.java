package leetcode.BitOperations;

/**
 * 136. Single Number.
 * [Bit Operations]
 * [4, 1, 2, 1, 2]
 * 1 ^ 1 = 0
 * 2 ^ 2 = 0
 * 0 ^ 4 = 4
 * 4 ^ 1 ^ 2 ^ 1 ^ 2 = (1 ^ 1) ^ (2 ^ 2) ^ 4 = 4
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q136_SingleNumber {
    public int singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }
}
