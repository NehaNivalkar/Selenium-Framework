package com.ErosNow.testCases;

import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ErosNow.pageObjects.LoginStart;

@Listeners(com.ErosNow.Utilities.Reporting.class)

public class LetsStart extends BaseClass {
	private static Logger logger = Logger.getLogger(LetsStart.class);

    @Parameters("browser")
    @Test
    public void loginTest() throws IOException, InterruptedException {
        LoginStart lp = new LoginStart(driver);
        System.out.println("Log4j properties file path: " + System.getProperty("user.dir") + "log4j.properties");

        PropertyConfigurator.configure("log4j.properties"); 
        
        logger.info("URL is opening");
        
        Thread.sleep(2000);
        lp.clickletsstart();
        logger.info("Clicked on Let's Start");
        
        lp.entercred(cred);
        logger.info("Phone Number Entered");
        Thread.sleep(2000);
        
        lp.clickcont();
        logger.info("Moved to entered password");
        
        lp.enterpass(password);
        logger.info("Password Entered");
        Thread.sleep(2000);
        
        lp.clickcont();
        logger.info("Logged In Succesfully");
        
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
        WebElement userProfile = driver.findElement(By.xpath("//*[@id=\"app\"]/header/ul/li[3]/div[2]/ul/li/div/div[1]/a/i"));
        wait.until(ExpectedConditions.visibilityOf(userProfile));
        Assert.assertTrue("User profile is not displayed", userProfile.isDisplayed());
        Actions action = new Actions(driver);
        action.moveToElement(userProfile).perform();
       
        Thread.sleep(3000);
        
        lp.clicklogout();
        logger.info("Logged Out Succesfully");
    }
}
