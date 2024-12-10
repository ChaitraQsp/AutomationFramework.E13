package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.SeleniumUtility;

public class InventoryPage extends SeleniumUtility{
	
	//declaration
	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuBtn;
	
	@FindBy(linkText = "Logout")
	private WebElement logoutLnk;
	
	//Initilaization
	public InventoryPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getMenuBtn() {
		return menuBtn;
	}

	public WebElement getLogoutLnk() {
		return logoutLnk;
	}

	//Business Library - operation
	/**
	 * This method will click on menu button
	 */
	public void clickOnMenubutton()
	{
		menuBtn.click();
	}
	
	/**
	 * This method will perform logout operation
	 */
	public void logoutOfApp()
	{
		menuBtn.click();
		logoutLnk.click();
	}
	
	/**
	 * This method will click on a particular product and capture the title of 
	 * product and return it to caller
	 * @param driver
	 * @param PRODUCTNAME
	 * @return
	 */
	public String clickOnProductAndCaptureTitle(WebDriver driver , String PRODUCTNAME)
	{
		String ProductTitle = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).getText();
		driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']")).click();
		
		return ProductTitle; //use for validation
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
