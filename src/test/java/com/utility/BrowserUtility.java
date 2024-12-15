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

import com.constants.Browser;

public class BrowserUtility {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(getClass());

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);

	}

	public BrowserUtility(Browser browserName) {
		if (browserName == Browser.CHROME)
			driver.set(new ChromeDriver());
		else if (browserName == Browser.EDGE)
			driver.set(new EdgeDriver());
		else
			logger.error("Please select correct browser");

	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		if (browserName == Browser.CHROME) {
			if (isHeadless) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			} else {
				driver.set(new ChromeDriver());
			}
		} else if (browserName == Browser.EDGE) {
			if (isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--disable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver());
			}
		} else {
			logger.error("Please select correct browser");
		}
	}

	public void goToWebsite(String url) {
		driver.get().get(url);
		maximizeWindow();
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

	public void maximizeWindow() {
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		WebElement element = driver.get().findElement(locator);
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		WebElement element = driver.get().findElement(locator);
		element.sendKeys(textToEnter);
	}

	public String getVisibleText(By locator) {
		WebElement element = driver.get().findElement(locator);
		return element.getText();
	}

	public void quit() {
		driver.get().quit();
	}
	public String takeScreenShot(String testName) {
		WebDriver driver = getDriver(); // Get the WebDriver instance
		if (driver == null) {
			throw new IllegalStateException("WebDriver instance is null. Cannot take a screenshot.");
		}

		if (!(driver instanceof TakesScreenshot)) {
			throw new IllegalStateException("WebDriver does not support TakesScreenshot.");
		}

		File screenshotData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Timestamp for uniqueness
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String screenshotPath = "./screenshots/" + testName + "-" + timestamp + ".png";
		File screenshotFile = new File(screenshotPath);

		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return screenshotPath;
	}
}