package ds.string;

import java.util.Arrays;

public class BruteForcePatternMatching {
	public static final String text = "trailtraintrailtrain";
	public static final String pattern = "train";
	
	public static void main(String[] args) {
		System.out.println("Text: " + text);
		System.out.println("Pattern: " + pattern);
		System.out.println("findFirstMatch(): " + findFirstMatch(text, pattern));
		System.out.println("findEveryMatch(): " + Arrays.toString(findEveryMatch(text, pattern)));
	}
	
	/**
	 * This method searches for the pattern into a String and returns the index of the first char that matches.
     * If no match is found -1 is returned
     * 
     * For example
     * text = "abcdef"
     * pattern = "def"
     * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static int findFirstMatch(String text, String pattern) {
		int text_length = text.length();
		int pattern_length = pattern.length();
		int f = 0; // for text
		int p = 0; // for pattern

		for (f = 0; f < text_length; f++) {
			for (p = 0; p < pattern_length; p++) {
				if (text.charAt(f + p) != pattern.charAt(p)) {
					break;
				}
				if (p == pattern_length - 1) {
					return f;
				}
			}
		}

		return -1;
	}
	
	/**
	 * This method searches for the pattern into a String and returns an array of indexes of every pattern that matches.
     * If no match is found <null> is returned
     * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static int[] findEveryMatch(String text, String pattern) {
		int text_length = text.length();
		int pattern_length = pattern.length();
		int[] found = new int[text_length];
		Arrays.fill(found, -1);	// fill all the elements with -1
		int index = 0;

		int f = 0; // for text
		int p = 0; // for pattern

		for (f = 0; f < text_length; f++) {
			for (p = 0; p < pattern_length; p++) {
				if (text.charAt(f + p) != pattern.charAt(p)) {
					break;
				}
				if (p == pattern_length - 1) {
					found[index++] = f;
				}
			}
		}

		return found;
	}
}
