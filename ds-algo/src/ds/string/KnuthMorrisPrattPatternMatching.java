package ds.string;

public class KnuthMorrisPrattPatternMatching {
	public static final String text = "abazacabababac";
	public static final String pattern = "ababac";
	
	public static void main(String[] args) {
		System.out.println("Text: " + text);
		System.out.println("Pattern: " + pattern);
		System.out.println("First match is at index: " + findFirstMatchUsingKMP(text, pattern));
	}
	
	/**
	 * This method returns the index for the first match of the pattern into the index.
     * It will return -1 in case it does not match.
	 * 
	 * <pre>
     *                 i
     * 0 1 2 3 4 5 6 7 8 9 10111213
     * a b a z a c a b a b a b a c
     *
     *                 a b a b a c
     *                 0 0 1 2 3 0
     *                           j
     * this example returns index of 8
     * </pre>
     * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static int findFirstMatchUsingKMP(String text, String pattern) {
		int text_length = text.length();
		int pattern_length = pattern.length();
		int[] lsp = _computeLSPTable(pattern);
		int i = 0;

		for (int f = 0; f < text_length; f++) {
			while (i > 0 && text.charAt(f) != pattern.charAt(i)) {
				i = lsp[i - 1];
			}
			if (text.charAt(f) == pattern.charAt(i)) {
				i++;
				if (i == pattern_length) {
					return (f - (i - 1));
				}
			}
		}

		return -1;
	}

	/**
	 * This computes the table of the longest suffix and longest preffix on the pattern.
     * This is to be used by Knuth-Morris-Pratt main method of the algorithm.
     * 
	 * @param pattern
	 * @return
	 */
	private static int[] _computeLSPTable(String pattern) {
		int pattern_length = pattern.length();
		int[] lsp = new int[pattern_length];
		int i = 0;

		for (int f = 1; f < pattern_length; f++) {
			while (i > 0 && pattern.charAt(f) != pattern.charAt(i)) {
				i = lsp[i - 1];
			}
			if (pattern.charAt(f) == pattern.charAt(i)) {
				lsp[f] = i + 1;
				i++;
			} else {
				lsp[f] = 0;
			}
		}

		return lsp;
	}
}
