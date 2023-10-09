package com.ErosNow.pageObjects;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginStart {
	WebDriver ldriver;
	public LoginStart(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//button[contains(text(),'Let’s Start')]")
	@CacheLookup
	WebElement letstart;

	
	@FindBy(xpath="//body/div[@id='app']/section[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")
	@CacheLookup
	WebElement entercrd;
	
	@FindBy(xpath="//*[@id=\"app\"]/section/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/div[2]/input")
	@CacheLookup
	WebElement enterpass;
	
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	@CacheLookup
	WebElement cont;
	
	@FindBy(xpath="//*[@id=\"app\"]/section/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/button")
	@CacheLookup
	WebElement cont1;
	
	
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
	
	public void entercred(String cred)
	{
		entercrd.sendKeys(cred);
	}
	
	public void enterpass(String password)
	{
		enterpass.sendKeys(password);
	}
	
	public void clickcont()
	{
		cont.click();
	}
	
	public void clickcont1()
	{
		cont1.click();
	}
    
	public void clicklogout() {
		logout.click();
	}
	
	
	
}
