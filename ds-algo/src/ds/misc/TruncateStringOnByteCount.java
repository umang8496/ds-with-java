package ds.misc;

public class TruncateStringOnByteCount {
	
	private static String str01 = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$";	// size: 50 bytes
	private static String str02 = "©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©";	// size:100 bytes
	private static String str03 = "€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€";	// size:150 bytes
	
	public static void main(String[] args) {
		System.out.println("Each $ character takes 1 byte  to get stored.");
		System.out.println("Each © character takes 2 bytes to get stored.");
		System.out.println("Each € character takes 3 bytes to get stored.");
		System.out.println();
		System.out.println();
		
		System.out.println("str01: " + str01);
		System.out.println("str02: " + str02);
		System.out.println("str03: " + str03);
		System.out.println();
		System.out.println();
		
		System.out.println("Truncate str01 beyond 30 bytes: " + truncateUTF8(str01, 30));
		System.out.println("Truncate str02 beyond 30 bytes: " + truncateUTF8(str02, 30));
		System.out.println("Truncate str03 beyond 30 bytes: " + truncateUTF8(str03, 30));
	}
	
	public static String truncateUTF8(String val_str, int maxBytes) {
		int b = 0;
		for (int i = 0; i < val_str.length(); i++) {
			char ch = val_str.charAt(i);

			int skip = 0;
			int more;
			if (ch <= 0x007f) {				// 0x007f = 127
				more = 1;
			} else if (ch <= 0x07ff) {		// 0x07ff = 2047
				more = 2;
			} else if (ch <= 0xffff) {		// 0xffff = 65535
				more = 3;
			} else if (ch <= 0x10ffff) {	// 0x10ffff = 1114111
				more = 4;
				skip = 1;
			} else {
				more = 3;
			}

			if (b + more > maxBytes) {
				return val_str.substring(0, i);
			}
			b += more;
			i += skip;
		}
		return val_str;
	}
	
}
