package ds.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ZAlgorithmTest {
	public ZAlgorithm zAlgo = null;

	@Before
	public void init() {
		zAlgo = new ZAlgorithm();
	}

	@Test
	public void test_1() {
		Assert.assertEquals("aaaaa", zAlgo.findLongestSubString("aaaaaa"));
	}
	
	@Test
	public void test_2() {
		Assert.assertEquals("aa", zAlgo.findLongestSubString("aabaacd"));
	}
	
	@Test
	public void test_3() {
		Assert.assertEquals("ababab", zAlgo.findLongestSubString("abababab"));
	}
	
	@Test
	public void test_4() {
		Assert.assertEquals(null, zAlgo.findLongestSubString("abcdefgh"));
	}
}
