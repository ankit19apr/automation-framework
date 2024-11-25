package com.ui.tests;

import static org.testng.Assert.*;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojos.User;
import com.utility.LoggerUtility;


@Listeners(com.ui.listerners.TestListener.class)
public class LoginTest extends TestBase {

	  Logger logger = LoggerUtility.getLogger(this.getClass());
	
	

	
	  @Test(description =
	  "To Verify valid user is able to login into application successfully", groups
	  = {"e2e", "sanity"}, dataProviderClass =
	  com.ui.dataprovider.LoginDataProvider.class, dataProvider =
	  "LoginTestDataProvider") public void loginTest(User user){
	  
	  
//	 Test Method 1. Test Script small 2. You can not have conditional statements,
//	  loops, try-catch in your test methods TestScripts --> Should only follow the
//	  Test Steps (There should not be any logic) 3. Reduce the use of local
//	 Variables. 4. You should have atleast one assertion.
	  
	 
	
	  assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(),
	  user.getPassword()).getUserName(), "Ankit Sharma");
	  
	  }
	 

	@Test(description = "To Verify valid user is able to login into application successfully", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataprovider.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")//, retryAnalyzer = com.ui.listerners.MyRetryAnalyzer.class
	public void loginExcelTest(User user) {
		
		
		
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Ankit Sharma");
		
	}
	
	
}
