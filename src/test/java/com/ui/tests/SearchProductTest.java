package com.ui.tests;

import com.ui.pages.MyAccountPage;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ui.listerners.TestListener.class)
public class SearchProductTest extends TestBase{

    private MyAccountPage  myAccountPage;
    private static final String SEARCH_TERM = "Printed Summer Dress";

    @BeforeMethod(description = "Valid user logs in to the application")
    public void setup(){
        myAccountPage= homePage.goToLoginPage().doLoginWith("bajoy99768@inikale.com","Pass123");
    }

    @Test(description = "Verify if a logged in user is able to search for a product and correct product search results are displayed",
            groups = {"e2e", "smoke", "sanity"})

    public void verifyProductSearchTest() {
       boolean actualResult =  myAccountPage.searchForProduct(SEARCH_TERM).isSearchTermPresentInProductList(SEARCH_TERM);
        Assert.assertEquals(actualResult,true);
    }


}
