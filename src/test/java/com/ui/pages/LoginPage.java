package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility {

	private static final By EMAIL_FIELD_LOCATOR = By.xpath("//input[@id='email']");
	private static final By PASSWORD_FIELD_LOCATOR = By.xpath("//input[@id='passwd']");
	private static final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//button[@id='SubmitLogin']");

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

	public byte[] doLoginWith(String emailAddress, String password, String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
