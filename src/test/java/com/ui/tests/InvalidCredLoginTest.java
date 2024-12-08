package com.ui.tests;

import com.ui.pojos.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Listeners(com.ui.listerners.TestListener.class)
public class InvalidCredLoginTest extends TestBase {

	  Logger logger = LoggerUtility.getLogger(this.getClass());
	  private static final String INVALID_EMAIL_ADDRESS ="abc@gmail.com";
	  private static final String INVALID_PASSWORD = "abc@123";


  @Test(description =
	  "To Verify if the proper error message is shown for invalid credentials", groups
	  = {"e2e", "sanity", "smoke" })
  public void loginTest(){
	  assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS,INVALID_PASSWORD).getErrorMessage(),"Authentication failed.");
	  
	  

	
	
}}
