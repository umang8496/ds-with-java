package ds.string;

import java.util.Arrays;

public class ZAlgorithm {
	private int[] zArray = null;
	private final char SEPARATOR = '$';

	public String findLongestPrefixSuffixString(String text) {
		String longestSubString = null;
		if (text != null) {
			zArray = _computeZArray(text);
			longestSubString = _processZArray(text, zArray);
		}
		return longestSubString;
	}

	private int[] _computeZArray(String text) {
		int textLength = text.length();
		int[] zArray = new int[textLength];
		Arrays.fill(zArray, 0);
		int zeroth = 0;

		for (int f = 1; f < textLength; f++) {
			if (text.charAt(zeroth) == text.charAt(f)) {
				int matched_val = 1;
				int w = 1;

				while (((f + w) < textLength) && (text.charAt(zeroth + w) == text.charAt(f + w))) {
					matched_val++;
					w++;
				}

				zArray[f] = matched_val;
			} else {
				zArray[f] = 0;
			}
		}
		return zArray;
	}

	private String _processZArray(String text, int[] zArray) {
		if (zArray != null && text != null) {
			// System.out.println(Arrays.toString(zArray));
			int max = zArray[0];
			int index = -1;
			for (int f = 1; f < zArray.length; f++) {
				int num = zArray[f];
				if (num > max) {
					max = num;
					index = f;
				}
			}
			return _getTheString(text, index, max);
		} else {
			return null;
		}
	}

	private String _getTheString(String text, int index, int max) {
		if (index > -1) {
			// System.out.println("index: " + index + " max: " + max);
			return text.substring(index, index + max);
		} else {
			return null;
		}
	}

	public int search(String text, String pattern) {
		if (text != null && pattern != null) {
			String longString = _createLongString(text, pattern);
			int[] z = _computeZArray(longString);
			// int[] z = _createZTable(longString);

			int i = pattern.length() + 1;
			while (i < z.length) {
				if (z[i] == pattern.length()) {
					return (i - pattern.length() - 1);
				}
				i++;
			}
		}
		return -1;
	}

	public int[] searchAll(String text, String pattern) {
		if (text != null && pattern != null) {
			String longString = _createLongString(text, pattern);
			int[] result = new int[text.length()];
			int[] z = _computeZArray(longString);
			int i = pattern.length() + 1;

			while (i < z.length) {
				if (z[i] == pattern.length())
					result[i - pattern.length() - 1] = z[i];
				i++;
			}
			return result;
		}
		return null;
	}

	private String _createLongString(String text, String pattern) {
		char[] charArray = new char[pattern.length() + text.length() + 1];

		for (int i = 0; i < pattern.length(); i++) {
			charArray[i] = pattern.charAt(i);
		}

		charArray[pattern.length()] = SEPARATOR;

		for (int i = 0; i < text.length(); i++) {
			charArray[pattern.length() + 1 + i] = text.charAt(i);
		}

		// System.out.println(String.valueOf(charArray));
		return String.valueOf(charArray);
	}

	private int[] _createZTable(String text) {
		int[] z = new int[text.length()];
		int left = 0;
		int right = 0;
		for (int i = 1; i < text.length(); i++) {
			if (i > right) {
				left = right = i;
				while (right < text.length() && text.charAt(right - left) == text.charAt(right))
					right++;
				z[i] = right - left;
				right--;
			} else {
				int k = i - left;
				if (z[k] < right - i + 1)
					z[i] = z[k];
				else {
					left = i;
					while (right < text.length() && text.charAt(right - left) == text.charAt(right))
						right++;
					z[i] = right - left;
					right--;
				}
			}
		}
		return z;
	}
}
