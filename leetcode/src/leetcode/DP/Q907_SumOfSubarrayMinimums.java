package leetcode.DP;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 907. Sum of Subarray Minimums.
 * [DP & Monotonic Stack]
 * arr = [3, 1, 5, 4]
 * [3]          : [3]  -> 3
 *              Monotonic Stack: []
 *              dp[0] = (i + 1) * 3 = 3
 *              Monotonic Stack: [0(3)]
 *
 * [3, 1]       : [3, 1], [1] -> 1, 1
 *              Monotonic Stack: []
 *              dp[1] = (i + 1) * 1 = 2
 *              Monotonic Stack: [1(1)]
 *
 * [3, 1, 5]    : [3, 1, 5], [1, 5], [5] -> 1, 1, 5
 *              Monotonic Stack: [1(1)]
 *              dp[2] = dp[prevMin] + (i - prevMin) * curr = 2 + 5 = 7
 *              Monotonic Stack: [1(1), 2(5)]
 *
 * [3, 1, 5, 4] : [3, 1, 5, 4], [1, 5, 4], [5, 4], [4] -> 1, 1, 4, 4
 *              Monotonic Stack: [1(1)]
 *              dp[3] = dp[prevMin] + (i - prevMin) * curr
 *                    = dp[1] + (3 - 1) * 4 = 2 + 8 = 10
 *              Monotonic Stack: [1(1), 3(4)]
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q907_SumOfSubarrayMinimums {
    private static final int MOD = 1_000_000_007;

    public static int sumSubarrayMins(int[] arr) {
        int[] dp = new int[arr.length];
        Deque<Integer> monoStack = new ArrayDeque<>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!monoStack.isEmpty() && arr[monoStack.peekLast()] > arr[i]) {
                monoStack.removeLast();
            }
            if (monoStack.isEmpty()) {
                dp[i] = (i + 1) * arr[i];
            } else {
                dp[i] = dp[monoStack.peekLast()] + (i - monoStack.peekLast()) * arr[i];
            }
            monoStack.add(i);
            sum += dp[i];
            sum %= MOD;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 4};
        System.out.println(sumSubarrayMins(arr));
        // output: 22
    }
}
