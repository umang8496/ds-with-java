package dynamicProgramming;

/**
 * There are n stairs, a person standing at the bottom wants to reach the top.
 * The person can climb either 1 stair or 2 stairs at a time.
 * Count the number of ways, the person can reach the top.
 *
 */
public class StairsClimbingProblem {

	public static int numberOfWaysToClimb(int stairs) {
		if (stairs == 1) {
			return 1;		// {1}
		} else if (stairs == 2) {
			return 2;		// {1,1} and {2}
		} else {
			return (numberOfWaysToClimb(stairs-1) + numberOfWaysToClimb(stairs-2)); 
		}
	}
	
	public static int numberOfWaysToClimbUsingDP(int stairs) {
		if (stairs <= 0) {
			return -1;
		}else {
			if (stairs == 1) {
				return 1;		// {1}
			} else if (stairs == 2) {
				return 2;		// {1,1} and {2}
			} else {
				int[] dp = new int[stairs+1];
				dp[1] = 1;
				dp[2] = 2;
				for(int f = 3; f <= stairs; f++) {
					dp[f] = dp[f - 1] + dp[f - 2];
				}
				return dp[stairs];
			}
		}
	}
	
}
