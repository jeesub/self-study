package Tree;

/**
 * targetSoFar = -1
 * case 0. curr == null
 *     return targetSoFar
 * case 1. num <= curr.key
 *     // a target is on the left side.
 *     curr = curr.left
 * case 2. curr.key < num
 *     // curr can be a target. update targetSoFar.
 *     targetSoFar = curr.key
 *     curr = curr.right
 * TC: O(h)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class LargestSmallerBSTKey {
    private Node root;

    int findLargestSmallerKey(int num) {
        int targetSoFar = -1;
        Node curr = root;
        while (curr != null) {
            if (num <= curr.key) {
                curr = curr.left;
            } else {
                targetSoFar = curr.key;
                curr = curr.right;
            }
        }
        return targetSoFar;
    }

    private static class Node {
        int key;
        Node left;
        Node right;
        Node parent;

        Node(int key) {
            this.key = key;
            left = null;
            right = null;
            parent = null;
        }
    }
}
