package leetcode.TreeSetTreeMap;

import java.util.TreeSet;

/**
 * 1675. Minimize Deviation in Array.
 * [TreeSet]
 * while we can increase min, increase min
 * while we can decrease max, decrease max
 * TC: O(n*logn*logk), k = num
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1675_MinimizeDeviationInArray {
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            treeSet.add(num);
        }

        int deviation = treeSet.last() - treeSet.first();
        int min = treeSet.first();
        while (min % 2 == 1) {
            treeSet.add(treeSet.pollFirst() * 2);
            min = treeSet.first();
            deviation = Math.min(deviation, treeSet.last() - treeSet.first());
        }

        int max = treeSet.last();
        while (max % 2 == 0) {
            treeSet.add(treeSet.pollLast() / 2);
            max = treeSet.last();
            deviation = Math.min(deviation, treeSet.last() - treeSet.first());
        }

        return deviation;
    }
}
