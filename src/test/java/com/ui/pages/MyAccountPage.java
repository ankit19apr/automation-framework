package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;
import org.openqa.selenium.WebElement;

public final class MyAccountPage extends BrowserUtility {

	private static final By USER_NAME_LOCATOR = By.xpath("//a[@title=\"View my customer account\"]/span");
	private static final By SEARCH_TEXT_BOX_LOCATOR = By.id("search_query_top");
	private static final By ADD_NEW_ADDRESS_LINK_LOCATOR = By.xpath("//a[@title=\"Add my first address\"]");
	private static final By MY_ADDRESSES_LINK_LOCATOR = By.xpath("//a[@title='Addresses']");

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public String getUserName() {
		return getVisibleText(USER_NAME_LOCATOR);
	}

	public SearchResultPage searchForProduct(String productName){
		enterText(SEARCH_TEXT_BOX_LOCATOR, productName);
		enterSpecialKey(SEARCH_TEXT_BOX_LOCATOR, Keys.ENTER);
		SearchResultPage searchResultPage =  new SearchResultPage(getDriver());
		return searchResultPage;
	}

	public AddNewAddressPage goToAddAddressPage() {
		AddNewAddressPage addNewAddressPage = new AddNewAddressPage(getDriver());

		if (isElementPresent(ADD_NEW_ADDRESS_LINK_LOCATOR)) {
			clickOn(ADD_NEW_ADDRESS_LINK_LOCATOR);

		} else  {
			clickOn(MY_ADDRESSES_LINK_LOCATOR);
			SavedAddressPage savedAddressPage = new SavedAddressPage(getDriver());
			savedAddressPage.goToAddNewAddressPageSavedAddress();
		}
		return addNewAddressPage;

	}


}


