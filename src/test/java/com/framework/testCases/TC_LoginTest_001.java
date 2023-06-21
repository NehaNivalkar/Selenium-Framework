package com.framework.testCases;
import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.framework.pageObjects.LoginPage;




public class TC_LoginTest_001 extends BaseClass {
	
	@Parameters("browser")
	@Test
	public void loginTest() throws IOException
	{
		
		logger.info("URL is opening");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered Username");
		
		lp.setPassword(password);
		logger.info("Entered Password");
		
		lp.clickSubmit();
		
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
	}
	
	
	
}
