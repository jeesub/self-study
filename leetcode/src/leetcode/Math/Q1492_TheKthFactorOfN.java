package leetcode.Math;

import java.util.ArrayList;
import java.util.List;

/**
 * 1492. The kth Factor of n.
 * [Math]
 * [1, 2, 3, 4, 6, 12], n = 12, k = 5
 *              ^
 * [1, 3, 9], n = 9, k = 3
 *        ^
 * 1. From 1 to sqrt(n), if n % curr == 0, add curr to a list.
 *    If list's size >= k, return the number we have.
 * 2. If the last element * last element == n,
 *      Index to look at = 2 * list's size - k - 1
 *    Else,
 *      Index to look at = 2 * list's size - k
 * 3. Return -1 if the index is not valid.
 *    Else, return the n / list.get(index to look at)
 * TC: O(sqrt(n))
 * SC: O(k)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q1492_TheKthFactorOfN {
    public static int kthFactor(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
            }
            if (list.size() >= k) {
                return list.get(k - 1);
            }
        }

        int index = 2 * list.size() - k;
        int last = list.get(list.size() - 1);
        if (last * last == n) {
            index--;
        }
        if (index < 0) {
            return -1;
        }
        return n / list.get(index);
    }

    public static void main(String[] args) {
        System.out.println(kthFactor(12, 5));
        // output: 6
    }
}
