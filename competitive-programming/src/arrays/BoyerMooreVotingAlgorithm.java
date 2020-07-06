package arrays;

/**
 * Given an array of integer write an algorithm to find the majority element in it (if exist).
 * Majority Element: If an element appears more than n/2 times in array where n is the size of the array.
 * 
 * In its simplest form, the algorithm finds a majority element, if there is one: that is, 
 * an element that occurs repeatedly for more than half of the elements of the input.
 * However, if there is no majority, the algorithm will not detect that fact, and will still output one of the elements.
 * A version of the algorithm that makes a second pass through the data can be used to verify that the element found in the first pass really is a majority. 
 * 
 * As per above algorithm we can divide out implementation into two parts
 * First iteration – Find the element which could be a majority element.
 * Second iteration – check the element(found in first iteration) count is greater than n/2
 * 
 */
public class BoyerMooreVotingAlgorithm {
	public static void main(String[] args) {
		int[] arrA = { 1, 3, 5, 5, 5, 5, 4, 1, 5 };
		find(arrA);
	}

	public static void find(int[] arrA) {
		if (arrA.length == 0)
			return;

		int size = arrA.length;
		int majorityElement = arrA[0];
		int count = 1;
		
		for (int i = 1; i < size; i++) {
			if (majorityElement == arrA[i]) {
				count++;
			} else if (count == 0) {
				majorityElement = arrA[i];
				count = 1;
			} else {
				count--;
			}
		}
		
		// check if majorityElement is appearing more than n/2 times
		count = 0;
		for (int i = 0; i < size; i++) {
			if (arrA[i] == majorityElement) {
				count++;
			}
		}
		if (count > size / 2) {
			System.out.println("(Boyer_Moore)Element appearing more than n/2 times: " + majorityElement);
		} else {
			System.out.println("No element appearing more than n/2 times");
		}
	}
}
