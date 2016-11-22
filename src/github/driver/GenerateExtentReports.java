package github.driver;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenerateExtentReports {
	
	
	public static GenerateExtentReports getGenerateExtentReports(){
		return new GenerateExtentReports();
	}
	
	public ExtentTest startTest(String description){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement method = stacktrace[stacktrace.length-1];
		String methodName = method.getMethodName();
		String current="";
		String projectPath="";
		 try {
			 current = new java.io.File( "." ).getCanonicalPath();
			 projectPath=current.split("src")[0]+"//Reports";
		} catch (IOException e) {
		System.out.println("IO Exception occurred");
		}
		ExtentReports extent = new ExtentReports(projectPath+"//"+method.getFileName().split("\\.")[0]+".html");
		ExtentTest test=extent.startTest(methodName, description);
		extent.flush();
		return test;
	}
	
	
	public void endTest(ExtentTest test){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement method = stacktrace[stacktrace.length-1];
		String current="";
		String projectPath="";
		 try {
			 current = new java.io.File( "." ).getCanonicalPath();
			 projectPath=current.split("src")[0]+"//Reports";
		} catch (IOException e) {
		}
		ExtentReports extent = new ExtentReports(projectPath+"//"+method.getFileName().split("\\.")[0]+".html");
		test.log(LogStatus.PASS,method.getMethodName()+" test case passed successfully");
		extent.endTest(test);
		extent.flush();
		
	
	}
	
	public void takeScreenShot(ExtentTest test){
	  Wait.oneSecond();
	  StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	  StackTraceElement method = stacktrace[stacktrace.length-1];
	  String current="";
	  String projectPath="";
	  Calendar cal = Calendar.getInstance();
	  SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
	try {
		current = new java.io.File( "." ).getCanonicalPath();
	} catch (IOException e) {		
	}
	   ExtentReports extent = new ExtentReports(projectPath+"//"+method.getFileName().split("\\.")[0]+".html");
	   projectPath=current.split("src")[0]+"//Reports";
		test.log(LogStatus.INFO, "Screencast below for the failure: " + test.addScreencast(projectPath+"//"+sdf.format(cal.getTime())+".png"));
		extent.flush();
	}
	
	
	public void info(String info,ExtentTest test){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement method = stacktrace[stacktrace.length-1];
		String current="";
		String projectPath="";
		 try {
			 current = new java.io.File( "." ).getCanonicalPath();
			 projectPath=current.split("src")[0]+"//Reports";
		} catch (IOException e) {
		System.out.println("IO Exception occurred");
		}
		ExtentReports extent = new ExtentReports(projectPath+"//"+method.getFileName().split("\\.")[0]+".html");
		test.log(LogStatus.INFO, info);
		extent.flush();
		
	}
	
	public void assertTrue(boolean assertValue,String message,ExtentTest test){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement method = stacktrace[stacktrace.length-1];
		String current="";
		String projectPath="";
		 try {
			 current = new java.io.File( "." ).getCanonicalPath();
			 projectPath=current.split("src")[0]+"//Reports";
		} catch (IOException e) {
		System.out.println("IO Exception occurred");
		}
		if(assertValue){
			test.log(LogStatus.PASS, message);
		}
		else{
			takeScreenShot(test);
			test.log(LogStatus.FAIL, message);		
		}
		ExtentReports extent = new ExtentReports(projectPath+"//"+method.getFileName().split("\\.")[0]+".html");
		extent.flush();
		
		
	}
	
	
	public void assertEquals(String text1,String text2,String message,ExtentTest test){
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement method = stacktrace[stacktrace.length-1];
		String current="";
		String projectPath="";
		 try {
			 current = new java.io.File( "." ).getCanonicalPath();
			 projectPath=current.split("src")[0]+"//Reports";
		} catch (IOException e) {
		System.out.println("IO Exception occurred");
		}
		if(text1.equals(text2)){
			test.log(LogStatus.PASS, message);
		}
		else{
			takeScreenShot(test);
			test.log(LogStatus.FAIL, message);
		}
		ExtentReports extent = new ExtentReports(projectPath+"//"+method.getFileName().split("\\.")[0]+".html");
		extent.flush();
		
		
	}
	
	
	
}
