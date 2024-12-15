package com.ui.tests;
import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners({com.ui.listners.TestListner.class})
public class LoginTest extends TestBase{
	
	@Test(description="Verify if valid user is able to login",groups= {"end to end","sanity"},dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,dataProvider = "LoginTestDataProvider",retryAnalyzer = com.ui.listners.RetryAnalyzer.class)
	public void loginTestJson(User user) {
	

	assertEquals(homePage.goToLoginPage().doLogin(user.getEmailAddress(), user.getPassword()).getUserName(),"Ayush Adhikari");

	}
	@Test(description="Verify if valid user is able to login",groups= {"end to end","sanity"},dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,dataProvider = "LoginCSVDataProvider",retryAnalyzer = com.ui.listners.RetryAnalyzer.class)
	public void loginTestCSV(User user) {
	
	assertEquals(homePage.goToLoginPage().doLogin(user.getEmailAddress(), user.getPassword()).getUserName(),"Ayush Adhikari");
	}
	
	@Test(description="Verify if valid user is able to login",groups= {"end to end","sanity"},dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,dataProvider = "LoginExcelDataProvider")
	public void loginTestExcel(User user) {
	
	assertEquals(homePage.goToLoginPage().doLogin(user.getEmailAddress(), user.getPassword()).getUserName(),"Ayush Adhikari");
	}
}
