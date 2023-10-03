package com.ErosNow.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.ErosNow.Utilities.ReadConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class BaseClass {
	
	 ReadConfig readconfig = new ReadConfig();

	    public String baseURL = readconfig.getApplicationURL();
	    public String cred = readconfig.entercred();
	    public String password = readconfig.enterpass();
	    public static WebDriver driver;
	    public static ExtentReports extent;
	    public static ExtentTest test;
	    public static Logger logger;
	    
	    @Parameters("browser")
	    @BeforeClass
	    public void setup(String br) {
	    	logger = Logger.getLogger("ErosNow");
	        PropertyConfigurator.configure("C:\\Users\\Neha\\eclipse-workspace\\ErosNow\\log4j.properties");
	        System.out.println("Current Directory: " + System.getProperty("user.dir"));

	        extent = new ExtentReports();
	        test = extent.createTest(getClass().getSimpleName()); // Create the extent test with the class name

	        if (br.equals("chrome")) {
	            System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
	            System.setProperty("webdriver.chrome.logfile", System.getProperty("user.dir") + "/Logs/chromedriver.log");
	            System.setProperty("webdriver.chrome.verboseLogging", "true");
	            System.setProperty("webdriver.chrome.silentOutput", "true");

	            
	            driver = new ChromeDriver();
	        } else if (br.equals("firefox")) {
	            System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
	            FirefoxOptions options = new FirefoxOptions();
	            options.setBinary(readconfig.getFirefoxBinaryPath());

	            driver = new FirefoxDriver(options);
	        }
	        System.setProperty("webdriver.chrome.silentOutput", "true");

	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get(baseURL);
	    }


@AfterMethod
public void tearDown() {
    driver.quit();
}

public void captureScreen(WebDriver driver, String tname) throws IOException {
    TakesScreenshot ts = (TakesScreenshot) driver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + tname + ".png";
    File target = new File(screenshotPath);
    FileUtils.copyFile(source, target);
    logger.info("Screenshot taken and saved at: " + screenshotPath);
}
}
