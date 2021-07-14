package leetcode.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Q364. Nested List Weight Sum II.
 * Reveal every lists.
 * Make two lists, 1. list of integers and 2. list of depths.
 * Get the maxDepth. Use this to calculate weights.
 * TC: O(n), where n is the number of elements
 * SC: O(n), where n is the number of elements
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q364_NestedListWeightSumII {

    public static int depthSumInverse(List<NestedInteger> nestedList) {
        List<Integer> integers = new ArrayList<>();
        List<Integer> depths = new ArrayList<>();
        for (NestedInteger each : nestedList) {
            reveal(each, integers, depths, 1);
        }

        int maxDepth = 0;
        for (Integer each : depths) {
            maxDepth = Math.max(maxDepth, each);
        }

        int result = 0;
        for (int i = 0; i < integers.size(); i++) {
            int weight = maxDepth - depths.get(i) + 1;
            result += integers.get(i) * weight;
        }

        return result;
    }

    private static void reveal(NestedInteger nested, List<Integer> integers, List<Integer> depths, int depth) {
        if (nested.isInteger()) {
            integers.add(nested.getInteger());
            depths.add(depth);
            return;
        }

        for (NestedInteger each : nested.getList()) {
            reveal(each, integers, depths, depth + 1);
        }
    }

    private static class NestedInteger {
        Object nested;

        private NestedInteger() {}

        private NestedInteger(int value) {
            nested = value;
        }

        private boolean isInteger() {
            return nested instanceof Integer;
        }

        private Integer getInteger() {
            if (!(nested instanceof Integer)) {
                throw new RuntimeException("It is not an Integer");
            }
            return (Integer) nested;
        }

        private void setInteger(int value) {
            nested = value;
        }

        @SuppressWarnings("unchecked")
        private void add(NestedInteger ni) {
            if (!(nested instanceof List)) {
                List<Object> list = new ArrayList<>();
                if (nested != null) {
                    list.add(new NestedInteger((int) nested));
                }
                nested = list;
            }
            ((List<NestedInteger>) nested).add(ni);
        }

        @SuppressWarnings("unchecked")
        private List<NestedInteger> getList() {
            if (!(nested instanceof List)) {
                throw new RuntimeException("It is not a List");
            }
            return (List<NestedInteger>) nested;
        }
    }

    public static void main(String[] args) {
        NestedInteger first = new NestedInteger(1);
        NestedInteger second = new NestedInteger(4);
        NestedInteger third = new NestedInteger();
        third.add(new NestedInteger(6));
        second.add(third);
        first.add(second);
        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(first);
        // [1, [4, [6]]]
        System.out.println(depthSumInverse(nestedList));
        // output: 17
    }

}
