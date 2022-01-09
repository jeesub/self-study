package leetcode.DFS;

import java.util.List;

/**
 * 339. Nested List Weight Sum.
 * [DFS, Recursion]
 * Iterate through the elements.
 * If curr is an element, calculate the value.
 * If curr is a list, recursively call itself with depth + 1.
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q339_NestedListWeightSum {

    private interface NestedInteger {
        public boolean isInteger();

        public Integer getInteger();

        public List<NestedInteger> getList();
    }
    
    public static int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }

    private static int depthSum(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger each : nestedList) {
            if (each.isInteger()) {
                sum += each.getInteger() * depth;
            } else {
                sum += depthSum(each.getList(), depth + 1);
            }
        }
        return sum;
    }
}
