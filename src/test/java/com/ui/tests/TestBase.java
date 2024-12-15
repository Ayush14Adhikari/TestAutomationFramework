package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {
	protected HomePage homePage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;

	
	@BeforeMethod(description = "Load the Homepage of the website")
	public void setup(
	        @Optional("chrome") String browser,
	        @Optional("false") boolean isLambdaTest,
	        @Optional("false") boolean isHeadless,
	        ITestResult result) {
		System.out.println(browser);
	    this.isLambdaTest = isLambdaTest;
	    WebDriver lambdaDriver;
	    logger.info("Test setup started. Is LambdaTest: " + isLambdaTest);

	    if (isLambdaTest) {
	        lambdaDriver = LambdaTestUtility.intializeLambdaTest(browser, result.getMethod().getMethodName());
	        if (lambdaDriver == null) {
	            throw new IllegalStateException("LambdaTest WebDriver is not initialized.");
	        }
	        homePage = new HomePage(lambdaDriver);
	    } else {
	        logger.info("Running tests locally...");
	        System.out.println(Browser.valueOf(browser.toUpperCase()));
	        homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
	    }

	    if (homePage == null) {
	        throw new IllegalStateException("HomePage initialization failed.");
	    }
	    logger.info("Test setup completed successfully.");
	}

	public BrowserUtility getInstance() {
		return homePage;
	}

	@AfterMethod(description = "Tear Down the browser")
	public void tearDown() {

		if (isLambdaTest) {
			LambdaTestUtility.quitSession(); // quit or close the browsersession on LT
		} else {
			homePage.quit(); // local
		}
	}

}
