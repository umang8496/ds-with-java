package ds.dp;

/**
 * Imagine you are a thief trying to steal as much gold from a neighborhood as possible.
 * This neighborhood consists of a single row of houses.
 * You know how much gold is in each house, so you’re able to plan out which houses you want to steal from ahead of time.
 * But, to avoid triggering any alarms, you set the constraint that won’t steal from any two houses that are right next to each other.
 * Meaning you’ll always leave at least one house between any two that you rob.
 *
 */
public class HouseThiefProblem {
	public static int rob(int[] nums) {
		if (nums == null) {
			return -1;
		}

		if (nums.length == 0) {
			return 0;
		}

		if (nums.length == 1) {
			return nums[0];
		}

		int[] dp = new int[nums.length];

		dp[0] = nums[0];
		dp[1] = nums[0] > nums[1] ? nums[0] : nums[1];

		for (int i = 2; i < nums.length; i++) {
			dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
		}

		return dp[dp.length - 1];
	}
}
