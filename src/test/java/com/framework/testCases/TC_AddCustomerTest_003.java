package com.framework.testCases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.framework.pageObjects.AddCustomerPage;
import com.framework.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {
	
	@Test
	public void addNewCustomer() throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("UserName for addcustomer is provided");
		lp.setPassword(password);
		logger.info("Password for addcustomer is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
        addcust.ClickAddNewCustomer();

        Thread.sleep(5000);
        
        WebElement frame1 = driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
        driver.switchTo().frame(frame1);
        WebElement frame2 = driver.findElement(By.id("ad_iframe"));
        driver.switchTo().frame(frame2);
        driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
		logger.info("GoogleAd Dismissed");
        driver.switchTo().defaultContent();
        Thread.sleep(5000);


     
		
		logger.info("Providing customer details...");
		addcust.custName("Neha");
		addcust.custgender("Female");
		addcust.custdob("01","19","2000");
		Thread.sleep(3000);
		addcust.custaddress("India");
		addcust.custcity("Mumbai");
		addcust.custstate("Maharashtra");
		addcust.custpinno("400025");
		addcust.custtelephoneno("9594505207");
		
		String email=randomstring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("Enter@123");
		addcust.custsubmit();
		
		Thread.sleep(3000);

		logger.info("Validation Started");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		if(res==true)
		{
		Assert.assertTrue(true);
		logger.info("TestCase for adding new customer passed");
		}
		else {
			logger.info("TestCase for adding new customer failed");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	

