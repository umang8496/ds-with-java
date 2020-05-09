package ds.dp;

/**
 * Ugly numbers are those numbers whose only prime factors are 2, 3 or 5.
 * The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers.
 * By convention, 1 is included.
 * 
 */
public class UglyNumbers {

	public static boolean isUglyNumber(int num) {
		if (num <= 0) {
			System.out.println("Number should be positive");
			System.exit(-1);
		}

		if (num == 1) {
			return true;
		}

		while (num != 1) {
			if (num % 5 == 0) {
				num = num / 5;
			} else if (num % 3 == 0) {
				num = num / 3;
			} else if (num % 2 == 0) {
				num = num / 2;
			} else {
				return false;
			}
		}
		return true;
	}

	public static int getNthUglyNumber(int nth) {
		if (nth == 1) {
			return 1;
		}

		int count = 1;
		int number = 1;
		while (count != nth) {
			++number;
			if (isUglyNumber(number)) {
				count++;
			}
		}
		return number;
	}

}
