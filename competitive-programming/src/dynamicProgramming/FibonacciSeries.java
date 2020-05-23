package dynamicProgramming;

import java.util.Arrays;

public class FibonacciSeries {

	/**
	 * Bottom-Up approach of Dynamic Programming
	 * 
	 * @param terms
	 * @return
	 */
	public static int fibonacciSeriesWithTabulation(int terms) {
		if (terms < 1) {
			System.out.println("Number of terms is less than 1");
			System.exit(-1);
		} else if (terms < 2) {
			if (terms == 1)
				return 0;
			if (terms == 2)
				return 1;
		}
		return _computeFibonacciSeriesWithTabulation(terms);
	}
	
	private static int _computeFibonacciSeriesWithTabulation(int terms) {
		int[] table = new int[terms];
		Arrays.fill(table, -1);
		table[0] = 0;
		table[1] = 1;

		for (int f = 2; f < terms; f++) {
			table[f] = table[f - 1] + table[f - 2];
		}

		return table[terms - 1];
	}
	
	/**
	 * Top-Down approach of Dynamic Programming
	 * 
	 * @param terms
	 * @return
	 */
	public static int fibonacciSeriesWithMemoization(int terms) {
		if (terms < 1) {
			System.out.println("Number of terms is less than 1");
			System.exit(-1);
		}

		int[] table = new int[terms];
		Arrays.fill(table, -1);
		int result = _computeFibonacciSeriesWithMemoization(terms, table);
		return result;
	}
	
	private static int _computeFibonacciSeriesWithMemoization(int terms, int[] table) {
		if (terms == 1) {
			return 0;
		} else if (terms == 2) {
			return 1;
		} else if (terms == 3) {
			return 1;
		} else {
			if (table[terms - 1] == -1) {
				return _computeFibonacciSeriesWithMemoization(terms - 1, table) + _computeFibonacciSeriesWithMemoization(terms - 2, table);
			}
			return table[terms];
		}
	}

}
