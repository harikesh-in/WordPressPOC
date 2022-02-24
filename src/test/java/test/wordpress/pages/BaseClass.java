package test.wordpress.pages;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import test.wordpress.utilities.BrowserInitiate;
import test.wordpress.utilities.CaptureScreenShot;
import test.wordpress.utilities.ConfigDataProvider;
import test.wordpress.utilities.ExcelDataProvider;

public class BaseClass {
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;

	public Random random;   

	@BeforeSuite
	public void setupSuite() {
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		random = new Random();
	}

	@BeforeClass
	public void setup() {
		driver = BrowserInitiate.startBrowser(driver, config.getBrowserName(), config.getQaURL());
	}


	@AfterClass
	public void tearDown() {
		BrowserInitiate.quiteBrowser(driver);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		String name = result.getName();
		if (result.getStatus() == ITestResult.FAILURE) {
			CaptureScreenShot.getScreenshot(driver, name);
		} else {
			CaptureScreenShot.getScreenshot(driver, name);
		}
	}

}
