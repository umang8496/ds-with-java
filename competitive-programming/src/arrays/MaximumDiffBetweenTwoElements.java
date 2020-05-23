package arrays;

/**
 * Given an array of integers,
 * Find out the maximum difference between any two elements such that larger element appears after the smaller number.
 *
 * For example,
 * Input : array = {2, 3, 10, 6, 4, 8, 1}
 * Output : 8
 * Explanation : The maximum difference is between 10 and 2.
 * 
 * Input : array = {7, 9, 5, 6, 3, 2}
 * Output : 2
 * Explanation : The maximum difference is between 9 and 7.
 * 
 */
public class MaximumDiffBetweenTwoElements {

	/**
	 * Naive solution
	 * Scan through Left to Right, and for each element compute the differences with every other element
	 * Filter out the maximum difference.
	 * 
	 * @param array
	 * @return maxDiff
	 * 
	 */
	public static int maximumDifferenceNaiveApproach(int[] array) {
		if (array == null) {
			return -1;
		} else if (array.length == 0 || array.length == 1) {
			return 0;
		} else {
			int maxDiff = 0;
			for (int f = 0; f < array.length - 1; ++f) {
				for (int ff = f + 1; ff < array.length; ++ff) {
					if (array[f] < array[ff]) {
						int diff = array[ff] - array[f];
						if (maxDiff < diff) {
							maxDiff = diff;
						}
					}
				}
			}
			return maxDiff;
		}
	}
	
	/**
	 * Efficient solution
	 * In this method, instead of taking difference of the picked element with every other element,
	 * we take the difference with the minimum element found so far.
	 * So we need to keep track of 2 things:
	 * 		Maximum difference found so far (maxDiff)
	 * 		Minimum number visited so far (minimumElement).
	 * 
	 * @param array
	 * @return maxDiff
	 * 
	 */
	public static int maximumDifferenceEfficientApproach(int[] array) {
		if (array == null) {
			return -1;
		} else if (array.length == 0 || array.length == 1) {
			return 0;
		} else {
			int maxDiff = array[1] - array[0];
			int minimumElement = array[0];
			for (int f = 1; f < array.length; ++f) {
				if (array[f] - minimumElement > maxDiff) {
					maxDiff = array[f] - minimumElement;
				}
				if (array[f] < minimumElement) {
					minimumElement = array[f];
				}
			}
			return maxDiff;
		}
	}
	
	/**
	 * Tricky solution with Maximum Sum Subarray
	 * First find the difference between the adjacent elements of the array and store all differences in an auxiliary array diff[] of size n-1.
	 * Now this problems turns into finding the maximum sum subarray of this difference array.
	 * 
	 * @param array
	 * @return maxDiff
	 * 
	 * For Example
	 * array = {80, 2, 6, 3, 100}
	 * diff  = {-78, 4, -3, 97}
	 * Maximum Sum Subarray would be 98 {4, -3 , 97}
	 * 
	 */
	public static int maximumDifferenceUsingMaxSumSubarray(int[] array) {
		if (array == null) {
			return -1;
		} else if (array.length == 0 || array.length == 1) {
			return 0;
		} else {
			int size = array.length;
			int[] diff = new int[size - 1];
			
			for (int f = 0; f < size - 1; f++) {
				diff[f] = array[f + 1] - array[f];
			}
			
			int maxDiff = diff[0];
			
			for (int f = 1; f < size - 1; ++f) {
				if (diff[f - 1] > 0) {
					diff[f] += diff[f - 1];
				}
				if (maxDiff < diff[f]) {
					maxDiff = diff[f];
				}
			}
			return maxDiff;
		}
	}

}

