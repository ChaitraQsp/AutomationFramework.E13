package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUTility;
import genericUtilities.SeleniumUtility;
import objectRepository.LoginPage;

public class AddProductToCartUsingDDTAndGU {

	public static void main(String[] args) throws IOException {
		
		
		//Create Object Of All Required Utilities
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUTility pUtil = new PropertyFileUTility();
		SeleniumUtility sUtil = new SeleniumUtility();
		
		// Read Common Data from property file
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		// Read Test Data from Excel File
		String PRODUCTNAME = eUtil.readDataFromExcel("Products", 1, 2);
		
		// Step 1: Launch the browser
		WebDriver driver = new EdgeDriver();
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitelyWait(driver);

		// Step 2: Load the URL
		driver.get(URL);

		// Step 3: Login to Application
//		driver.findElement(By.id("user-name")).sendKeys(USERNAME);
//		driver.findElement(By.id("password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("login-button")).click();

		LoginPage lp = new LoginPage(driver);
//		lp.getUserNameEdt().sendKeys(USERNAME);
//		lp.getPasswordEdt().sendKeys(PASSWORD);
//		lp.getLoginBtn().click();
		
		lp.loginToApp(USERNAME, PASSWORD);
		
		
		
		
		
		// Step 4: Click on a Product - sauce labs backpack
		String productToBeAdded = driver.findElement(By.xpath("//div[.='" + PRODUCTNAME + "']")).getText();
		driver.findElement(By.xpath("//div[.='" + PRODUCTNAME + "']")).click();

		// Step 5: Add product to cart
		driver.findElement(By.id("add-to-cart")).click();

		// Step 6: Navigate to Cart
		driver.findElement(By.className("shopping_cart_link")).click();

		// Step 7: Validate the product in cart
		String actProductInCart = driver.findElement(By.className("inventory_item_name")).getText();

		if (actProductInCart.equals(productToBeAdded)) {
			System.out.println("PASS");
			System.out.println(actProductInCart);
		} else {
			System.out.println("FAIL");
		}

		// Step 8: Logout of Application
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.linkText("Logout")).click();

		// Step 9: Close the browser
		//driver.close();
	}

}
