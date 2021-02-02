package leetcode;
/**
 * 
 * iterate through the given prices array
 *   update the minimum cost if possible
 *   update the maximum profit if possible
 * 
 * @author jeesublee
 *
 */
public class BestTimeToBuyAndSellStock {

	public static int maxProfit(int[] prices) {
		int minCost = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int price : prices) {
			if (minCost > price) {
				minCost = price;
				continue;
			}
			if (price - minCost > maxProfit) {
				maxProfit = price - minCost;
			}
		}
		return maxProfit;
	}

	public static void main(String[] args) {
		int[] prices = {7,1,5,3,6,4};
		System.out.print(maxProfit(prices));
		// output: 5
	}

}
