package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility{
	private static final By EMAIL_TEXTBOX_LOCATOR = By.id("email");
	private static final By PASSWORD_TEXTBOX_LOCATOR = By.id("passwd");
	private static  final By SUBMIT_LOGIN_BUTTON_LOCATOR=By.id("SubmitLogin");
	private static final By ERROR_MESSAGE_LOCATOR=By.xpath("//div[contains(@class,\"alert-danger\")]/ol/li");    
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	public MyAccountPage doLogin(String email, String password)
	{
		enterText(EMAIL_TEXTBOX_LOCATOR, email);
		enterText(PASSWORD_TEXTBOX_LOCATOR, password);
		clickOn(SUBMIT_LOGIN_BUTTON_LOCATOR);
		MyAccountPage accountPage = new MyAccountPage(getDriver());
		return accountPage;
	}

}
