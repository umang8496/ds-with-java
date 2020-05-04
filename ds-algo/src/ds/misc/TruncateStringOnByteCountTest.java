package ds.misc;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TruncateStringOnByteCountTest {
	public String str_1_byte = new String("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");	// 1-bytes each
	public String str_2_byte = new String("©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©©");	// 2-bytes each
	public String str_3_byte = new String("€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€€");	// 3-bytes each

	@Test
	public void test_1_Byte_Chars() {
		assertEquals("@@", TruncateStringOnByteCount.truncateUTF8(str_1_byte, 2));
		assertEquals("@@@@", TruncateStringOnByteCount.truncateUTF8(str_1_byte, 4));
		assertEquals("@@@@@@", TruncateStringOnByteCount.truncateUTF8(str_1_byte, 6));
		assertEquals("@@@@@@@@", TruncateStringOnByteCount.truncateUTF8(str_1_byte, 8));
		
		assertEquals("@", TruncateStringOnByteCount.truncateUTF8(str_1_byte, 1));
		assertEquals("@@@", TruncateStringOnByteCount.truncateUTF8(str_1_byte, 3));
		assertEquals("@@@@@", TruncateStringOnByteCount.truncateUTF8(str_1_byte, 5));
		assertEquals("@@@@@@@", TruncateStringOnByteCount.truncateUTF8(str_1_byte, 7));
	}

	@Test
	public void test_2_Byte_Chars() {
		assertEquals("©", TruncateStringOnByteCount.truncateUTF8(str_2_byte, 2));
		assertEquals("©©", TruncateStringOnByteCount.truncateUTF8(str_2_byte, 4));
		assertEquals("©©©©", TruncateStringOnByteCount.truncateUTF8(str_2_byte, 8));
		assertEquals("©©©©©©©©", TruncateStringOnByteCount.truncateUTF8(str_2_byte, 16));
		
		assertEquals("©", TruncateStringOnByteCount.truncateUTF8(str_2_byte, 3));
		assertEquals("©©©", TruncateStringOnByteCount.truncateUTF8(str_2_byte, 6));
		assertEquals("©©©©©", TruncateStringOnByteCount.truncateUTF8(str_2_byte, 11));
		assertEquals("©©©©©©©©©©", TruncateStringOnByteCount.truncateUTF8(str_2_byte, 20));
	}

	@Test
	public void test_3_Byte_Chars() {
		assertEquals("€", TruncateStringOnByteCount.truncateUTF8(str_3_byte, 3));
		assertEquals("€€", TruncateStringOnByteCount.truncateUTF8(str_3_byte, 6));
		assertEquals("€€€", TruncateStringOnByteCount.truncateUTF8(str_3_byte, 9));
		assertEquals("€€€€", TruncateStringOnByteCount.truncateUTF8(str_3_byte, 12));
		assertEquals("€€€€€", TruncateStringOnByteCount.truncateUTF8(str_3_byte, 15));
		
		assertEquals("€", TruncateStringOnByteCount.truncateUTF8(str_3_byte, 5));
		assertEquals("€€€", TruncateStringOnByteCount.truncateUTF8(str_3_byte, 10));
	}
	
}
