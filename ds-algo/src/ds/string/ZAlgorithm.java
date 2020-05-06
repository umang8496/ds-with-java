package ds.string;

import java.util.Arrays;

public class ZAlgorithm {
	private int[] zArray = null;

	public String findLongestSubString(String text) {
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

}
