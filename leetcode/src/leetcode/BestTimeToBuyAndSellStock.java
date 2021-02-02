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
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int price : prices) {
			if (minPrice > price) {
				minPrice = price;
			} else if (price - minPrice > maxProfit) {
				maxProfit = price - minPrice;
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
