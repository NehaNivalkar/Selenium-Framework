package com.ErosNow.testCases;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ErosNow.Utilities.XLUtilities;
import com.ErosNow.Utilities.capturescreen;
import com.ErosNow.pageObjects.HomePage;
import com.ErosNow.pageObjects.LoginStart;
import com.aventstack.extentreports.MediaEntityBuilder;

public class InvalidScenarios extends BaseClass{
	//private static Logger logger = LogManager.getLogger(LetsStart.class);
    @Parameters("browser")
    @Test
    public void testInvalidUsername() throws IOException, InterruptedException {
    	
		  LoginStart lp = new LoginStart(driver);
			HomePage hp = new HomePage(driver);
			 Wait<WebDriver> wait = new FluentWait<>(driver)
			            .withTimeout(Duration.ofSeconds(60)) // Adjust the timeout as needed
			            .pollingEvery(Duration.ofMillis(500)) // Adjust the polling interval as needed
			            .ignoring(org.openqa.selenium.NoSuchElementException.class);
		hp.clickletsstart();
		logger.info("Clicked on Lets Start");
		
		lp.entercred(invalidusername);
		logger.info("Username is provided");
		
		lp.clickcont();
		// Wait for the error message to be present in the DOM
		By errorLocator = By.cssSelector(".error-msg");
		WebElement errorMessageElement = wait.until(ExpectedConditions.presenceOfElementLocated(errorLocator));

		// Get the text of the error message
		String errorMessage = errorMessageElement.getText();
		 
		if (errorMessage.contains("Email is not valid.")) {
		    logger.error("Email is not valid.");
		     capturescreen.captureScreen(driver, "Email provided is not valid");
		}else if (errorMessage.contains("Mobile number is not valid.")) {
		    logger.error("Mobile number is not valid.");
		   // capturescreen.captureScreen(driver, "Invalid Phone Number Format.");
		}else if (errorMessage.contains("Not able to send or validate OTP")) {
		    logger.error("Not able to send or validate OTP");
		   // capturescreen.captureScreen(driver, "Mobile Number provided is not valid");
		}else {
		    logger.info("No error message found");
		}

		driver.navigate().refresh();
	
       
   
}
   /* @Test
    public void testInvalidPassword() throws IOException, InterruptedException {
    	
    	 LoginStart lp = new LoginStart(driver);
			HomePage hp = new HomePage(driver);
			 Wait<WebDriver> wait = new FluentWait<>(driver)
			            .withTimeout(Duration.ofSeconds(60)) // Adjust the timeout as needed
			            .pollingEvery(Duration.ofMillis(500)) // Adjust the polling interval as needed
			            .ignoring(org.openqa.selenium.NoSuchElementException.class);
		hp.clickletsstart();
		logger.info("Clicked on Lets Start");
		
		
		lp.entercred(cred);
		logger.info("Username is provided");
		
		lp.clickcont();
		logger.info("Clicked on continue");
		
		
		lp.enterpass(invalidpassword);
		logger.info("Password is provided");
		
		
		lp.clickcont1();
		
		// Wait for the error message to be present in the DOM
		By errorLocator = By.cssSelector(".error-msg");
		WebElement errorMessageElement = wait.until(ExpectedConditions.presenceOfElementLocated(errorLocator));

		// Get the text of the error message
		String errorMessage = errorMessageElement.getText();
		 
		if (errorMessage.contains("Invalid password")) {
		    logger.error("Invalid password");
		    WebElement viewPasswordIcon = driver.findElement(By.xpath("//*[@id=\"app\"]/section/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/div[2]/i"));
	        viewPasswordIcon.click();
	        logger.info("Clicked on EyeIcon to see the password");
		    capturescreen.captureScreen(driver, "Password is Invalid");
		}else if (errorMessage.contains("password - This field may not be blank")) {
		    logger.error("password - This field may not be blank");
		   // capturescreen.captureScreen(driver, "Password field is blank");
		}else {
		    logger.info("No error message found");
		}

		driver.navigate().refresh();
	
}*/
   
}
