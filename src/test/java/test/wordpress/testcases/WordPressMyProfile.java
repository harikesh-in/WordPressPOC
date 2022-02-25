package test.wordpress.testcases;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import test.wordpress.pages.BaseClass;
import test.wordpress.pages.LoginPage;
import test.wordpress.pages.MyProfilePage;

public class WordPressMyProfile extends BaseClass{

	@Test(priority = 0)
	public void loginApplication() {
		System.out.println(driver.getTitle());
		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		MyProfilePage myProfile = PageFactory.initElements(driver, MyProfilePage.class);
		String userName = excel.getStringCellData("Login", 1, 0);
		String Password = excel.getStringCellData("Login", 1, 1);
		loginpage.loginToWordPress(userName, Password);
		String titleName = excel.getStringCellData("MyProfileData", 1, 0);
		boolean flag = myProfile.verifyTitle(titleName);
		assertEquals(flag, true, "User logged in and able to see My Profile");
	}

	@Test(priority = 1, dependsOnMethods = "loginApplication")
	public void update_ProfileInfo() {
		MyProfilePage myProfile = PageFactory.initElements(driver, MyProfilePage.class);
		String firstName = excel.getStringCellData("MyProfileData", 1, 1) + random.nextInt(100);
		String lastName = excel.getStringCellData("MyProfileData", 1, 2) + random.nextInt(100);
		String displayName = excel.getStringCellData("MyProfileData", 1, 3) + random.nextInt(1000);
		myProfile.updateFirstName(firstName);
		myProfile.updateLastName(lastName);
		myProfile.updateDisplayName(displayName);
		myProfile.saveProfile();
		String msg = myProfile.getSuccessMsg();
		assertEquals(msg, "Settings saved successfully!", "User Settings saved successfully on My Profile");
	}

	@Test(priority = 2, dependsOnMethods = "loginApplication")
	public void update_AboutMe() {
		MyProfilePage myProfile = PageFactory.initElements(driver, MyProfilePage.class);
		String aboutMe = excel.getStringCellData("MyProfileData", 1, 4) + random.nextInt(10000000);
		myProfile.updateAboutMe(aboutMe);
		myProfile.saveProfile();
		String msg = myProfile.getSuccessMsg();
		assertEquals(msg, "Settings saved successfully!", "User Settings saved successfully on My Profile");
	}

	@Test(priority = 3, dependsOnMethods = "loginApplication")
	public void additonOfURL() {
		MyProfilePage myProfile = PageFactory.initElements(driver, MyProfilePage.class);
		int preCount = myProfile.getSiteCount();
		String URLName = excel.getStringCellData("MyProfileData", 1, 5) + random.nextInt(100) + ".com";
		String URLdescription = excel.getStringCellData("MyProfileData", 1, 6) + random.nextInt(100000);
		myProfile.addSiteURL();
		myProfile.enterSiteURL(URLName, URLdescription);
		myProfile.saveSiteURL();
		int postCount = myProfile.getSiteCount();
		assertEquals(preCount+1, postCount, "User saved URL details successfully on My Profile");
	}


	@Test(priority = 4, dependsOnMethods = "loginApplication")
	public void deletionOfURL() {
		MyProfilePage myProfile = PageFactory.initElements(driver, MyProfilePage.class);
		int allCount = myProfile.getSiteCount();
		for (int i = allCount-1; i >= 0; i--) {
			myProfile.deleteSiteByIndex(i);
		}
		int postCount = myProfile.getSiteCount();
		assertEquals(postCount, 0, "User deleted URL details successfully on My Profile");
	}



	@Test(priority = 5, dependsOnMethods = "loginApplication")
	public void showGravatarProfile() {
		MyProfilePage myProfile = PageFactory.initElements(driver, MyProfilePage.class);
		boolean status = myProfile.gravatarStatus();
		if (!status) {
			myProfile.toggleGravatar();
			myProfile.saveProfile();
			String msg = myProfile.getSuccessMsg();
			assertEquals(msg, "Settings saved successfully!", "User Settings saved successfully on My Profile");
		}
		status = myProfile.gravatarStatus();
		assertEquals(status, true, "User gravatar profile is on successfully");
	}

	@Test(priority = 6, dependsOnMethods = "loginApplication")
	public void hideGravatarProfile() {
		MyProfilePage myProfile = PageFactory.initElements(driver, MyProfilePage.class);
		boolean status = myProfile.gravatarStatus();
		if (status) {
			myProfile.toggleGravatar();
			myProfile.saveProfile();
			String msg = myProfile.getSuccessMsg();
			assertEquals(msg, "Settings saved successfully!", "User Settings saved successfully on My Profile");
		}
		status = myProfile.gravatarStatus();
		assertEquals(status, false, "User gravatar profile is on successfully");
	}


	@Test(priority = 7, dependsOnMethods = "loginApplication")
	public void cancelAdditonOfURL() {
		MyProfilePage myProfile = PageFactory.initElements(driver, MyProfilePage.class);
		int preCount = myProfile.getSiteCount();
		String URLName = excel.getStringCellData("MyProfileData", 1, 5) + random.nextInt(100) + ".com";
		String URLdescription = excel.getStringCellData("MyProfileData", 1, 6) + random.nextInt(100000);
		myProfile.addSiteURL();
		myProfile.enterSiteURL(URLName, URLdescription);
		myProfile.clickCancel();
		int postCount = myProfile.getSiteCount();
		assertEquals(preCount, postCount, "User canceled URL details successfully on My Profile");
	}

	@Test(priority = 8, dependsOnMethods = "loginApplication")
	public void cancelSiteCreation() {
		MyProfilePage myProfile = PageFactory.initElements(driver, MyProfilePage.class);
		myProfile.addWordPressSite();
		myProfile.clickCancel();
		String title = driver.getTitle();
		System.out.println("title2 "+ title);
		assertEquals(title, "My Profile — WordPress.com", "User canceled site created successfully on My Profile");

	}

	@Test(priority = 9, dependsOnMethods = "loginApplication")
	public void siteCreation() {
		MyProfilePage myProfile = PageFactory.initElements(driver, MyProfilePage.class);
		myProfile.addWordPressSite();
		myProfile.gotoWordPressSite();
		String title = driver.getTitle();
		System.out.println("title1 "+ title);
		assertEquals(title, "WordPress.com", "User site created successfully on My Profile");
	}


}
