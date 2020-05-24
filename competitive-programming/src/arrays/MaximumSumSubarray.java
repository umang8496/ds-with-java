package arrays;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * 
 * For Example
 * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Output: 6
 * Explanation: [4, -1, 2, 1] has the largest sum = 6.
 * 
 */
public class MaximumSumSubarray {

	public static int maxSumForSubarray(int[] array) {
		if (array == null || array.length == 0) {
			return Integer.MIN_VALUE;
		} else if (array.length == 1) {
			return array[0];
		} else {
			int size = array.length;
			int max_so_far = Integer.MIN_VALUE;
			int max_ending_here = 0;
	
			for (int f = 0; f < size; ++f) {
				max_ending_here = max_ending_here + array[f];
				if (max_so_far < max_ending_here) {
					max_so_far = max_ending_here;
				}
				if (max_ending_here < 0) {
					max_ending_here = 0;
				}
			}
			return max_so_far;
		}
	}

}

