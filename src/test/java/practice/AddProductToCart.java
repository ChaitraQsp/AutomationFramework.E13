package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class AddProductToCart {
	
	public static void main(String[] args) {
		
		//Step 1: Launch the browser
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Step 2: Load the URL
		driver.get("https://www.saucedemo.com/");
		
		//Step 3: Login to Application
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		//Step 4: Click on a Product - sauce labs backpack
		String productToBeAdded = driver.findElement(By.xpath("//div[.='Sauce Labs Backpack']")).getText();
		driver.findElement(By.xpath("//div[.='Sauce Labs Backpack']")).click();
		
		//Step 5: Add product to cart
		driver.findElement(By.id("add-to-cart")).click();
		
		//Step 6: Navigate to Cart
		driver.findElement(By.className("shopping_cart_link")).click();
		
		//Step 7: Validate the product in cart
		String actProductInCart = driver.findElement(By.className("inventory_item_name")).getText();
		
		if(actProductInCart.equals(productToBeAdded))
		{
			System.out.println("PASS");
			System.out.println(actProductInCart);
		}
		else
		{
			System.out.println("FAIL");
		}
		
		//Step 8: Logout of Application
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.linkText("Logout")).click();
		
		//Step 9: Close the browser
		driver.close();
		
	}

}
