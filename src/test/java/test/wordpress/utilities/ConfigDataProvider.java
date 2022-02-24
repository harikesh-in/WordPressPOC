package test.wordpress.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	Properties pro;
	public ConfigDataProvider() {

		File src = new File("./Config/config.properties");
		try {

			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);

		} catch (Exception e) {
			System.out.println("unable to load config file "+e.getMessage());
		}

	}

	public String getValueForKey(String keyname) {
		return pro.getProperty(keyname);
	}

	public String getBrowserName() {
		return pro.getProperty("Browser");
	}


	public String getQaURL() {
		return pro.getProperty("qaURL");
	}
}
