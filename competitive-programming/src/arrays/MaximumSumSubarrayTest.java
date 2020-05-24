package arrays;

import org.junit.Assert;
import org.junit.Test;

public class MaximumSumSubarrayTest {

	@Test
	public void test_1() {
		Assert.assertEquals(6, MaximumSumSubarray.maxSumForSubarray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
	}
	
	@Test
	public void test_2() {
		Assert.assertEquals(Integer.MIN_VALUE, MaximumSumSubarray.maxSumForSubarray(new int[] {}));
	}
	
	@Test
	public void test_3() {
		Assert.assertEquals(Integer.MIN_VALUE, MaximumSumSubarray.maxSumForSubarray(null));
	}
	
	@Test
	public void test_4() {
		Assert.assertEquals(-2, MaximumSumSubarray.maxSumForSubarray(new int[] {-2}));
	}
	
	@Test
	public void test_5() {
		Assert.assertEquals(0, MaximumSumSubarray.maxSumForSubarray(new int[] { -7, -6, -5, -4, -3, -2, -1, 0 }));
	}
	
	@Test
	public void test_6() {
		Assert.assertEquals(-1, MaximumSumSubarray.maxSumForSubarray(new int[] { -7, -6, -5, -4, -3, -2, -1 }));
	}
	
}
