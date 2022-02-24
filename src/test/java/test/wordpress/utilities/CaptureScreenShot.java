package test.wordpress.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class CaptureScreenShot {

	public static void getScreenshot(WebDriver driver, String name) {
		String path = "./Screenshots/"+getCurrentDateTime();
		File newFolder = new File(path);
		boolean flag  = newFolder.mkdir();
		if (flag) {
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				FileHandler.copy(src, new File(path+"/"+name+getCurrentDateTime()+".png"));
			} catch (Exception e) {
				System.out.println("unable to take screenshot file "+e.getMessage());
			}
		} else {
			System.out.println("unable to create folder for screenshot ");
		}

	}

	public static String getCurrentDateTime() {
		DateFormat customFormate = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormate.format(currentDate);
	}

}
