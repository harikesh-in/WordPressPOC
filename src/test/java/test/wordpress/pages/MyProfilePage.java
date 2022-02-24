package test.wordpress.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage {

	WebDriver driver;

	public MyProfilePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(className="formatted-header__title") WebElement ProfileTitle;
	@FindBy(name="first_name") WebElement firstname;
	@FindBy(name="last_name") WebElement lastname;
	@FindBy(name="display_name") WebElement display_name;
	@FindBy(className="form-button") WebElement savebtn;
	@FindBy(className="notice__text") WebElement successMsg;
	@FindBy(className="notice__dismiss") WebElement cancelSuccessMsg;
	@FindBy(name="description") WebElement description;
	@FindBy(id="inspector-toggle-control-0") WebElement inspector;
	@FindBy(xpath ="*//button[@class='button is-compact']") WebElement addBtn;
	@FindBy(xpath="*//button[text()='Add URL']") WebElement addURL;
	@FindBy(xpath="*//button[text()='Add WordPress Site']") WebElement addSite;
	@FindBy(xpath="*//input[@name='value']") WebElement enterURL;
	@FindBy(xpath="*//input[@name='title']") WebElement enterURLDescription;
	@FindBy(xpath="*//button[text()='Add Site']") WebElement addURLBtn;
	@FindBy(xpath="*//button[text()='Cancel']") WebElement Cancelbtn;
	@FindBy(xpath="*//button[text()='Create Site']") WebElement createSiteBtn;
	@FindBy(xpath="*//li[@class='profile-link']") List<WebElement> linkList;

	public boolean verifyTitle(String Title) {
		String name = ProfileTitle.getText();
		if (name.equalsIgnoreCase(Title)) {
			return true;
		} else {
			return false;
		}
	}

	public void updateFirstName(String name) {
		firstname.clear();
		firstname.sendKeys(name);
	}

	public void updateLastName(String Lastname) {
		lastname.clear();
		lastname.sendKeys(Lastname);
	}


	public void updateDisplayName(String name) {
		display_name.clear();
		display_name.sendKeys(name);
	}

	public void updateAboutMe(String aboutme) {
		description.clear();
		description.sendKeys(aboutme);
	}

	public void saveProfile() {
		savebtn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addSiteURL() {
		addBtn.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		addURL.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void enterSiteURL(String urlLink, String urlDescription) {
		enterURL.sendKeys(urlLink);
		enterURLDescription.sendKeys(urlDescription);
	}

	public void saveSiteURL() {
		try {
			Thread.sleep(2000);
			addURLBtn.click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getSuccessMsg() {
		String message = successMsg.getText();
		cancelSuccess();
		return message;
	}

	public void cancelSuccess() {
		cancelSuccessMsg.click();
	}

	public void clickCancel() {
		Cancelbtn.click();
	}

	public void addWordPressSite() {
		addBtn.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		addSite.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void gotoWordPressSite() {
		createSiteBtn.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public int getSiteCount() {
		return linkList.size(); 
	}

	public void deleteSiteByIndex(int index) {
		int count = linkList.size();
		if (count>index) {
			linkList.get(index).findElement(By.tagName("button")).click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
