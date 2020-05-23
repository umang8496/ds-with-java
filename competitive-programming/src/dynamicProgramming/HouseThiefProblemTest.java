package dynamicProgramming;

import org.junit.Assert;
import org.junit.Test;

public class HouseThiefProblemTest {
	@Test
	public void test_1() {
		Assert.assertEquals(4, HouseThiefProblem.rob(new int[] { 1, 2, 3, 1 }));
	}

	@Test
	public void test_2() {
		Assert.assertEquals(12, HouseThiefProblem.rob(new int[] { 2, 7, 9, 3, 1 }));
	}

	@Test
	public void test_3() {
		Assert.assertEquals(0, HouseThiefProblem.rob(new int[] { }));
		Assert.assertEquals(-1, HouseThiefProblem.rob(null));
	}
	
}
