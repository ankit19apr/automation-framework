package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver); // Initialize the instance variable driver
	}

	/*
	 * public BrowserUtility(String browserName) {
	 * 
	 * if(browserName.equalsIgnoreCase("chrome")) { driver = new ChromeDriver(); }
	 * else if (browserName.equalsIgnoreCase("edge")) { driver = new EdgeDriver(); }
	 * else {
	 * System.err.println("Invalid Browser Name.. Please select Chrome or Edge"); }
	 * }
	 */
	/*public BrowserUtility(Browser browserName) {
		logger.info("Launching Browser for " + browserName);
		if (browserName == Browser.CHROME) {

			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {

			driver.set(new EdgeDriver());
		} else if (browserName == Browser.FIREFOX) {

			driver.set(new FirefoxDriver());
		} else {
			logger.error("Invalid Browser Name... Please select valid option");
			System.err.println("Invalid Browser Name... Please select valid option");
		}
	}*/

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching Browser for " + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless=old"); // headless mode launch of browser
				chromeOptions.addArguments("--window-size=1920,1800");
				driver.set(new ChromeDriver(chromeOptions));
			} else {
				driver.set(new ChromeDriver());

			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--headless=old"); // headless mode launch of browser
				edgeOptions.addArguments("--window-size=1920,1800");
				driver.set(new EdgeDriver(edgeOptions));
			} else {
				driver.set(new EdgeDriver());

			}
		} else if (browserName == Browser.FIREFOX) {
			if (isHeadless) {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--headless=old"); // headless mode launch of browser
				firefoxOptions.addArguments("--window-size=1920,1800");
				driver.set(new FirefoxDriver(firefoxOptions));
			} else {
				driver.set(new FirefoxDriver());
			}

		}
	}

	public void goToWebsite(String url) {
		logger.info("Visiting the website.." + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the browser window");

		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		logger.info("Finding element with the locator" + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and performing the click");

		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding element with the locator" + locator);

		WebElement element = driver.get().findElement(locator);
		logger.info("Element found and now enter text" + textToEnter);

		element.sendKeys(textToEnter);

	}

	public String getVisibleText(By locator) {

		logger.info("Finding element with the locator" + locator);

		WebElement element = driver.get().findElement(locator);

		logger.info("Element found and returing the visible test" + element.getText());

		return element.getText();
	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();

		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);

		String path =  "./screenshots/" + name + "  -  " + timeStamp + ".png";

		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}

}
