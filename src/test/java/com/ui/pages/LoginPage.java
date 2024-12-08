package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility {

	private static final By EMAIL_FIELD_LOCATOR = By.xpath("//input[@id='email']");
	private static final By PASSWORD_FIELD_LOCATOR = By.xpath("//input[@id='passwd']");
	private static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//button[@id='SubmitLogin']");
	private static final By ERROR_MESSAGE_LOCATOR= By.xpath("//div[contains(@class,\"alert-danger\")]/ol/li");
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public MyAccountPage doLoginWith(String emailAddress, String password) {
		
		enterText(EMAIL_FIELD_LOCATOR, emailAddress);
		enterText(PASSWORD_FIELD_LOCATOR, password);
		clickOn(SIGN_IN_BUTTON_LOCATOR);
		
		MyAccountPage myAccountPage = new MyAccountPage(getDriver());
		return myAccountPage;
		
	}
	public LoginPage doLoginWithInvalidCredentials(String emailAddress, String password){
		enterText(EMAIL_FIELD_LOCATOR, emailAddress);
		enterText(PASSWORD_FIELD_LOCATOR, password);
		clickOn(SIGN_IN_BUTTON_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
	public String getErrorMessage(){
		return getVisibleText(ERROR_MESSAGE_LOCATOR);
	}

	
}
