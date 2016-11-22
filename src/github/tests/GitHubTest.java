package github.tests;
import java.sql.Driver;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import github.driver.DriverObject;
import github.driver.Report;
import github.driver.Wait;



public class GitHubTest extends Report {

	Report LOG=Report.getReport(GitHubTest.class);	
	@Test
	public void github_login() throws InterruptedException{	
		LOG.startTest("Github login test case have been started");
		DriverObject.getDriver().get("https://github.com/login");	
		DriverObject.getDriver().findElement(By.id("login_field")).sendKeys("prathikshams3@gmail.com");
		DriverObject.getDriver().findElement(By.id("password")).sendKeys("Amma12345");
		DriverObject.getDriver().findElement(By.xpath("//input[@value='Sign in']")).click();
		LOG.assertEquals(DriverObject.getDriver().getTitle(), "GitHub", "Logged into github successfully");
		DriverObject.getDriver().findElement(By.xpath("//ul[@id='user-links']/li[3]/a")).click();
		DriverObject.getDriver().findElement(By.xpath("//ul[@id='user-links']/li[3]/div/div/form/button")).click();
		Wait.untilElementAppears(DriverObject.getDriver().findElement(By.id("user[login]")), 10000);
		LOG.assertEquals(DriverObject.getDriver().getTitle(),"How people build software · GitHub"," logged out successfully");
		LOG.endTest();
	}
	@Test
	public void github_login_error() throws InterruptedException{
		LOG.startTest("Github login test case have been started");
		DriverObject.getDriver().get("https://github.com/login");
		DriverObject.getDriver().findElement(By.id("login_field")).sendKeys("prathikshagmail.com$");
		DriverObject.getDriver().findElement(By.id("password")).sendKeys("123$%%");
		DriverObject.getDriver().findElement(By.xpath("//input[@value='Sign in']")).click();
		Thread.sleep(5000);
		LOG.assertEquals(DriverObject.getDriver().findElement(By.xpath("//div[@id='js-flash-container']/div/div")).getText(),"Incorrect username or password.","Incorrect username or password  text is present");
		LOG.endTest();
		
	}
	//@Test
	public void TC_Finace_003(){
		LOG.startTest("Github login test case ended");
		
		LOG.endTest();
	}
	}

