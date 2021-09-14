package leetcode.List;
/**
 * Q974. Subarray Sums Divisible by K.
 * [List]
 * 1. Build an accumulate sum % k array. Put 0 in the beginning.
 * 2. Iterate through the acc%k array keeping track counts of acc%k so far.
 * nums  =    [4, 5, 0, -2, -3, 1], k = 5
 * acc%k = [0, 4, 4, 4, 2, 4, 0]
 *          ^ total count += <count 0>, <count 0>++
 *
 *             ^ total count += <count 4>, <count 4>++
 *               total count = 0, <count 4> = 1
 *
 *                ^ total count += <count 4>, <count 4>++
 *                  total count = 1, <count 4> = 2
 *                  // (1, 2) makes 0 subarray sum // [5]
 *
 *                   ^ total count += <count 4>, <count 4>++
 *                     total count = 3, <count 4> = 3
 *                     // (1, 3), (2, 3) make 0 subarray sum // [5, 0], [0]
 *
 *                      ^ total count += <count 2>, <count 2>++
 *                        total count = 3, <count 2> = 1
 *
 *                         ^ (1, 5), (2, 5), (3, 5)
 *                           total count += <count 4>, <count 4>++
 *                           total count = 6, <count 4> = 4
 *                           // [5, 0, -2, -3], [0, -2, -3], [-2, -3]
 *
 *                            ^ (0, 5)
 *                              total count += <count 0>, <count 0>++
 *                              total count = 7, <count 0> = 2
 *                              // [4, 5, 0, -2, -3, 1]
 * TC: O(n), where n is the length of the input array
 * SC: O(n), where n is the length of the input array
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q974_SubarraySumsDivisibleByK {

    public static int subarraysDivByK(int[] nums, int k) {
        int[] acc = new int[nums.length + 1];
        for (int i = 1; i < acc.length; i++) {
            acc[i] = ((acc[i - 1] + nums[i - 1]) % k + k) % k;
        }

        int[] count = new int[k];
        int totalCount = 0;
        for (int i = 0; i < acc.length; i++) {
            totalCount += count[acc[i]];
            count[acc[i]]++;
        }

        return totalCount;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 0, -2, -3, 1};
        int k = 5;
        System.out.println(subarraysDivByK(nums, k));
        // output: 7
    }

}
