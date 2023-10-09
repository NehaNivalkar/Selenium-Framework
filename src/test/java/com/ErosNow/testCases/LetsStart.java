package com.ErosNow.testCases;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ErosNow.pageObjects.LoginStart;

@Listeners(com.ErosNow.Utilities.Reporting.class)

public class LetsStart extends BaseClass {
	private static Logger logger = LogManager.getLogger(LetsStart.class);
    @Parameters("browser")
    @Test
    public void loginTest() throws IOException, InterruptedException {
 	   LoginStart lp = new LoginStart(driver);

        System.out.println("Log4j properties file path: " + System.getProperty("user.dir") + "log4j2.properties");

        
        logger.info("URL is opening");
        
        Thread.sleep(2000);
        lp.clickletsstart();
        logger.info("Clicked on Let's Start");
        
        lp.entercred(cred);
        logger.info("Phone Number Entered");
        Thread.sleep(2000);
        
        lp.clickcont();
        logger.info("Moved to entered password");
        Thread.sleep(2000);
        
        lp.enterpass(password);
        logger.info("Password Entered");
        Thread.sleep(2000);
        
        lp.clickcont1();
        logger.info("Logged In Succesfully");
        Thread.sleep(2000);
        
        //Validate successfully Login
        String expectedTitle = "Eros Now - Watch & Download over 11,000+ HD Movies, TV Shows, Originals & Songs Online | Eros Now";
        String actualTitle = driver.getTitle();

        if (actualTitle.equals(expectedTitle)) {
            Assert.assertTrue(true);
            logger.info("Correct Title");
        }  else {
            logger.info("Incorrect Title");
            captureScreen(driver, "Incorrect Title");
            Assert.assertTrue("Incorrect Title: ", false);
        }
        
        // Validate user profile or username 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
            
            userProfile = driver.findElement(By.xpath("//*[@id=\"app\"]/header/ul/li[3]/div[2]/ul/li/div/div[1]/a/i"));
            Actions action = new Actions(driver);
            action.moveToElement(userProfile).perform();
        }

       
        lp.clicklogout();
        logger.info("Logged Out Succesfully");
        
        //Validation pending
        //URL, Letsstart button
   
}

}
    
  /* @DataProvider(name = "credentials")
    public Object[][] credentialsProvider() {
        return new Object[][] {
            {"9594505207", "Enter@123"},
            {"6752", ""},
            {"nehanivalkar20", "Enter@123"},
            {"", "Enter@123"},
        };
    } 
    
   
    
    @Test(dataProvider = "credentials")
    public void loginFailureTest(String username, String password) throws IOException, InterruptedException {
       // try {
        	 
            // Your login failure logic here using 'username' and 'password' parameters
            lp.entercred(username);
            lp.clickcont();
            lp.enterpass(password);
            lp.clickcont1(); 
            
            
          /*  WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"app\"]/section/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div"));
            String expectedErrorMessage = "Mobile number is not valid.";
            String actualErrorMessage = errorMessage.getText();
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
            logger.info("Login failure for user: " + username);
        } catch (NoSuchElementException e) {
            // Handle the NoSuchElementException and log it
            logger.error("Element not found: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions and log them
            logger.error("Exception occurred: " + e.getMessage());
        }*/
    
    
    
    

