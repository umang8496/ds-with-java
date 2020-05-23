package arrays;

import org.junit.Assert;
import org.junit.Test;

public class MaximumDiffBetweenTwoElementsTest {

	@Test
	public void test_1() {
		Assert.assertEquals(80, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {100, 180}));
		Assert.assertEquals(0, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {100, 100}));
		Assert.assertEquals(0, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {100}));
		Assert.assertEquals(0, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {}));
		Assert.assertEquals(8, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {2, 3, 10, 6, 4, 8, 1}));
		Assert.assertEquals(2, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {7, 9, 5, 6, 3, 2}));
	}
	
	@Test
	public void test_2() {
		Assert.assertEquals(80, MaximumDiffBetweenTwoElements.maximumDifferenceEfficientApproach(new int[] {100, 180}));
		Assert.assertEquals(0, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {100, 100}));
		Assert.assertEquals(0, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {100}));
		Assert.assertEquals(0, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {}));
		Assert.assertEquals(8, MaximumDiffBetweenTwoElements.maximumDifferenceEfficientApproach(new int[] {2, 3, 10, 6, 4, 8, 1}));
		Assert.assertEquals(2, MaximumDiffBetweenTwoElements.maximumDifferenceEfficientApproach(new int[] {7, 9, 5, 6, 3, 2}));
	}
	
	@Test
	public void test_3() {
		Assert.assertEquals(80, MaximumDiffBetweenTwoElements.maximumDifferenceUsingMaxSumSubarray(new int[] {100, 180}));
		Assert.assertEquals(0, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {100, 100}));
		Assert.assertEquals(0, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {100}));
		Assert.assertEquals(0, MaximumDiffBetweenTwoElements.maximumDifferenceNaiveApproach(new int[] {}));
		Assert.assertEquals(8, MaximumDiffBetweenTwoElements.maximumDifferenceUsingMaxSumSubarray(new int[] {2, 3, 10, 6, 4, 8, 1}));
		Assert.assertEquals(2, MaximumDiffBetweenTwoElements.maximumDifferenceUsingMaxSumSubarray(new int[] {7, 9, 5, 6, 3, 2}));
		Assert.assertEquals(98, MaximumDiffBetweenTwoElements.maximumDifferenceUsingMaxSumSubarray(new int[] {80, 2, 6, 3, 100}));
	}
	
}

