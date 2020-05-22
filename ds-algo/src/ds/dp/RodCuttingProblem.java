package ds.dp;

/**
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 *
 * For example,
 * If length of the rod is 8 units and the values of different pieces are given as following,
 * then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)
 * 
 * index    | 0   1   2   3   4   5   6   7  
 * ------------------------------------------
 * length   | 1   2   3   4   5   6   7   8  
 * ------------------------------------------
 * price    | 1   5   8   9   10  17  17  20
 * 
 */
public class RodCuttingProblem {

	public static int maximizeValueForRod(int[] array, int size) {
		if (array == null || size < 0) {
			return -1;
		} else if (size == 0) {
			return 0;
		} else {
			int maxVal = Integer.MIN_VALUE;
			int temp = 0;
			// Recursively cut the rod in different pieces and compare different configurations
			for (int f = 0; f < size; f++) {
				temp = array[size - f - 1] + maximizeValueForRod(array, f);
				if (temp > maxVal) {
					maxVal = temp;
				}
			}
			return maxVal;
		}
	}
	
	public static int maximizeValueForRodUsingDP(int[] array, int size) {
		if (array == null || size < 0) {
			return -1;
		} else if (size == 0) {
			return 0;
		} else {
			int[] ARR = new int[size + 1];
			ARR[0] = 0;

			for (int f = 1; f <= size; f++) {
				int maxVal = -1;
				for (int ff = 1; ff <= f; ff++) {
					int temp = array[ff - 1] + ARR[f - ff];
					if (temp > maxVal) {
						maxVal = temp;
					}
				}
				ARR[f] = maxVal;
			}
			return ARR[size];
		}
	}
	
}
