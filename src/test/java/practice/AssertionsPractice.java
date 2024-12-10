package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsPractice {
	
	@Test
	public void test()
	{
		System.out.println("step 1");
		Assert.assertEquals(0, 1);//p
		System.out.println("step 2");
		System.out.println("step 3");
	}
	
	@Test
	public void testSoft()
	{
		SoftAssert sa = new SoftAssert();

		System.out.println("step 1");
		sa.assertEquals(0, 1);//f
		System.out.println("step 2");
		sa.assertTrue(false);
		System.out.println("step 3");
		
		sa.assertAll(); //log
	}

}
