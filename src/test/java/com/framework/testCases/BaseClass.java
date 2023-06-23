package com.framework.testCases;

import java.io.File;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.framework.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL=	readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		logger = Logger.getLogger("seliumframework");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome"))
		{
	    System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver = new ChromeDriver();
		}
		else if (br.equals("firefox"))
		{
		    System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
		    FirefoxOptions options = new FirefoxOptions();
            options.setBinary(readconfig.getFirefoxBinaryPath());

            driver = new FirefoxDriver(options);
		}

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);

		
	}
	
	@AfterClass
	public void tearDown() 
	{
		driver.quit();
	}
	public void captureScreen(WebDriver driver, String tname) throws IOException {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + tname + ".png";
	    File target = new File(screenshotPath);
	    FileUtils.copyFile(source, target);
	    System.out.println("Screenshot taken and saved at: " + screenshotPath);
	    logger.info("Screenshot taken and saved at: " + screenshotPath);
	}

	
	public String randomstring()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return(generatedstring);
	}
	
	public String randomNum()
	{
		String generatedstring2 = RandomStringUtils.randomNumeric(5);
		return(generatedstring2);
	}

		
}

