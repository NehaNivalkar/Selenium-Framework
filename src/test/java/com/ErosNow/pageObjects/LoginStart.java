package com.ErosNow.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class LoginStart {
	WebDriver ldriver;
	public LoginStart(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	 private Wait<WebDriver> wait;

	    public void initializeWait() {
	        wait = new FluentWait<>(ldriver)
	            .withTimeout(Duration.ofSeconds(60))
	            .pollingEvery(Duration.ofMillis(500))
	            .ignoring(org.openqa.selenium.NoSuchElementException.class);
	    }
       
	/*@FindBy(xpath="//body/div[@id='app']/section[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")
	@CacheLookup
	WebElement entercrd;
	
	@FindBy(xpath="//body/div[@id='app']/section[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")
	@CacheLookup
	WebElement invalidusername;
	
	@FindBy(xpath="//*[@id=\"app\"]/section/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/div[2]/input")
	@CacheLookup
	WebElement enterpass;
	
	@FindBy(xpath="//*[@id=\"app\"]/section/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/div[2]/input")
	@CacheLookup
	WebElement invalidpassword;
	
	@FindBy(xpath="//button[contains(text(),'Continue')]")
	@CacheLookup
	WebElement cont;
	
	@FindBy(xpath="//*[@id=\"app\"]/section/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/button")
	@CacheLookup
	WebElement cont1;*/
	
	
	 public void entercred(String cred) {
	        initializeWait();
	        WebElement entercrd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[@id='app']/section[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
	        entercrd.sendKeys(cred);
	    }
	
	
	 public void enterpass(String password) {
	        initializeWait();
	        WebElement enterpass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/section/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/div[2]/input")));
	        enterpass.sendKeys(password);
	    }
	
	public void invalidusername(String invalidusername)
	{
		initializeWait();
        WebElement entercrd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[@id='app']/section[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")));
		entercrd.sendKeys(invalidusername);
	}
	
	public void invalidpassword(String invalidpassword)
	{
		initializeWait();
        WebElement enterpass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\\\"app\\\"]/section/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/div[2]/input")));
		enterpass.sendKeys(invalidpassword);
	}
	
	 public void clickcont() {
	        initializeWait();
	        WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
	        cont.click();
	    }
	
	
	public void clickcont1()
	{
		initializeWait();
        WebElement cont1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\\\"app\\\"]/section/div[2]/div/div/div[2]/div/div[2]/div/div/div[2]/button")));
		cont1.click();
	}
    
	
	
	
	
}
