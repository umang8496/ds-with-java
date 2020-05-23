package strings;

import java.util.Arrays;

public class EveryLexicographicalPermutation {
	public static String result = null;
	
	public static void main(String[] args) {
		// String str_1 = "BA";		// AA AB BA BB
		
		// String str_1 = "BAC";	// AAA AAB AAC ABA ABB ABC ACA ACB ACC BAA BAB BAC
									// BBA BBB BBC BCA BCB BCC CAA CAB CAC CBA CBB
									// CBC CCA CCB CCC
		
		// String str_1 = "BAB";	// AAA AAB AAB ABA ABB ABB ABA ABB ABB BAA BAB BAB
									// BBA BBB BBB BBA BBB BBB BAA BAB BAB BBA BBB
									// BBB BBA BBB BBB
		
		// String str_1 = "BAB";	// AAA AAB ABA ABB BAA BAB BBA BBB
		
		String str_1 = "DCBA";		// AAAA AAAB AAAC AAAD AABA AABB AABC AABD AACA
									// AACB AACC AACD AADA AADB AADC AADD ABAA ABAB
									// ABAC ABAD ABBA ABBB ABBC ABBD ABCA ABCB ABCC
									// ABCD ABDA ABDB ABDC ABDD ACAA ACAB ACAC ACAD
									// ACBA ACBB ACBC ACBD ACCA ACCB ACCC ACCD ACDA
									// ACDB ACDC ACDD ADAA ADAB ADAC ADAD ADBA ADBB
									// ADBC ADBD ADCA ADCB ADCC ADCD ADDA ADDB ADDC
									// ADDD BAAA BAAB BAAC BAAD BABA BABB BABC BABD
									// BACA BACB BACC BACD BADA BADB BADC BADD BBAA
									// BBAB BBAC BBAD BBBA BBBB BBBC BBBD BBCA BBCB
									// BBCC BBCD BBDA BBDB BBDC BBDD BCAA BCAB BCAC
									// BCAD BCBA BCBB BCBC BCBD BCCA BCCB BCCC BCCD
									// BCDA BCDB BCDC BCDD BDAA BDAB BDAC BDAD BDBA
									// BDBB BDBC BDBD BDCA BDCB BDCC BDCD BDDA BDDB
									// BDDC BDDD CAAA CAAB CAAC CAAD CABA CABB CABC
									// CABD CACA CACB CACC CACD CADA CADB CADC CADD
									// CBAA CBAB CBAC CBAD CBBA CBBB CBBC CBBD CBCA
									// CBCB CBCC CBCD CBDA CBDB CBDC CBDD CCAA CCAB
									// CCAC CCAD CCBA CCBB CCBC CCBD CCCA CCCB CCCC
									// CCCD CCDA CCDB CCDC CCDD CDAA CDAB CDAC CDAD
									// CDBA CDBB CDBC CDBD CDCA CDCB CDCC CDCD CDDA
									// CDDB CDDC CDDD DAAA DAAB DAAC DAAD DABA DABB
									// DABC DABD DACA DACB DACC DACD DADA DADB DADC
									// DADD DBAA DBAB DBAC DBAD DBBA DBBB DBBC DBBD
									// DBCA DBCB DBCC DBCD DBDA DBDB DBDC DBDD DCAA
									// DCAB DCAC DCAD DCBA DCBB DCBC DCBD DCCA DCCB
									// DCCC DCCD DCDA DCDB DCDC DCDD DDAA DDAB DDAC
									// DDAD DDBA DDBB DDBC DDBD DDCA DDCB DDCC DDCD
									// DDDA DDDB DDDC DDDD
		
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
				// this will prevent the duplicate strings
				while (f+1 < string.length() && string.charAt(f) == string.charAt(f+1)) {
					f++;
				}
				_everyLexicographicalPermutation(string, result + string.charAt(f));
			}
		} else {
			System.out.println("Parameters for _everyLexicographicalPermutation are null");
		}
	}
	
}
