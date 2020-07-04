package miscellaneous;

/**
 * Count the number of 1s in the binary representation of any non-negative integer.
 * 
 * For Example
 * num = 6     #1s: 2
 * num = 9     #1s: 2
 * num = 0     #1s: 0
 * num = 253   #1s: 8
 * num = 1023  #1s: 10
 * 
 */
public class CountNumberOfOnes {

	public static int numberOfOnesByBruteForce(int num) {
		if (num == 0) {
			return 0;
		}

		int count = 0;
		while (num > 0) {
			count = count + (num & 1);
			num = (num >> 1);
		}

		return count;
	}

	public static int numberOfOnesByBrianKernighan(int num) {
		if (num == 0) {
			return 0;
		}

		int count = 0;
		while (num > 0) {
			num = num & (num - 1);
			count += 1;
		}

		return count;
	}
}
