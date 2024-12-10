package genericUtilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provides implementation for ITestListener interface of TestNG
 * @author Chaitra M
 *
 */
public class ListenersImplementation implements ITestListener{
	
	//To Capture current system date and time
	Date d = new Date();
	SimpleDateFormat f = new SimpleDateFormat(" dd-MM-yyyy hh-mm-ss");
	String date = f.format(d);
	
	//For Extent Reports
	ExtentReports report;
	ExtentTest test;
	

	public void onTestStart(ITestResult result) {
		
		String testMethodName = result.getMethod().getMethodName();
		System.out.println(testMethodName+"  --- Test execution started ---");
		
		//Intimate the @Test for extent reports
		test = report.createTest(testMethodName);
		
			
	}

	public void onTestSuccess(ITestResult result) {
		
		String testMethodName = result.getMethod().getMethodName();
		System.out.println(testMethodName+"  --- Test PASS ---");
		
		//Log the test status as PASS in Extent reports
		test.log(Status.PASS, testMethodName+"  --- Test PASS ---");
		
	}

	public void onTestFailure(ITestResult result) {
		
		String testMethodName = result.getMethod().getMethodName();
		System.out.println(testMethodName+"  --- Test FAIL ---");
		
		//Capture Exception
		System.out.println(result.getThrowable());
		
		//Log the Status as FAIL in Extent Report
		test.log(Status.FAIL, testMethodName+"  --- Test FAIL ---");
		
		//Log the exception in extent reports
		test.log(Status.WARNING,result.getThrowable() );
		
		
		//Capture the screenshot
		SeleniumUtility s = new SeleniumUtility();
		
		//Configuring screenshot name
		String screenshotName = testMethodName+date;
		
		try 
		{
			String path = s.captureScreenShot(BaseClass.sdriver,screenshotName);
			
			//Attach the screenshot to extent reports
			test.addScreenCaptureFromPath(path, screenshotName);
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		
		String testMethodName = result.getMethod().getMethodName();
		System.out.println(testMethodName+"  --- Test SKIP ---");
		
		//Capture Exception
		System.out.println(result.getThrowable());
		
		//Log the status as SKIP in extent Reports
		test.log(Status.SKIP, testMethodName+"  --- Test SKIP ---");
		
		//Log the exception in extent report
		test.log(Status.WARNING, result.getThrowable());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
		System.out.println("--- Suite execution started ---");
		
		//Configure extent Report
		ExtentSparkReporter esr = new ExtentSparkReporter(".\\ExtentReports\\Report -"+date+".html");
		esr.config().setDocumentTitle("Swag Labs Execution Report");
		esr.config().setReportName("Execution Report");
		esr.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(esr);
		report.setSystemInfo("Base Browser", "Microsoft edge");
		report.setSystemInfo("Base Platform", "Windows Family");
		report.setSystemInfo("Base URL", "http://testEnv.com");
		report.setSystemInfo("Reporter Name", "Chaitra");
		
		
	}

	public void onFinish(ITestContext context) {
		
		System.out.println("--- Suite Execution Finished ---");
		
		//Generate Extent Report
		report.flush();
		
	}
	
	

}
