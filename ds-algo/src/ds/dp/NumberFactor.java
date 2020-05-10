package ds.dp;

/**
 * Given a number N, count the number of ways to express N as the sum of numbers 1, 3 and 4.
 *
 * For Example:
 * N = 4
 * {1,1,1,1}, {1,3}, {3,1}, {4}
 * count = 4
 * 
 */
public class NumberFactor {

	public static int waysToGetNumber(int num) {
		if ((num == 0) || (num == 1) || (num == 2))
			return 1; //
		if (num == 3)
			return 2;

		int subtract1 = waysToGetNumber(num - 1); // if we subtract 1, we will be left with 'n-1'
		int subtract3 = waysToGetNumber(num - 3); // if we subtract 3, we will be left with 'n-3'
		int subtract4 = waysToGetNumber(num - 4); // if we subtract 4, we will be left with 'n-4'

		return subtract1 + subtract3 + subtract4;
	}
	
}
