package com.ErosNow.Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class capturescreen {
	public static Logger logger;
	
	public static void captureScreen(WebDriver driver, String tname) throws IOException {
		try {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + tname + ".png";
	    File target = new File(screenshotPath);
	    FileUtils.copyFile(source, target);
	    logger.info("Screenshot taken and saved at: " + screenshotPath);
	
} catch (IOException e) {
    logger.error("Exception occurred while taking a screenshot: " + e.getMessage());
}
	}
}
