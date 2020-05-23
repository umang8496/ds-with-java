package strings;

public class MinimumDeletionRequiredForConvertingStringIntoPalindrome {
	
	public static final String string_1 = "ACBCDBAA"; // ABCBA  (3 deletions)
	public static final String string_2 = "AAAMADAMBBB"; // MADAM (6 deletions)
	
	public static void main(String[] args) {
		convertToPalindrome(string_1);
		System.out.println();
		convertToPalindrome(string_2);
	}

	public static void convertToPalindrome(String str) {
		int length_of_str = str.length();
		int firstIndex = 0;
		int lastIndex = length_of_str - 1;

		int deletions = _numberOfDeletions(str, firstIndex, lastIndex);
		System.out.println("Minimum number of deletions required for " + str + "to be a palindrome: " + deletions);
	}

	private static int _numberOfDeletions(String str, int firstIndex, int lastIndex) {
		// base condition
		if(firstIndex >= lastIndex) {
			return 0;
		}

		// if last character of the given string is same as the first one, then recur
		if (str.charAt(firstIndex) == str.charAt(lastIndex)) {
			return _numberOfDeletions(str, firstIndex + 1, lastIndex - 1);
		}
		
		// else if last character of string is different than the first one
		// then, do the following:
		// 1. remove the last  character and recur for the remaining substring
		// 2. remove the first character and recur for the remaining substring
		// then at last,
		// return 1 (for remove operation) + minimum of the two values
		
		return 1 + (Math.min(_numberOfDeletions(str, firstIndex, lastIndex - 1), _numberOfDeletions(str, firstIndex + 1, lastIndex)));		
	}
	
}
