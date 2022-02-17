package leetcode.List;

/**
 * 169. Majority Element.
 * [Boyer-Moore Voting Algorithm]
 * itereate through the array with int majority, int count
 *
 * if count == 0, majority = current num, count++
 * else if majority == current num, count++
 * else, count--
 *
 * after the iteration, we have majority
 * TC: O(n)
 * sC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q169_MajorityElement {
    public static int majorityElement(int[] nums) {
        int majority = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                majority = num;
                count++;
            } else if (majority == num) {
                count++;
            } else {
                count--;
            }
        }
        return majority;
    }
}
