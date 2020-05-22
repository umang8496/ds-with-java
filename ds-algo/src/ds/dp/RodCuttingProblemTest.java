package ds.dp;

import org.junit.Assert;
import org.junit.Test;

public class RodCuttingProblemTest {

	@Test
	public void test_1() {
		Assert.assertEquals(22, RodCuttingProblem.maximizeValueForRod(new int[] { 1, 5, 8, 9, 10, 17, 17, 20 }, 8));
		Assert.assertEquals(10, RodCuttingProblem.maximizeValueForRod(new int[] { 1, 5, 8, 9 }, 4));
		Assert.assertEquals(13, RodCuttingProblem.maximizeValueForRod(new int[] { 1, 5, 8, 9, 10 }, 5));
	}

	@Test
	public void test_2() {
		Assert.assertEquals(22, RodCuttingProblem.maximizeValueForRodUsingDP(new int[] { 1, 5, 8, 9, 10, 17, 17, 20 }, 8));
		Assert.assertEquals(10, RodCuttingProblem.maximizeValueForRodUsingDP(new int[] { 1, 5, 8, 9 }, 4));
		Assert.assertEquals(13, RodCuttingProblem.maximizeValueForRodUsingDP(new int[] { 1, 5, 8, 9, 10 }, 5));
	}

}
