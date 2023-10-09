package com.ErosNow.testCases;

import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ErosNow.Utilities.XLUtilities;
import com.ErosNow.pageObjects.LoginStart;

public class Login_DDT extends BaseClass{
	@DataProvider(name="LoginData1")
	String[][] getData() throws IOException
	{
		String path= System.getProperty("user.dir")+"\\src\\test\\java\\com\\ErosNow\\testData\\LoginData1.xlsx";
		
		int rownum=XLUtilities.getRowCount(path, "Sheet1");
		int colcount=XLUtilities.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]=new String[rownum][colcount];
		for(int i=1; i<=rownum;i++)
		{
			for(int j=0; j<colcount;j++)
			{
				logindata[i-1][j]=XLUtilities.getCellData(path,"Sheet1",i,j);
			}
		}
			return logindata;	
	}
	
	@Test(dataProvider="LoginData1")
	public void LoginDDT(String username, String password) throws InterruptedException
	{
		
		  logger.info("Test data received - Username: " + username + ", Password: " + password);
		LoginStart lp = new LoginStart(driver);
		lp.clickletsstart();
		logger.info("Clicked on Lets Start");
		Thread.sleep(1000);
		
		lp.entercred(username);
		logger.info("Username is provided");
		Thread.sleep(1000);
		
		lp.clickcont();
		logger.info("Clicked on continue");
		Thread.sleep(1000);
		
		lp.enterpass(password);
		logger.info("Password is provided");
		Thread.sleep(1000);
		
		lp.clickcont1();
		Thread.sleep(3000);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement userProfile = null;

        try {
            userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/header/ul/li[3]/div[2]/ul/li/div/div[1]/a/i")));
            Thread.sleep(1000);
            logger.info("Is User Profile Displayed (Before Assertion): " + userProfile.isDisplayed());
            try {
                Assert.assertTrue("User profile is not displayed", userProfile.isDisplayed());
            } catch (AssertionError e) {
                logger.error("User profile is not displayed.");
                throw e; // Re-throw the exception to mark the test as failed
            }
            Actions action = new Actions(driver);
            action.moveToElement(userProfile).perform();
        } catch (StaleElementReferenceException e) {
            Thread.sleep(2000);
            logger.info("For the next iteration");
            userProfile = driver.findElement(By.xpath("//*[@id=\"app\"]/header/ul/li[3]/div[2]/ul/li/div/div[1]/a/i"));
            
            Actions action = new Actions(driver);
            action.moveToElement(userProfile).perform();
        }
   
        Thread.sleep(3000);
		lp.clicklogout();
        logger.info("Logged Out Succesfully");
       
}
	

}