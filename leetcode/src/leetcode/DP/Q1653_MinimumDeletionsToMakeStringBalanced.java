package leetcode.DP;

/**
 * 1653. Minimum Deletions to Make String Balanced.
 * [DP : Accumulated Array]
 *        a a b a b b a b
 * accB = 0 0 1 1 2 2 2 3 (forward)
 * accA = 4 3 2 2 1 1 1 0 (backward)
 *              ^ at this point, remove b before curr & remove a after curr
 *                = 1 + 1 = 2
 * TC: O(n)
 * SC: O(n)
 *
 * [DP: Optimization]
 *        a a b a b b a b
 *              ^ at this point, remove curr 'a' or remove previous 'b's
 *                min = min(remove curr 'a', remove all prev 'b's)
 *                remove curr 'a' = prev min + 1
 *                remove all prev 'b's = num of prev 'b's
 *                ^ at this point, if the previous string is valid, it's also valid
 * TC: O(n)
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1653_MinimumDeletionsToMakeStringBalanced {
    public int minimumDeletions(String s) {
        int min = 0;
        int bs = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                min = Math.min(min + 1, bs);
            } else {
                bs++;
            }
        }
        return min;
    }
}
