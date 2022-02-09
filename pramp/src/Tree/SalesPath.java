package Tree;

/**
 * base case: children.length == 0, return it's cost
 * for children, min(min from children)
 * return it's cost + miin from children
 * TC: O(n)
 * SC: O(n)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class SalesPath {
    static int getCheapestCost(Node rootNode) {
        if (rootNode == null) {
            return 0;
        }
        if (rootNode.children == null || rootNode.children.length == 0) {
            return rootNode.cost;
        }

        int min = Integer.MAX_VALUE;
        for (Node child : rootNode.children) {
            min = Math.min(min, getCheapestCost(child));
        }
        return rootNode.cost + min;
    }

    private static class Node {
        int cost;
        Node[] children;
        Node parent;

        Node(int cost) {
            this.cost = cost;
            children = null;
            parent = null;
        }
    }
}
