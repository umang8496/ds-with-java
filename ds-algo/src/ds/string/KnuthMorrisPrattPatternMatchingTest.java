package ds.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KnuthMorrisPrattPatternMatchingTest {
	public String text_1 = null;
	public String text_2 = null;
	
	@Before
	public void init() {
		text_1 = new  String("onionions");
		text_2 = new  String("trailtraintrailtrain");
	}
	
	@Test
	public void test_1() {
		Assert.assertEquals(3, KnuthMorrisPrattPatternMatching.findFirstMatchUsingKMP(text_1, "onions"));
		Assert.assertEquals(0, KnuthMorrisPrattPatternMatching.findFirstMatchUsingKMP(text_1, "onion"));
	}
	
	@Test
	public void test_2() {
		Assert.assertEquals(0, KnuthMorrisPrattPatternMatching.findFirstMatchUsingKMP(text_2, "trail"));
		Assert.assertEquals(5, KnuthMorrisPrattPatternMatching.findFirstMatchUsingKMP(text_2, "train"));
	}

}
