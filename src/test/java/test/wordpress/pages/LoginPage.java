package test.wordpress.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(name="usernameOrEmail") WebElement uname;
	@FindBy(className="form-button") WebElement continueBtn;
	@FindBy(name="password") WebElement pass;


	public void loginToWordPress(String userName, String Password) {
		uname.sendKeys(userName);
		continueBtn.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		pass.sendKeys(Password);
		continueBtn.click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
}
