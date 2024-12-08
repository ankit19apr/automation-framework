package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SavedAddressPage extends BrowserUtility {

    private static final By ADD_NEW_ADDRESS_LINK_LOCATOR_SAVED_ADDRESS_PAGE = By.xpath("//a[@title='Add an address']");

    public SavedAddressPage(WebDriver driver) {
        super(driver);
    }

    public void goToAddNewAddressPageSavedAddress(){
        clickOn(ADD_NEW_ADDRESS_LINK_LOCATOR_SAVED_ADDRESS_PAGE);

    }
}
