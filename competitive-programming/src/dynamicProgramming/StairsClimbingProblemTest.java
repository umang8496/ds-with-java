package dynamicProgramming;

import org.junit.Assert;
import org.junit.Test;

public class StairsClimbingProblemTest {
	
	@Test
	public void test_1() {
		Assert.assertEquals(1, StairsClimbingProblem.numberOfWaysToClimb(1));
		Assert.assertEquals(2, StairsClimbingProblem.numberOfWaysToClimb(2));
		Assert.assertEquals(3, StairsClimbingProblem.numberOfWaysToClimb(3));
		Assert.assertEquals(5, StairsClimbingProblem.numberOfWaysToClimb(4));
		Assert.assertEquals(8, StairsClimbingProblem.numberOfWaysToClimb(5));
		Assert.assertEquals(1836311903, StairsClimbingProblem.numberOfWaysToClimb(45));
	}
	
	@Test
	public void test_2() {
		Assert.assertEquals(1, StairsClimbingProblem.numberOfWaysToClimbUsingDP(1));
		Assert.assertEquals(2, StairsClimbingProblem.numberOfWaysToClimbUsingDP(2));
		Assert.assertEquals(3, StairsClimbingProblem.numberOfWaysToClimbUsingDP(3));
		Assert.assertEquals(5, StairsClimbingProblem.numberOfWaysToClimbUsingDP(4));
		Assert.assertEquals(8, StairsClimbingProblem.numberOfWaysToClimbUsingDP(5));
		Assert.assertEquals(1836311903, StairsClimbingProblem.numberOfWaysToClimbUsingDP(45));
	}
	
}
