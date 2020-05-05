package ds.string;

import java.util.Arrays;

/**
 * Boyer Moore Algorithm
 * This algorithm will search for a pattern into an array of characters and return the index of the match or -1 in case of no match.
 * This algorithm uses two tables that helps to improve the time complexity and return the search value in less comparisons than the Brute Force Algorithm.
 * 
 */
public class BoyerMooreAlgorithm {
	private static final int ASCII_TABLE_SIZE = 256;

    /**
     * This method will use the tables of Good Suffix and BAd Character to decide how many comparison are possible to skip.
     * Which one that computes ore comparisons the search will use to optimize the search
     *
     * abcdbabaibai
     *    baibai
     *
     * @param array
     * @param pattern
     * @return
     */
	public static int search(String text, String pattern) {
		if (pattern == null || pattern.length() == 0)
			return 0;
		
		if (text == null)
			return -1;

		int[] badCharTable = precomputeBadCharTable(pattern);
		int[]  suffixTable = preprocessSuffixTable(pattern);
		
		for (int i = pattern.length() - 1, j; i < text.length();) {
			for (j = pattern.length() - 1; pattern.charAt(j) == text.charAt(i); --i, --j) {
				if (j == 0) {
					return i;
				}
			}
			i += Math.max(suffixTable[pattern.length() - 1 - j], badCharTable[text.charAt(i)]);
		}
		return -1;
	}
	
	/**
    *
    * This method pre-processes the Good Suffix Table
    *
    * abcdbabaibai
    * baibai
    * 1st round 6,7,8,9,10,11
    *
    * @param pattern
    * @return
    */
	public static int[] preprocessSuffixTable(String pattern) {
		int[] table = new int[pattern.length()];
		computePrefix(pattern, table);
		computeSuffix(pattern, table);
		return table;
	}

   /**
    * This method creates  the bad character table.
    * It will add pattern.length to every character that is not in the pattern.
    * It will also add pattern.length to the last character of the patter in case it is unique.
    *
    * test as in test
    * 121
    *
    * ab* as in abc
    * 213
    * max(1, p.length - index - 1)
    * @param pattern
    * @return
    */
	public static int[] precomputeBadCharTable(String pattern) {
		int[] table = new int[ASCII_TABLE_SIZE];
		Arrays.fill(table, pattern.length());

		for (int t = 0; t < pattern.length() - 1; t++) {
			table[pattern.charAt(t)] = Math.max(1, pattern.length() - t - 1);
		}

		if (table[pattern.charAt(pattern.length() - 1)] < pattern.length()) {
			table[pattern.charAt(pattern.length() - 1)] = 1;
		}
		return table;
	}

	public static void computePrefix(String pattern, int[] table) {
		int lastPrefixPosition = pattern.length();
		for (int i = pattern.length(); i > 0; --i) {
			if (isPrefix(pattern, i))
				lastPrefixPosition = i;
			table[pattern.length() - i] = lastPrefixPosition - i + pattern.length();
		}
	}

	public static void computeSuffix(String pattern, int[] table) {
		for (int i = 0; i < pattern.length() - 1; ++i) {
			int len = suffixLength(pattern, i);
			table[len] = pattern.length() - 1 - i + len;
		}
	}

   /**
    * baibai
    *   j  i
    * @param pattern
    * @param index
    * @return
    */
	public static boolean isPrefix(String pattern, int index) {
		for (int i = index, j = 0; i < pattern.length(); ++i, ++j) {
			if (pattern.charAt(i) != pattern.charAt(j))
				return false;
		}
		return true;
	}

   /**
    * baidai
    * i    j
    *
    * @param pattern
    * @param index
    * @return
    */
	public static int suffixLength(String pattern, int index) {
		int len = 0;
		int j = pattern.length() - 1;
		for (int i = index; i >= 0 && pattern.charAt(i) == pattern.charAt(j); --i, --j) {
			len++;
		}
		return len;
	}
   
}
