package com.ui.listerners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReportUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {

	Logger logger = LoggerUtility.getLogger(this.getClass());

	ExtentSparkReporter extentSparkReporter;// Provide the functionality to create the HTML file(look and styling)
	ExtentReports extentReports; // it does the heavy lifting, means which of the data need to be dumped in HTML
									// report
	ExtentTest extentTest; // to store the information about test that which test started, completed and
							// status of test whether passed, failed, skipped

	public void onTestStart(ITestResult result) {
		// the moment when test is started
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtentReportUtility.createExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// when test pass
		logger.info(result.getMethod().getMethodName() + "" + "PASSED");
		ExtentReportUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
	}

	public void onTestFailure(ITestResult result) {
		// When Test Failed
		logger.error(result.getMethod().getMethodName() + "" + "FAILED");
		logger.error(result.getThrowable().getMessage());
		ExtentReportUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
		ExtentReportUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());
	
		Object testclass = result.getInstance();
		BrowserUtility browserUtility = ((TestBase)testclass).getInstance();
		logger.info("Capturing Screenshot for the failed tests");
		
		String screenshotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());
		logger.info("Attaching the Screenshot to the HTML File");
		
		ExtentReportUtility.getTest().addScreenCaptureFromPath(screenshotPath);
	}

	public void onTestSkipped(ITestResult result) {
		// not implemented
		logger.warn(result.getMethod().getMethodName() + "" + "SKIPPED");
		ExtentReportUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");

	}

	public void onStart(ITestContext context) {
		// at the starting of Suite
		logger.info("Test Suite Started");
		ExtentReportUtility.setupSparkReporter("report.html");

	}

	public void onFinish(ITestContext context) {
		// when suite completed
		logger.info("Test Suite completed");
		ExtentReportUtility.flushReport();
	}

//	ExtentSparkReporter extentSparkReporter;
//	ExtentReports extentReports;
//	ExtentTest extentTest;
//	
//	extentSparkReporter = new ExtentSparkReporter();
}
