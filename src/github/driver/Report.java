package github.driver;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report extends DriverObject {
	private static ExtentReports extent;
	private static ExtentTest test;
	private static final String screenShotPath = "C:\\Users\\NUTHAN\\workspace\\NewGit\\ScreenShot";
	private static final String reports = "C:\\Users\\NUTHAN\\workspace\\NewGit\\Reports\\";
	public static String reportFileName;

	
	
	@BeforeClass
	public static void beforeClass() {
		extent = ExtentManager.getReporter(reports + reportFileName + ".html");
	}


	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String filePath = com.UtilsFiles.Utils.captureScreenshot(screenShotPath);
			filePath = test.addScreenCapture(filePath);
			test.log(LogStatus.FAIL, result.getThrowable().getMessage(),filePath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			test.log(LogStatus.PASS, "Test passed");
		}
		extent.endTest(test);
		extent.flush();
	}
	
    @AfterClass
    public void afterClass(){
    	Report.extent.close();
    }
 
	public void info(String message) {
		test.log(LogStatus.INFO, message);
	}

	public void fail(String message) {
		test.log(LogStatus.FAIL, message);
	}

	public void pass(String message) {
		test.log(LogStatus.PASS, message);
	}

	public void warning(String message) {
		test.log(LogStatus.WARNING, message);
	}

	public void startTest(String message) {
		test = extent.startTest(message);
		setTest(test);
	}

	private void setTest(ExtentTest test) {
		Report.test = test;
	}

	public void endTest() {
		extent.endTest(test);
	}

	public void assertContains(String completeString, CharSequence pattern, String message) {
		if (completeString.contains(pattern)) {
			test.log(LogStatus.PASS, message);
		} else {
			String filePath = com.UtilsFiles.Utils.captureScreenshot(screenShotPath);
			filePath = test.addScreenCapture(filePath);
			test.log(LogStatus.FAIL, message, filePath);
			Assert.assertTrue(false,message);
		}

	}

	public void assertTrue(Boolean value, String message) {
		if (value) {
			test.log(LogStatus.PASS, message);
		} else {
			String filePath = com.UtilsFiles.Utils.captureScreenshot(screenShotPath);
			filePath = test.addScreenCapture(filePath);
			test.log(LogStatus.FAIL, message, filePath);
			Assert.assertTrue(false,message);
		}
	}

	public void assertEquals(String firstString, String secondString, String message) {
		if (firstString.equals(secondString)) {
			test.log(LogStatus.PASS, message);
		} else {
			String filePath = com.UtilsFiles.Utils.captureScreenshot(screenShotPath);
			filePath = test.addScreenCapture(filePath);
			test.log(LogStatus.FAIL, message, filePath);
			Assert.assertTrue(false,message);
		}
	}
	public static Report getReport(Class className) {
		reportFileName=className.getSimpleName();
		return new Report();

	}

}
