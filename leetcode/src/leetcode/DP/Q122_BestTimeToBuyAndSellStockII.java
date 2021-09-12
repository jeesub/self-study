package leetcode.DP;
/**
 * Q122. Best Time to Buy and Sell Stock II.
 * [DP]
 * Sum all the increment.
 * TC: O(n), where n is the length of input array.
 * SC: O(1)
 * @author Jeesub Lee (jeesubl@andrew.cmu.edu)
 */
public class Q122_BestTimeToBuyAndSellStockII {

    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] >= prices[i]) {
                continue;
            }
            profit += prices[i] - prices[i - 1];
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
        // output: 7
    }

}
