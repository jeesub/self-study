package leetcode.DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Q364. Nested List Weight Sum II.
 * [DFS]
 * Reveal every list.
 * Make two lists, 1. list of integers and 2. list of depths.
 * Get the maxDepth. Use this to calculate weights.
 * TC: O(n), where n is the number of elements
 * SC: O(n), where n is the number of elements
 *
 * [BFS]
 * Reveal each layer at a time.
 * Keep the accumulated sum and each number's sum.
 * Before start each layer, add each number's sum to the accumulate sum (weighting).
 * If the current node is integer, add it.
 * If the current node is list, add it to a queue.
 * TC: O(n^2), where n is the number of elements
 * SC: O(n), where n is the number of elements
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q364_NestedListWeightSumII {

    public static int depthSumInverseDFS(List<NestedInteger> nestedList) {
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

    public static int depthSumInverseBFS(List<NestedInteger> nestedList) {
        Deque<NestedInteger> deque = new ArrayDeque<>();
        for (NestedInteger each : nestedList) {
            deque.add(each);
        }
        
        int sum = 0;
        int weights = 0;
        while (!deque.isEmpty()) {
            sum += weights;
            int i = deque.size();
            while(i > 0) {
                i--;
                NestedInteger curr = deque.remove();
                if (curr.isInteger()) {
                    sum += curr.getInteger();
                    weights += curr.getInteger();
                    continue;
                }
                for (NestedInteger each : curr.getList()) {
                    deque.add(each);
                }
            }
        }
        
        return sum;
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
        System.out.println(depthSumInverseDFS(nestedList));
        // output: 17
        System.out.println(depthSumInverseBFS(nestedList));
        // output: 17
    }

}
