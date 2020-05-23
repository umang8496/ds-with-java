package dynamicProgramming;

import org.junit.Assert;
import org.junit.Test;

public class BuyAndSellStocksProblemTest {

	@Test
	public void test_1() {
		Assert.assertEquals(80, BuyAndSellStocksProblem.maximizeTheProfitWithNoTransactionLimits(new int[] {100, 180}));
	}
	
	@Test
	public void test_2() {
		Assert.assertEquals(-1, BuyAndSellStocksProblem.maximizeTheProfitWithNoTransactionLimits(null));
	}
	
	@Test
	public void test_3() {
		Assert.assertEquals(0, BuyAndSellStocksProblem.maximizeTheProfitWithNoTransactionLimits(new int[] {100}));
	}
	
	@Test
	public void test_4() {
		Assert.assertEquals(160, BuyAndSellStocksProblem.maximizeTheProfitWithNoTransactionLimits(new int[] {100, 180, 260}));
	}
	
	@Test
	public void test_5() {
		Assert.assertEquals(860, BuyAndSellStocksProblem.maximizeTheProfitWithNoTransactionLimits(new int[] {100, 180, 260, 310, 40, 540, 690}));
	}
	
}

