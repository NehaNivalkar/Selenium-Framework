package com.ErosNow.testCases;

import java.io.IOException;


import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ErosNow.Utilities.XLUtilities;
import com.ErosNow.Utilities.capturescreen;
import com.ErosNow.pageObjects.LoginStart;
import com.ErosNow.pageObjects.HomePage;

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
	public void LoginDDT(String username, String password) throws InterruptedException, IOException
	{
		
		  logger.info("Test data received - Username: " + username + ", Password: " + password);
		  LoginStart lp = new LoginStart(driver);
			HomePage hp = new HomePage(driver);
			 Wait<WebDriver> wait = new FluentWait<>(driver)
			            .withTimeout(Duration.ofSeconds(60)) // Adjust the timeout as needed
			            .pollingEvery(Duration.ofMillis(500)) // Adjust the polling interval as needed
			            .ignoring(org.openqa.selenium.NoSuchElementException.class);
		hp.clickletsstart();
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
		
		String expectedTitle = "Eros Now - Watch & Download over 11,000+ HD Movies, TV Shows, Originals & Songs Online | Eros Now";
        String actualTitle = driver.getTitle();

        if (actualTitle.equals(expectedTitle)) {
            Assert.assertTrue(true);
            logger.info("Correct Title");
        }  else {
            logger.info("Incorrect Title");
            capturescreen.captureScreen(driver, "Incorrect Title");
            Assert.assertTrue("Incorrect Title: ", false);
        }
        

        // Validate user profile or username 
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement userProfile = null;

        try {
            userProfile = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/header/ul/li[3]/div[2]/ul/li/div/div[1]/a/i")));
            Thread.sleep(1000);
            
            try {
                Assert.assertTrue("User profile is not displayed", userProfile.isDisplayed());
            } catch (AssertionError e) {
                logger.error("User profile is not displayed.");
                throw e; // Re-throw the exception to mark the test as failed
            }
            Actions action = new Actions(driver);
            action.moveToElement(userProfile).perform();
        } catch (StaleElementReferenceException e) {
            
            userProfile = driver.findElement(By.xpath("//*[@id=\"app\"]/header/ul/li[3]/div[2]/ul/li/div/div[1]/a/i"));
            Actions action = new Actions(driver);
            action.moveToElement(userProfile).perform();
        }

   
        logout();
        
        
}

	private void logout() {
	    HomePage hp = new HomePage(driver);
	   
	    hp.clicklogout();
	    logger.info("Logged Out Successfully");
	    //Validate logout
	    // Wait for the URL to match
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    boolean isURLMatching=wait.until(ExpectedConditions.urlToBe("https://erosnow.com/"));
	    if (isURLMatching) {
	        System.out.println("URL matches the expected URL: https://erosnow.com/");
	        logger.info("URL matches the expected URL: https://erosnow.com/");
	    } else {
	        System.out.println("URL does not match the expected URL.");
	        logger.error("URL does not match the expected URL.");
	    }
	    // Wait for the "Let’s Start" button to be visible
	    WebElement letsStartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Let’s Start')]")));
	    
	    if (letsStartButton.isDisplayed()) {
	        logger.info("Let's Start button is visible after logout");
	    } else {
	        logger.error("Let's Start button is not visible after logout");
	        Assert.fail("Let's Start button is not visible after logout");
	    }
	   
	}
      
}
	