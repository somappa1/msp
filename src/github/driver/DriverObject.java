package github.driver;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class DriverObject {
	public static WebDriver driver;


@BeforeSuite
@Parameters("browser")
	public void intializeDriver(String browser) {
	if(browser.equals("firefox")){
		FirefoxProfile fp = new FirefoxProfile();
		driver = new FirefoxDriver(fp);
	}
	else{
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\NUTHAN\\workspace\\SeleniumPrathi\\chromedriver\\chromedriver.exe");
		driver=new ChromeDriver();
	}
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

public static WebDriver getDriver() {
		return driver;
	}


	@AfterSuite
	public void closeDriver() {
	
		DriverObject.driver.quit();
		File htmlFile = new File("C:\\Users\\NUTHAN\\workspace\\SeleniumPrathi\\Reports\\AccountMangmentTest.html");
		try {
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
