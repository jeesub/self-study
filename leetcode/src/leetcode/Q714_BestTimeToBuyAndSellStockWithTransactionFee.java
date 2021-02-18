package leetcode;
/**
 * Q714. Best Time to Buy and Sell Stock with Transaction Fee.
 * int[] maxBuy & int[] maxSell track the best options
 * @author jeesublee
 */
public class Q714_BestTimeToBuyAndSellStockWithTransactionFee {

	public static int maxProfit(int[] prices, int fee) {
		if (prices.length == 0) {
			return 0;
		}
		int[] maxBuy = new int[prices.length];
		int[] maxSell = new int[prices.length];
		maxBuy[0] = -prices[0];
		maxSell[0] = 0;

		for (int i = 1; i < prices.length; i++) {
			maxBuy[i] = Math.max(maxBuy[i - 1], maxSell[i - 1] - prices[i]);
			maxSell[i] = Math.max(maxSell[i - 1], maxBuy[i - 1] + prices[i] - fee);
		}

		return maxSell[prices.length - 1];
	}

	public static void main(String[] args) {
		int[] prices = {1,3,2,8,4,9};
		int fee = 2;
		System.out.println(maxProfit(prices, fee));
		// output: 8
	}

}
