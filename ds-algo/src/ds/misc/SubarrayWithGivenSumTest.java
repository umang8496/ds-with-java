package ds.misc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SubarrayWithGivenSumTest {
	public static int[] array = null;

	@Before
	public void init() {
		array = new int[] { 4, 8, 2, 0, 3, 7, 4, 1, 9, 6 };
	}

	@Test
	public void test_1() {
		assertEquals(new Object[] { 1, 4 }, SubarrayWithGivenSum.findSubArray(array, 13));
	}

	@Test
	public void test_2() {
		assertEquals(new Object[] { 1, 2 }, SubarrayWithGivenSum.findSubArray(array, 10));
	}

	@Test
	public void test_3() {
		assertEquals(new Object[] { 1, 5 }, SubarrayWithGivenSum.findSubArray(array, 20));
	}
	
	@Test
	public void test_4() {
		assertEquals(new Object[] { 0, 1 }, SubarrayWithGivenSum.findSubArray(array, 12));
	}
}
