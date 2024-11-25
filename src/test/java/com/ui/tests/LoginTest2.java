package com.ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;

public class LoginTest2 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();// Launch a Browser Window..!! Browser Session is created.
		HomePage homePage = new HomePage(driver);
		
		LoginPage loginPage = homePage.goToLoginPage();
		loginPage.doLoginWith("bajoy99768@inikale.com", "Pass123");
	}

}
