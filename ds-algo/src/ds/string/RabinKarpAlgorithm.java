package ds.string;

/**
 * Rabin-Karp algorithm is an algorithm used for searching/matching patterns in the text using a hash function.
 * A hash function is a tool to map a larger input value to a smaller output value.
 * This output value is called the hash value.
 *
 * When the hash value of the pattern matches with the hash value of a window of the text but the window is not the actual pattern then it is called a spurious hit.
 * Spurious hit increases the time complexity of the algorithm.
 * In order to minimize spurious hit, we use modulus. It greatly reduces the spurious hit.
 * 
 */
public class RabinKarpAlgorithm {
	private final static int PRIME = 7;
	private static String text = null;
	private static String pattern = null;
	
	public static void main(String[] args) {
		text = "ABCCDDAEFG";
		pattern = "CDD";

		int index = searchForPattern(text, pattern);
		if (index != -1) {
			System.out.println("Match found at index: " + index);
		} else {
			System.out.println("Match not found.");
		}
	}

	public static int searchForPattern(String text, String pattern) {
		int retval = -1;
		int textLength = text.length();
		int patternLength = pattern.length();

		if (textLength >= patternLength) {
			int lastChar = (textLength - patternLength);
			int patternHash = _calculateTheHashValueFor(pattern, patternLength);
			int stringHash = _calculateTheHashValueFor(text, patternLength);

			for (int f = 0; f <= lastChar; f++) {
				if (patternHash == stringHash) {
					if (_match(text, pattern, f)) {
						return f;
					}
				} else {
					if ((f + patternLength + 1) > textLength) {
						return -1;
					} else {
						stringHash = _calculateTheHashValueFor(text.substring(f + 1, f + patternLength + 1), patternLength);
					}
				}
			}
		}

		return retval;
	}

	private static int _calculateTheHashValueFor(String str, int size) {
		int hash = -1;
		int ascii = -1;

		if (str != null) {
			for (int f = 0; f < size; f++) {
				ascii = _getAsciiValueFor(str.charAt(f));
				hash = (int) (hash + ascii * Math.pow(PRIME, f));
			}
		}

		return hash;
	}

	private static int _getAsciiValueFor(char ch) {
		if (ch != '\0') {
			return Character.getNumericValue(ch);
		} else {
			return -1;
		}
	}

	private static boolean _match(String text, String pattern, int i) {
		for (int f = 0; f < pattern.length(); f++) {
			if (text.charAt(i) != pattern.charAt(f)) {
				return false;
			} else {
				i++;
			}
		}
		return true;
	}
	
}
