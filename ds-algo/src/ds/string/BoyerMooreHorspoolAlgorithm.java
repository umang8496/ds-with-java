package ds.string;

import java.util.Arrays;

/**
 * Boyer Moore Horspool
 * This implementation is the simplified version of the Boyer Moore Algorithms.
 * It only implements the Bad Character Table.
 */
public class BoyerMooreHorspoolAlgorithm {
    private static final int ASCII_TABLE_SIZE = 256;

	public static int search(String text, String pattern) {
		if (pattern == null || pattern.length() == 0) {
			return 0;
		}
		
		if (text == null) {
			return -1;
		}
		
		int[] table = preprocessTable(pattern);

		for (int i = (pattern.length() - 1); i < text.length();) {
			for (int j = (pattern.length() - 1); pattern.charAt(j) == text.charAt(i); i--, j--) {
				if (j == 0) {
					return i;
				}
			}
			i += table[text.charAt(i)];
		}
		return -1;
	}

    /**
     * This method creates  the bad character table.
     * It will add pattern.length to every character that is not in the pattern.
     * It will also add pattern.length to the last character of the patter in case it is unique.
     *
     * tes* as in test
     * 1214
     *
     * abc* as in abc
     * 2133
     * 
     * toh* as in tooth
     * 1255
     * 
     * max(1, p.length - index - 1)
     * @param pattern
     * @return
     */
	public static int[] preprocessTable(String pattern) {
		int[] table = new int[ASCII_TABLE_SIZE];
		Arrays.fill(table, pattern.length());

		for (int t = 0; t < pattern.length() - 1; t++) {
			table[pattern.charAt(t)] = Math.max(1, pattern.length() - t - 1);
		}

		// the last character of the pattern must always be max(1, p.length - index - 1)
		if (table[pattern.charAt(pattern.length() - 1)] < pattern.length()) {
			table[pattern.charAt(pattern.length() - 1)] = 1;
			// table[pattern.charAt(pattern.length() - 1)] = Math.max(1, pattern.length() - pattern.length() - 1);;
		}
		
		return table;
	}

}
