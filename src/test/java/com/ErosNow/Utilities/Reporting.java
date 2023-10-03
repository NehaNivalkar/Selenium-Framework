package com.ErosNow.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	public ExtentSparkReporter htmlReporter;

	public ExtentReports extent;
	public ExtentTest logger;


	public void onStart(ITestContext testContext)
	{
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "ErosTest-Report-"+timestamp+".html";
				htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Erostest-output/"+repName);
				try {
					htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				extent = new ExtentReports();
				extent.attachReporter(htmlReporter);
				extent.setSystemInfo("Host Name", "localhost");
				extent.setSystemInfo("Environment", "QA");
				extent.setSystemInfo("User", "Neha");
				htmlReporter.config().setDocumentTitle("ErosNow Test Project");
				htmlReporter.config().setReportName("Functional Test Report");
		       // htmlReporter.config().setChartVisibilityOnOpen(true); // Set the chart visibility to true
				htmlReporter.config().setTheme(Theme.STANDARD);		
				
	}




	public void onTestSuccess(ITestResult tr) 
	{
		logger=extent.createTest(tr.getName());
		//logger.info("URL is opening <br>"//
	         //   + "Login with Mobile mobile button is clickable <br>"
	         //   + "Entered Phone Number <br>"
	         //   + "Clicked on Next Button\");                                                                                          ");

		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));


	}

	public void onTestFailure(ITestResult tr) {
	    logger = extent.createTest(tr.getName());
	    logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

	    String screenshotPath1 = System.getProperty("user.dir") + "/Screenshots/" + tr.getName() + ".png";
	    File f = new File(screenshotPath1);
	    if (f.exists()) {
	        logger.fail("Screenshot is below:", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath1).build());
	    }
	}

	 public void onTestSkipped(ITestResult tr)
	 {
		 logger=extent.createTest(tr.getName());
		 logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE ));
	 }
	 public void onFinish(ITestContext testContext)
	 {
		 extent.flush();
	 }
}
