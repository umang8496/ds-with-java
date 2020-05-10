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
			return 1;
		if (num == 3)
			return 2;

		int subtract1 = waysToGetNumber(num - 1); // if we subtract 1, we will be left with 'num-1'
		int subtract3 = waysToGetNumber(num - 3); // if we subtract 3, we will be left with 'num-3'
		int subtract4 = waysToGetNumber(num - 4); // if we subtract 4, we will be left with 'num-4'

		return subtract1 + subtract3 + subtract4;
	}
	
	public static int waysToGetNumber_BottomUp(int num) {
		int dp[] = new int[num + 1];
		if (num == 0) {
			dp[0] = 1;
		} else if (num == 1) {
			dp[0] = dp[1] = 1;
		} else if (num == 2) {
			dp[0] = dp[1] = dp[2] = 1;
		} else if (num == 3) {
			dp[0] = dp[1] = dp[2] = 1;
			dp[3] = 2;
		} else {
			dp[0] = dp[1] = dp[2] = 1;
			dp[3] = 2;
			
			for (int i = 4; i <= num; i++) {
				dp[i] = dp[i - 1] + dp[i - 3] + dp[i - 4];
			}
		}

		return dp[num];
	}
	
	public static int waysToGetNumber_TopDown(int num) {
		int dp[] = new int[num + 1];
		return _waysToGetNumber(dp, num);
	}
	
	private static int _waysToGetNumber(int[] dp, int num) {
		if ((num == 0) || (num == 1) || (num == 2))
			return 1;
		if (num == 3)
			return 2;

		if (dp[num] == 0) {
			int subtract1 = _waysToGetNumber(dp, num - 1); // if we subtract 1, we will be left with 'num-1'
			int subtract3 = _waysToGetNumber(dp, num - 3); // if we subtract 3, we will be left with 'num-3'
			int subtract4 = _waysToGetNumber(dp, num - 4); // if we subtract 4, we will be left with 'num-4'

			dp[num] = subtract1 + subtract3 + subtract4;
		}

		return dp[num];
	}
}
