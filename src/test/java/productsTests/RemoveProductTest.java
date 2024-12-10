package productsTests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;

@Listeners(genericUtilities.ListenersImplementation.class)
public class RemoveProductTest extends BaseClass{
	
	@Test(groups = "RegressionSuite")
	public void removeProduct()
	{
		System.out.println("product removed");
	}

}
