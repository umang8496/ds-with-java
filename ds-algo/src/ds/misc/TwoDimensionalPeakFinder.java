package ds.misc;

import java.util.Arrays;

// TEST CASE : 01
// [10  21  34  19]
// [12  20  34  19]
// [24  21  30  19]
// [36  25  34  40]
// [[10,21,34,19],[12,20,34,19],[24,21,30,19],[36,25,34,40]]
//OUTPUT : 40

// TEST CASE : 02
// [10   8  10  10]
// [14  13  12  11]
// [15   9  11  21]
// [16  17  19  20]
// [[10,8,10,10],[14,13,12,11],[15,9,11,21],[16,17,19,20]]
// OUTPUT : 21

public class TwoDimensionalPeakFinder {
	public static void main(String[] args) {
		int[][] array = new int[][] { { 10, 8, 10, 10 }, { 14, 13, 12, 11 }, { 15, 9, 11, 21 }, { 16, 17, 19, 20 } };
		// int[][] array = new int[][] { { 10, 21, 34, 19 }, { 12, 20, 34, 19 }, { 24, 21, 30, 19 }, { 36, 25, 34, 40 } };

		int no_of_cols = array.length;
		int mid_col = ((no_of_cols) / 2);

		System.out.println("Two Dinemsional Array");
		System.out.println(Arrays.toString(array[0]));
		System.out.println(Arrays.toString(array[1]));
		System.out.println(Arrays.toString(array[2]));
		System.out.println(Arrays.toString(array[3]));
		System.out.println();

		int peak = twoDimensionalPeakFinder(array, mid_col);
		if (peak != Integer.MIN_VALUE) {
			System.out.println("2D peak is: " + peak);
		}

	}

	public static int twoDimensionalPeakFinder(int[][] array, int mid_col) {
		if (array != null) {
			Object[] ob = findMax(array, mid_col);

			int no_of_cols = array.length;
			int f = (int) ob[0];
			int g = (int) ob[1];
			int maxval = (int) ob[2];

			if (g == no_of_cols - 1) {
				if (maxval >= array[f][g - 1]) {
					return maxval;
				}
			} else if (g == 0) {
				if (maxval >= array[f][g + 1]) {
					return maxval;
				}
			} else {
				if (maxval >= array[f][g + 1] && maxval >= array[f][g - 1]) {
					return maxval;
				}
			}

			if (maxval < array[f][g + 1])
				return twoDimensionalPeakFinder(array, g + 1);
			else
				return twoDimensionalPeakFinder(array, g - 1);
		} else {
			return Integer.MIN_VALUE;
		}

	}

	public static Object[] findMax(int[][] array, int mid_col) {
		int g = mid_col;
		int f = 0;
		int maxval = array[0][g];

		for (f = 0; f < array.length; f++) {
			if (maxval < array[f][g]) {
				maxval = array[f][g];
			}
		}
		return new Object[] { f - 1, g, maxval };
	}
}
