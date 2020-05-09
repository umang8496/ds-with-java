package ds.misc;

import java.util.Arrays;

public class EveryLexicographicalPermutation {
	public static String result = null;
	
	public static void main(String[] args) {
		// String str_1 = "BA"; // AA AB BA BB
		
		// String str_1 = "BAC";// AAA AAB AAC ABA ABB ABC ACA ACB ACC BAA BAB BAC
								// BBA BBB BBC BCA BCB BCC CAA CAB CAC CBA CBB
								// CBC CCA CCB CCC
		
		String str_1 = "BAB"; 	// AAA AAB AAB ABA ABB ABB ABA ABB ABB BAA BAB BAB
								// BBA BBB BBB BBA BBB BBB BAA BAB BAB BBA BBB
								// BBB BBA BBB BBB  
		System.out.println("Printing Every Lexicographical Permutation of " + str_1);

		try {
			char[] ch = str_1.toCharArray();
			Arrays.sort(ch);
			str_1 = String.valueOf(ch);
			result = _initWithEmptyString();
			_everyLexicographicalPermutation(str_1, result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("THE END");
	}
	
	private static String _initWithEmptyString() {
		return "";
	}
	
	private static void _everyLexicographicalPermutation(String string, String result) {
		if (string != null && result != null) {
			// base condition
			if (result.length() == string.length()) {
				System.out.print(result + "  ");
				return;
			}

			for (int f = 0; f < string.length(); f++) {
				_everyLexicographicalPermutation(string, result + string.charAt(f));
			}
		} else {
			System.out.println("Parameters for _everyLexicographicalPermutation are null");
		}
	}
	
}
