 package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JsonUtility;
import com.utility.PropertiesUtil;

public final class HomePage extends BrowserUtility {

	private static final By SIGN_IN_LINK_LOCATOR=By.xpath("//a[contains(text(),'Sign in')]");
	
	public HomePage(WebDriver driver) {
		super(driver); // To Call the Parent Class constructor from the child constructor
		goToWebsite(JsonUtility.readJson(QA).getUrl());
	}
	public HomePage(Browser browserName, boolean isHeadless)
	{
		super(browserName,true);
		goToWebsite(JsonUtility.readJson(QA).getUrl());
	}
	
	public LoginPage goToLoginPage()
	{
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
		
	}
	
}
