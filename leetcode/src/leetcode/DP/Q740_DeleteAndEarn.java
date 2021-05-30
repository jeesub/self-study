package leetcode.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q740. Delete and Earn.
 * [DP]
 * Sort the list of Pairs<num, points> by num.
 * Iterate through the list and keep track of prevprev max and prev max.
 * Curr max so far is
 *   1. Including curr
 *      -> curr points + prevprev max points
 *   2. Not including curr
 *      -> prev max points
 *
 * [2,2]
 *   1. choose 2 and get 4
 *   2. not choose 2 and it doesn't make sense
 * [2,2,3,3,3]
 *   1. choose 3 and get 9
 *   2. not choose 3 and get the result of 2, prev max
 * [2,2,3,3,3,4]
 *   1. choose 4 and get 4 + the result of 2, prevprev max
 *   2. not choose 4 and get the result of 3, prev max
 * [2,2,3,3,3,4,5]
 *   1. choose 5 and get 5 + the result of 3, prevprev max
 *   2. not choose 5 and get the result of 4, prev max
 * [2,2,3,3,3,4,5,8]
 *   1. choose 8 and get 8 + max(the result of 5, the result of 4)
 *   2. not choose 8 and it doesn't make sense
 *
 * TC: O(nlogn), where n is the length of the input array.
 * SC: O(n), where n is the length of the input array.
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q740_DeleteAndEarn {

    public static int deleteAndEarn(int[] nums) {
        List<Pair> list = buildList(nums);
        int prevprev = 0;
        int prev = list.get(0).points;
        int max = prev;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).num + 1 < list.get(i).num) {
                max = list.get(i).points + Math.max(prevprev, prev);
            } else {
                max = Math.max(prevprev + list.get(i).points, prev);
            }
            prevprev = prev;
            prev = max;
        }
        return max;
    }

    private static List<Pair> buildList(int[] nums) {
        List<Pair> list = new ArrayList<>();
        Arrays.sort(nums);
        int num = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (num != nums[i]) {
                list.add(new Pair(num, num * count));
                num = nums[i];
                count = 1;
            } else {
                count++;
            }
        }
        list.add(new Pair(num, num * count));
        return list;
    }

    private static class Pair {
        int num;
        int points;

        private Pair(int newNum, int newPoints) {
            num = newNum;
            points = newPoints;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,2,3,3,3,4,5,8};
        System.out.println(deleteAndEarn(nums));
        // output: 22
    }

}
