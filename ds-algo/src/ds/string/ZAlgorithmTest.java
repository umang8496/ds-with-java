package ds.string;

import java.util.Arrays;

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
		Assert.assertEquals("aaaaa", zAlgo.findLongestPrefixSuffixString("aaaaaa"));
		Assert.assertEquals("aa", zAlgo.findLongestPrefixSuffixString("aabaacd"));
	}

	@Test
	public void test_2() {
		Assert.assertEquals("ababab", zAlgo.findLongestPrefixSuffixString("abababab"));
		Assert.assertEquals(null, zAlgo.findLongestPrefixSuffixString("abcdefgh"));
	}

	@Test
	public void test_3() {
		Assert.assertEquals(-1, zAlgo.search("trailtraintrailtrain", "tooth"));
		Assert.assertEquals(5, zAlgo.search("trailtraintrailtrain", "train"));
	}

	@Test
	public void test_4() {
		Assert.assertEquals(Arrays.toString(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}),
				Arrays.toString(zAlgo.searchAll("trailtraintrailtrain", "tooth")));
		Assert.assertEquals(Arrays.toString(new int[] {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0}),
				Arrays.toString(zAlgo.searchAll("trailtraintrailtrain", "train")));
	}

}
