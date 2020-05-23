package dynamicProgramming;

import org.junit.Assert;
import org.junit.Test;

/**
 * Fibonacci Series: 0 1 1 2 3 5 8 13 21 34 55 87 ...
 * 
 */
public class FibonacciSeriesTest {
	
	@Test
	public void test_1() {
		Assert.assertEquals(0, FibonacciSeries.fibonacciSeriesWithMemoization(1));
		Assert.assertEquals(1, FibonacciSeries.fibonacciSeriesWithMemoization(2));
		Assert.assertEquals(1, FibonacciSeries.fibonacciSeriesWithMemoization(3));
		Assert.assertEquals(2, FibonacciSeries.fibonacciSeriesWithMemoization(4));
		Assert.assertEquals(3, FibonacciSeries.fibonacciSeriesWithMemoization(5));
		Assert.assertEquals(5, FibonacciSeries.fibonacciSeriesWithMemoization(6));
		Assert.assertEquals(8, FibonacciSeries.fibonacciSeriesWithMemoization(7));
		Assert.assertEquals(13, FibonacciSeries.fibonacciSeriesWithMemoization(8));
		Assert.assertEquals(21, FibonacciSeries.fibonacciSeriesWithMemoization(9));
		Assert.assertEquals(34, FibonacciSeries.fibonacciSeriesWithMemoization(10));
	}
	
	@Test
	public void test_2() {
		Assert.assertEquals(0, FibonacciSeries.fibonacciSeriesWithTabulation(1));
		Assert.assertEquals(1, FibonacciSeries.fibonacciSeriesWithTabulation(2));
		Assert.assertEquals(1, FibonacciSeries.fibonacciSeriesWithTabulation(3));
		Assert.assertEquals(2, FibonacciSeries.fibonacciSeriesWithTabulation(4));
		Assert.assertEquals(3, FibonacciSeries.fibonacciSeriesWithTabulation(5));
		Assert.assertEquals(5, FibonacciSeries.fibonacciSeriesWithTabulation(6));
		Assert.assertEquals(8, FibonacciSeries.fibonacciSeriesWithTabulation(7));
		Assert.assertEquals(13, FibonacciSeries.fibonacciSeriesWithTabulation(8));
		Assert.assertEquals(21, FibonacciSeries.fibonacciSeriesWithTabulation(9));
		Assert.assertEquals(34, FibonacciSeries.fibonacciSeriesWithTabulation(10));
	}

}
