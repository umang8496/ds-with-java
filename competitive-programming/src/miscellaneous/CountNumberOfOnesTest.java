package miscellaneous;

import org.junit.Assert;
import org.junit.Test;

public class CountNumberOfOnesTest {

	@Test
	public void test_1() {
		Assert.assertEquals(2, CountNumberOfOnes.numberOfOnesByBruteForce(6));
		Assert.assertEquals(2, CountNumberOfOnes.numberOfOnesByBrianKernighan(6));
	}
	
	@Test
	public void test_2() {
		Assert.assertEquals(2, CountNumberOfOnes.numberOfOnesByBruteForce(9));
		Assert.assertEquals(2, CountNumberOfOnes.numberOfOnesByBrianKernighan(9));
	}
	
	@Test
	public void test_3() {
		Assert.assertEquals(0, CountNumberOfOnes.numberOfOnesByBruteForce(0));
		Assert.assertEquals(0, CountNumberOfOnes.numberOfOnesByBrianKernighan(0));
	}
	
	@Test
	public void test_4() {
		Assert.assertEquals(8, CountNumberOfOnes.numberOfOnesByBruteForce(255));
		Assert.assertEquals(8, CountNumberOfOnes.numberOfOnesByBrianKernighan(255));
	}
	
	@Test
	public void test_5() {
		Assert.assertEquals(10, CountNumberOfOnes.numberOfOnesByBruteForce(1023));
		Assert.assertEquals(10, CountNumberOfOnes.numberOfOnesByBrianKernighan(1023));
	}
}
