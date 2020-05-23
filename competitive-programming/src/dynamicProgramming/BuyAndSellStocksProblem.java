package dynamicProgramming;

/**
 * The cost of a stock on each day is given in an array.
 * Find the max profit that you can make by buying and selling in those days.
 * 
 * For example,
 * If the given array is {100, 180, 260, 310, 40, 540, 690}, the maximum profit can earned by buying on day 0, selling on day 3.
 * Again buy on day 4 and sell on day 6.
 * 
 * If the given array of prices is sorted in decreasing order, then profit cannot be earned at all
 *
 */
public class BuyAndSellStocksProblem {
	
	public static int maximizeTheProfitWithNoTransactionLimits(int[] stocks) {
		if (stocks == null) {
			return -1;
		} else if (stocks.length == 1) {
			return 0;
		} else {
			int size = stocks.length;
			int maxProfit = _maximizeProfit(stocks, 0, size - 1);
			return maxProfit;
		}
	}
	
	// Naive solution
	private static int _maximizeProfit(int[] stocks, int start, int end) {
		if (stocks.length == 2) {
			if (stocks[0] < stocks[1]) {
				return stocks[1] - stocks[0];
			} else {
				return 0;
			}
		} else {
			int maxProfit = 0;
			for (int f = start; f < end; ++f) {
				for (int ff = f + 1; ff <= end; ++ff) {
					if (stocks[ff] > stocks[f]) {
						int currentProfit = (stocks[ff] - stocks[f]) + _maximizeProfit(stocks, start, f - 1) + _maximizeProfit(stocks, ff + 1, end);
						maxProfit = Math.max(maxProfit, currentProfit);
					}
				}
			}
			return maxProfit;
		}
	}
	
}


