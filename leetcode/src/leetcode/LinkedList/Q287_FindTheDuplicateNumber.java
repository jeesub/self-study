package leetcode.LinkedList;

/**
 * 287. Find the Duplicate Number.
 * [Linked List & Cycle Detection]
 * a = distance before entering the cycle
 * c = length of the cycle
 * a + k = walker's steps when the walker and runner meets
 *
 * distance_runner = a + nc + k
 * distance_walker = a + k
 *
 * distance_runner = 2 * distance_walker
 * a + nc + k = 2a + 2k
 * nc = a + k
 *
 * nc - k = a
 * // nc - k = steps that current runner can reach the cycle entrance by walking
 * // a = stepts that a walker cun reach the cycle entrance from the beginning
 *
 * 1. walker walks & runner runs until they meet
 * 2. walker walks from the beginning & runner walks until they meet
 * 3. the point they meet is the entrance of the cycle (duplicate)
 *
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q287_FindTheDuplicateNumber {
    public static int findDuplicate(int[] nums) {
        int walker = 0;
        int runner = 0;

        while (true) {
            walker = nums[walker];
            runner = nums[nums[runner]];
            if (walker == runner) {
                break;
            }
        }

        walker = 0;
        while (walker != runner) {
            walker = nums[walker];
            runner = nums[runner];
        }

        return walker;
    }
}
