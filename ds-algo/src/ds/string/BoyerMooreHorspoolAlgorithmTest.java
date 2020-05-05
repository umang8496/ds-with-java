package ds.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoyerMooreHorspoolAlgorithmTest {
	public String text_1 = null;
	public String text_2 = null;

	@Before
	public void init() {
		text_1 = new String("onionions");
		text_2 = new String("trailtraintrailtrain");
	}

	@Test
	public void test_1() {
		Assert.assertEquals(3, BoyerMooreHorspoolAlgorithm.search(text_1, "onions"));
		Assert.assertEquals(0, BoyerMooreHorspoolAlgorithm.search(text_1, "onion"));
		Assert.assertEquals(-1, BoyerMooreHorspoolAlgorithm.search(text_1, "train"));
	}

	@Test
	public void test_2() {
		Assert.assertEquals(0, BoyerMooreHorspoolAlgorithm.search(text_2, "trail"));
		Assert.assertEquals(5, BoyerMooreHorspoolAlgorithm.search(text_2, "train"));
		Assert.assertEquals(-1, BoyerMooreHorspoolAlgorithm.search(text_2, "onions"));
	}
	
	@Test
	public void test_3() {
		Assert.assertEquals(9, BoyerMooreHorspoolAlgorithm.search("trusthardtoothbrushes", "tooth"));
	}
}
