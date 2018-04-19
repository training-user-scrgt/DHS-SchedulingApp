package gov.dhs.uscis.odos2.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadProperties {
	private static Logger logger = LoggerFactory.getLogger(LoadProperties.class);
	static private Properties testProperties = null;

	public static String getProperty(String key) {
		logger.info("Looking up key: " + key);
		if (testProperties == null) {
			LoadProperties.loadProperties();
		}
		return testProperties.getProperty(key);
	}

	/**
	 * Read configuration values from the Application.properties and driver.properties file
	 */
	private static void loadProperties() {
		try {
			InputStream inputStream = LoadProperties.class.getClassLoader()
					.getResourceAsStream("config/Application.properties");

			InputStream driverProp = LoadProperties.class.getClassLoader()
					.getResourceAsStream("driver.properties");
			testProperties = new Properties();
			testProperties.load(driverProp);
			testProperties.load(inputStream);
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * Return the application URL to test
	 * 
	 * @return URL stored as system property or from property file
	 */
	public static String getApplicationURL() {
		String webURL = System.getProperty("web.url") == null ? 
				LoadProperties.getProperty("web.url") : System.getProperty("web.url");
		logger.info("Application URL is: " + webURL);
		return webURL;
	}
}
