package ds.misc;

import java.util.Arrays;

/**
 * Find the first occurrence of subarray whose sum is equal to the given value.
 * Sliding Window Technique 
 * 
 */
public class SubarrayWithGivenSum {
	public static int[] array = null;
	
	public static void main(String[] args) {
		array = new int[] { 4, 8, 2, 0, 3, 7, 4, 1, 9, 6 };
		int sum = 20;
		Object[] indexes = findSubArray(array, sum);
		try {
			int index_i = (int) indexes[0];
			int index_j = (int) indexes[1];
			if (index_i != -1 && index_j != -1) {
				System.out.println("Required Sub Array is found from index: " + index_i + " to index: " + index_j);
			} else {
				System.out.println("No Required Sub Array is found.");
			}
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Finds the array indexes for the consecutive numbers which add up to the given sum.
	 * 
	 * @param array
	 * @param sum
	 * @return
	 */
	public static Object[] findSubArray(int[] array, int sum) {
		int[] window = new int[array.length];
		int w = 0;
		Arrays.fill(window, 0);

		for (int f = 0; f < array.length; f++) {
			window[f] += array[f];
			if (_sumOfArray(window) < sum) {
				continue;
			} else if (_sumOfArray(window) == sum) {
				return new Object[] { w, f };
			} else {
				window[w] = 0;
				w++;
				if (_sumOfArray(window) == sum) {
					return new Object[] { w, f };
				}
			}
		}

		return new Object[] { -1, -1 };
	}
	
	private static int _sumOfArray(int[] array) {
		int sum = 0;
		for (int num : array) {
			sum = sum + num;
		}
		return sum;
	}
	
}
