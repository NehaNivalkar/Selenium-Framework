package com.ErosNow.pageObjects;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver ldriver;
	public HomePage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	@FindBy(xpath="//button[contains(text(),'Let’s Start')]")
	@CacheLookup
	WebElement letstart;
	
	@FindBy(xpath="//span[contains(text(),'Logout')]")
	@CacheLookup
	WebElement logout;
	
	

	 public void clickletsstart() {
	        int attempts = 0;
	        while (attempts < 2) { // You can adjust the number of attempts as needed
	            try {
	                letstart.click();
	                break; // If successful, exit the loop
	            } catch (StaleElementReferenceException e) {
	                // Element is stale, retry
	            }
	            attempts++;
	        }
	    }
	 
	 public void clicklogout() {
			logout.click();
		}
}
