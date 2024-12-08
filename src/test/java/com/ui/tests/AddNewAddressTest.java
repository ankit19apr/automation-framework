package com.ui.tests;

import com.ui.pages.AddNewAddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojos.AddressPojo;
import com.utility.FakeAddressUtility;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class AddNewAddressTest extends TestBase{
    private MyAccountPage myAccountPage;
    private AddressPojo addressPojo;
    private AddNewAddressPage addNewAddressPage;

    @BeforeMethod(description = "Valid user logs in to the application")
    public void setup(){
       myAccountPage = homePage.goToLoginPage().doLoginWith("bajoy99768@inikale.com", "Pass123");
       addressPojo = FakeAddressUtility.getFakeAddress();
    }



    @Test

    public void addNewAddress(){

       myAccountPage.goToAddAddressPage().saveAddress(addressPojo);
    }


}
