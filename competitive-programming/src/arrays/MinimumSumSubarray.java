package arrays;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the smallest sum and return its sum.
 * 
 * For Example
 * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Output: -5
 * Explanation: [-5] has the smallest sum = -5.
 * 
 */
public class MinimumSumSubarray {

	public static int minSumForSubarray(int[] array) {
		if (array == null || array.length == 0) {
			return Integer.MAX_VALUE;
		} else if (array.length == 1) {
			return array[0];
		} else {
			int size = array.length;
			int min_so_far = Integer.MAX_VALUE;
			int min_ending_here = 0;
	
			for (int f = 0; f < size; ++f) {
				min_ending_here = min_ending_here + array[f];
				if (min_so_far > min_ending_here) {
					min_so_far = min_ending_here;
				}
				if (min_ending_here > 0) {
					min_ending_here = 0;
				}
			}
			return min_so_far;
		}
	}
	
}

