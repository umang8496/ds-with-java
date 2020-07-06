package arrays;

import java.util.Arrays;

/**
 * Given an array of integers in which all the elements are appear thrice but one which appears only one.
 * 
 * int [] arrA = { 6, 5, 3, 2, 4, 2, 5, 6, 3, 3, 6, 5, 2 };
 * Output: 4
 *
 */
public class FindSingleElementOutOfSetsOfThreeElements {

	public static void find(int[] arrA) {
		int singleElement = 0;
		
		// this is for calculating for every position in 32 bit integer
		for (int i = 0; i < 32; i++) {
			int y = (1 << i);
			int tempSum = 0;
			
			for (int j = 0; j < arrA.length; j++) {
				// if that particular bit is set for the ith position, add 1 to sum (w.r.t that bit) 
				if ((arrA[j] & y) >= 1)
					tempSum = tempSum + 1;
			}
			
			// if bits are not multiple of 3 then that bit belongs to the element appearing single time
			if ((tempSum % 3) == 1) {
				singleElement = singleElement | y;
			}
		}
		System.out.println("Element appearing once is: " + singleElement);
	}
	
	public static void main(String[] args) {
		// int[] arrA = { 1, 4, 5, 6, 1, 4, 6, 1, 4, 6 };
		// int[] arrA = { 6, 5, 3, 2, 4, 2, 5, 6, 3, 3, 6, 5, 2 };
		int[] arrA = { 6, 6, 6, 4 };
		System.out.println(Arrays.toString(arrA));
		find(arrA);
	}
}
