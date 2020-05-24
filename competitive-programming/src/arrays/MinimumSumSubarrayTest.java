package arrays;

import org.junit.Assert;
import org.junit.Test;

public class MinimumSumSubarrayTest {

	@Test
	public void test_1() {
		Assert.assertEquals(-28, MinimumSumSubarray.minSumForSubarray(new int[] { -7, -6, -5, -4, -3, -2, -1 }));
	}
	
	@Test
	public void test_2() {
		Assert.assertEquals(-28, MinimumSumSubarray.minSumForSubarray(new int[] { -7, -6, -5, -4, -3, -2, -1, 0 }));
	}
	
	@Test
	public void test_3() {
		Assert.assertEquals(Integer.MAX_VALUE, MinimumSumSubarray.minSumForSubarray(null));
	}
	
	@Test
	public void test_4() {
		Assert.assertEquals(-2, MinimumSumSubarray.minSumForSubarray(new int[] {-2}));
	}
	
	@Test
	public void test_5() {
		Assert.assertEquals(-5, MinimumSumSubarray.minSumForSubarray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
	}
	
	@Test
	public void test_6() {
		Assert.assertEquals(Integer.MAX_VALUE, MinimumSumSubarray.minSumForSubarray(new int[] {}));
	}
	
}
