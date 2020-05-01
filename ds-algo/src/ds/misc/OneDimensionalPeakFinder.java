package ds.misc;

import java.util.ArrayList;
import java.util.Arrays;

// example(1)
// arr1=[61,62,24,28,32,14,36,61,6,12]
// peaks=[62,32,61,12]
// numberofpeaks=4

// example(2)
// arr2=[10,20,15,2,23,90,67]
// peaks=[20,90]
// numberofpeaks=2

public class OneDimensionalPeakFinder {
	public static void main(String[] args) {
		int[] array = new int[] { 61, 62, 24, 28, 32, 14, 36, 61, 6, 12 };
		oneDimensionalPeakFinder(array);
	}

	public static void oneDimensionalPeakFinder(int[] array) {
		if (array != null) {
			System.out.println("Given array is: " + Arrays.toString(array));
			int lengthOfArray = array.length;
			ArrayList<Integer> peaks = new ArrayList<>();

			if (array[0] > array[1]) {
				peaks.add(array[0]);
			}

			for (int f = 1; f < lengthOfArray - 1; f++) {
				if (array[f] > array[f + 1] && array[f] > array[f - 1]) {
					peaks.add(array[f]);
				}
			}

			if (array[lengthOfArray - 1] > array[lengthOfArray - 2]) {
				peaks.add(array[lengthOfArray - 1]);
			}

			System.out.println("Peaks in the given array:" + Arrays.toString(peaks.toArray()));
			System.out.println("#peaks in the given array:" + peaks.size());
		}
	}
}
