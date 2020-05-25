package arrays;

import org.junit.Assert;
import org.junit.Test;

public class KthElementTest {

	@Test
	public void test_1() {
		Assert.assertEquals(4, KthElement.findKthLargestElement(new int[] { -2, 1, -3, 4, -1, 3, 1, -5, -4 }, 1));
	}

	@Test
	public void test_2() {
		Assert.assertEquals(-5, KthElement.findKthSmallestElement(new int[] { -2, 1, -3, 4, -1, 3, 1, -5, -4 }, 1));
	}

	@Test
	public void test_3() {
		Assert.assertEquals(3, KthElement.findKthLargestElement(new int[] { -2, 1, -3, 4, -1, 3, 1, -5, -4 }, 2));
	}

	@Test
	public void test_4() {
		Assert.assertEquals(-4, KthElement.findKthSmallestElement(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, -4 }, 2));
	}
	
	@Test
	public void test_5() {
		Assert.assertEquals(-2, KthElement.findKthLargestElement(new int[] {-2}, 1));
	}
	
	@Test
	public void test_6() {
		Assert.assertEquals(-2, KthElement.findKthSmallestElement(new int[] {-2}, 1));
	}
	
	@Test
	public void test_7() {
		Assert.assertEquals(4, KthElement.getKthLargestUsingPQ(new int[] { -2, 1, -3, 4, -1, 3, 1, -5, -4 }, 1));
	}
	
	@Test
	public void test_8() {
		Assert.assertEquals(3, KthElement.getKthLargestUsingPQ(new int[] { -2, 1, -3, 4, -1, 3, 1, -5, -4 }, 2));
	}

}
