package productsTests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUTility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;

@Listeners(genericUtilities.ListenersImplementation.class)
public class AddProductToCartTest extends BaseClass{
	
	@Test(groups = "SmokeSuite")
	public void tc_001_AddProductToCartTest() throws IOException 
	{
		
		// Read Test Data from Excel File
		String PRODUCTNAME = eUtil.readDataFromExcel("Products", 1, 2);
		
		// Step 1: Navigate to inventory Page and click on a Product 
		InventoryPage ip = new InventoryPage(driver);
		String productToBeAddedToCart = ip.clickOnProductAndCaptureTitle(driver, PRODUCTNAME);

		// Step 2: Navigate to product Page and Add product to cart
		ProductPage pp = new ProductPage(driver);
		pp.clickOnAddToCartBtn();
		
		// Step 3: Navigate to Cart 
		pp.clickonCartContainerLnk();
		
		//Assert.fail();

		// Step 4: Capture product info from Cart
		CartPage cp = new CartPage(driver);
		String actProductInCart = cp.captureProductInfo();
		
		// Step 5: Validate for product Name
		Assert.assertEquals(actProductInCart, productToBeAddedToCart);
		System.out.println(actProductInCart+" -> product added");

	}

}
