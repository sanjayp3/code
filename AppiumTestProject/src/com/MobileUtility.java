package com;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MobileUtility {

	public static WebDriver createWebDriver(String deviceType)
			throws MalformedURLException {

		WebDriver driver = null;

		if ("iOS".equalsIgnoreCase(deviceType)) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			 capabilities.setCapability("deviceName", "iPhone");
		        capabilities.setCapability("platformName", "iOS");
		        capabilities.setCapability("platformVersion", "7.1");
		        //capabilities.setCapability("browserName", "safari");
			driver = new RemoteWebDriver(
					new URL("http://localhost:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} else if ("android".equalsIgnoreCase(deviceType)) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("device", "android");
			capabilities.setCapability("version", "4.x");
			capabilities.setCapability("deviceType", "phone");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", "phone");
			driver = new RemoteWebDriver(
					new URL("http://localhost:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		return driver;
	}

	public Properties loadProperties() {
		Properties prop = new Properties();
		String propFileName = "ios.properties";

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void horizontalSwipe(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		swipeObject.put("startX", 0.95);
		swipeObject.put("startY", 0.5);
		swipeObject.put("endX", 0.05);
		swipeObject.put("endY", 0.5);
		swipeObject.put("duration", 1.8);
		js.executeScript("mobile: swipe", swipeObject);
	}

	public static void verticalSwipe(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		swipeObject.put("startX", 0.95);
		swipeObject.put("startY", 0.05);
		swipeObject.put("endX", 0.95);
		swipeObject.put("endY", 1.00);
		swipeObject.put("duration", 1.8);
		js.executeScript("mobile: swipe", swipeObject);
	}

	

}
