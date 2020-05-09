package ds.dp;

import org.junit.Assert;
import org.junit.Test;

public class UglyNumbersTest {
	@Test
	public void test_1() {
		Assert.assertEquals(true, UglyNumbers.isUglyNumber(1));
		Assert.assertEquals(true, UglyNumbers.isUglyNumber(2));
		Assert.assertEquals(true, UglyNumbers.isUglyNumber(3));
	}

	@Test
	public void test_2() {
		Assert.assertEquals(false, UglyNumbers.isUglyNumber(13));
		Assert.assertEquals(false, UglyNumbers.isUglyNumber(21));
		Assert.assertEquals(false, UglyNumbers.isUglyNumber(47));
		Assert.assertEquals(false, UglyNumbers.isUglyNumber(7));
	}

	@Test
	public void test_3() {
		Assert.assertEquals(1, UglyNumbers.getNthUglyNumber(1));
		Assert.assertEquals(8, UglyNumbers.getNthUglyNumber(7));
		Assert.assertEquals(5832, UglyNumbers.getNthUglyNumber(150));
	}
}
