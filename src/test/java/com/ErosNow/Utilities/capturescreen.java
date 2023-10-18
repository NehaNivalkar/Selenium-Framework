package com.ErosNow.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class capturescreen {

	 private static final Logger logger = LogManager.getLogger(capturescreen.class);
	 public static String captureScreen(WebDriver driver, String tname) throws IOException {
		    TakesScreenshot ts = (TakesScreenshot) driver;
		    File source = ts.getScreenshotAs(OutputType.FILE);
		    String screenshotPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator  +tname+ ".png";
		    File target = new File(screenshotPath);
		    FileUtils.copyFile(source, target);
		    logger.info("Screenshot taken and saved at: " + screenshotPath);
			return screenshotPath;
		}
	}