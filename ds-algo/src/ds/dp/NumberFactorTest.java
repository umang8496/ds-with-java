package ds.dp;

import org.junit.Assert;
import org.junit.Test;

public class NumberFactorTest {

	@Test
	public void test_1() {
		Assert.assertEquals(1, NumberFactor.waysToGetNumber(1));
		Assert.assertEquals(4, NumberFactor.waysToGetNumber(4));
		Assert.assertEquals(6, NumberFactor.waysToGetNumber(5));
	}
	
}
