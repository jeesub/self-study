package leetcode.BTR;

import java.util.ArrayList;
import java.util.List;

/**
 * 465. Optimal Account Balancing.
 * [BTR]
 * 1. build a balance sheet
 * [[0,1,10],[2,0,5],[2,3,3]]
 * (0: -10 + 5 = -5)
 * (1: +10)
 * (2: -5 -3 = -8)
 * (3: +3)
 *
 * 2. BTR
 * Non-zero should be paired.
 * [-5, 10, -8, 3]
 *   ^ can pair with 10 or 3
 *     pair with 10
 *     -> [0, 5, -8, 3]
 *            ^ can pair with -8
 *            -> [0, 0, -3, 3]
 *                       ^ can pair with 3
 *                       -> [0, 0, 0, 0]
 *                                    ^ it'll be always zero
 *     pair with 3
 *     -> [0, 10, -8, -2]
 *             ^ can pair with -8 or -2
 *             -> [0, 0, 2, -2]
 * TC: O(n + k!), n = transactions.length, k = # of non-zero debt person
 * SC: O(m), m = # of person
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q465_OptimalAccountBalancing {
    public static int minTransfers(int[][] transactions) {
        int[] balances = buildBalance(transactions);
        balances = getNonZeros(balances);
        return dfs(balances, 0);
    }

    private static int[] buildBalance(int[][] transactions) {
        int[] balances = new int[21];
        for (int[] transaction : transactions) {
            balances[transaction[0]] -= transaction[2];
            balances[transaction[1]] += transaction[2];
        }
        return balances;
    }

    private static int[] getNonZeros(int[] balances) {
        List<Integer> list = new ArrayList<>();
        for (int balance : balances) {
            if (balance == 0) {
                continue;
            }
            list.add(balance);
        }
        balances = new int[list.size()];
        for (int i = 0; i < balances.length; i++) {
            balances[i] = list.get(i);
        }
        return balances;
    }

    private static int dfs(int[] balances, int start) {
        if (start >= balances.length - 1) {
            return 0;
        }
        if (balances[start] == 0) {
            return dfs(balances, start + 1);
        }

        int minTrans = Integer.MAX_VALUE;
        for (int i = start + 1; i < balances.length; i++) {
            if (balances[start] * balances[i] >= 0) {
                continue;
            }
            balances[i] += balances[start];
            minTrans = Math.min(minTrans, dfs(balances, start + 1));
            balances[i] -= balances[start];
        }
        return minTrans + 1;
    }
}
