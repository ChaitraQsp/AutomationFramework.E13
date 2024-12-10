package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.InventoryPage;
import objectRepository.LoginPage;

/**
 * This class consists of basic configuration annotations of testNG
 * @author Chaitra M
 *
 */
public class BaseClass {
	
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public PropertyFileUTility pUtil = new PropertyFileUTility();
	public SeleniumUtility sUtil = new SeleniumUtility();
	
	public WebDriver driver;
	
	//For LIsteners
	public static WebDriver sdriver;
	
	@BeforeSuite(groups = {"SmokeSuite","RegressionSuite"})
	public void bsConfig()
	{
	     System.out.println(" ==== Database Connection succesfull ===== ");
	}
	
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(alwaysRun = true)
	public void bcConfig(/*String PARAMETERVALUE*/) throws IOException
	{
//		//For Cross Browser Execution
//		if(PARAMETERVALUE.equalsIgnoreCase("Firefox"))
//		{
//			driver = new FirefoxDriver();
//		}
//		else if(PARAMETERVALUE.equalsIgnoreCase("edge"))
//		{
//			driver = new EdgeDriver();
//		}
//		else
//		{
//			driver = new EdgeDriver();
//		}
		
		
		String URL = pUtil.readDataFromPropertyFile("url");
		
		driver = new EdgeDriver();
		
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitelyWait(driver);
		
		driver.get(URL);
		
		System.out.println(" ==== Browser Launch succesfull ===== ");
		
		//For Listeners
		sdriver=driver;
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws IOException
	{
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		System.out.println(" ==== Login to App succesfull ===== ");
	}
	
	@AfterMethod(alwaysRun = true)
	public void amConfig()
	{
		InventoryPage ip = new InventoryPage(driver);
		ip.logoutOfApp();
		
		System.out.println(" ==== Logout of App succesfull ===== ");
	}
	
	//@AfterTest
	@AfterClass(alwaysRun = true)
	public void acConfig()
	{
		driver.quit();
		
		System.out.println(" ==== Browser closure succesfull ===== ");
	}
	
	@AfterSuite(alwaysRun = true)
	public void asConfig()
	{
		 System.out.println(" ==== Database Connection succesfull ===== ");
	}

}
