package ds.string;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class BruteForcePatternMatchingTest {
	public String text_1 = null;
	public String text_2 = null;
	
	@Before
	public void init() {
		text_1 = new  String("onionions");
		text_2 = new  String("trailtraintrailtrain");
	}
	
	@Test
	public void testFirstMatch() {
		Assert.assertEquals(3, BruteForcePatternMatching.findFirstMatch(text_1, "onions"));
		Assert.assertEquals(0, BruteForcePatternMatching.findFirstMatch(text_1, "onion"));
	}
	
	@Test
	public void testEveryMatch() {
		int[] expect = new int[text_2.length()];
        resetExpectArray(expect);
        expect[0] = 0;
        expect[1] = 10;
        System.out.println("expected: " + Arrays.toString(expect));
        int[] found = BruteForcePatternMatching.findEveryMatch(text_2, "trail");
        System.out.println("original: " + Arrays.toString(found));
        Assert.assertArrayEquals(expect, found);

        resetExpectArray(expect);
        expect[0] = 5;
        expect[1] = 15;
        System.out.println("expected: " + Arrays.toString(expect));
        found = BruteForcePatternMatching.findEveryMatch(text_2, "train");
        System.out.println("original: " + Arrays.toString(found));
        Assert.assertArrayEquals(expect, found);
	}
	
	public void resetExpectArray(int[] expect) {
        Arrays.fill(expect,-1);
    }
}
