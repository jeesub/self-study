package leetcode.List;

/**
 * 189. Rotate Array.
 * [List]
 * k %= nums.length
 * while curr != i
 *   remember nums[next] // next = (curr + k) % nums.length
 *   update nums[next] = remembered nums[next] from the prev
 *   curr = next
 * if we've changed all numbers, stop
 * else, i++
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q189_RotateArray {
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        if (k == 0) {
            return;
        }

        for (int i = 0, moves = 0; moves < nums.length; i++) {
            int curr = i;
            int prevNum = nums[curr];
            do {
                int next = (curr + k) % nums.length;
                int tmp = nums[next];
                nums[next] = prevNum;
                prevNum = tmp;
                curr = next;
                moves++;
            } while (curr != i);
        }
    }
}
