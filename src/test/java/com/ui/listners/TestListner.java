package com.ui.listners;

import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuiteListener;
import org.testng.ISuite;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListner implements ITestListener, ISuiteListener {
    private final Logger logger = LoggerUtility.getLogger(this.getClass());

  
    public void onStart(ISuite suite) {
        logger.info("Test Suite Started: " + suite.getName());
        ExtentReporterUtility.setupSparkReporter("report.html");
    }

   
    public void onTestStart(ITestResult result) {
        logger.info("Starting Test: " + result.getMethod().getMethodName());
        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
        ExtentReporterUtility.getTest().info("Test started: " + result.getMethod().getDescription());
    }


    public void onTestSuccess(ITestResult result) {
        logger.info("Test PASSED: " + result.getMethod().getMethodName());
        ExtentReporterUtility.getTest().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
    }

   
    public void onTestFailure(ITestResult result) {
        logger.error("Test FAILED: " + result.getMethod().getMethodName());
        logger.error("Exception: ", result.getThrowable()); // Logs full stack trace

        ExtentTest test = ExtentReporterUtility.getTest();
        if (test != null) {
            test.log(Status.FAIL, "Test failed: " + result.getThrowable());
        } else {
            logger.error("ExtentTest is null. Test failure could not be logged.");
        }
       Object testClass=result.getInstance();
        BrowserUtility browserUtility=((TestBase)testClass).getInstance();
        String screenshotPath=browserUtility.takeScreenShot(result.getMethod().getMethodName());
        ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test SKIPPED: " + result.getMethod().getMethodName());
        ExtentTest test = ExtentReporterUtility.getTest();
        if (test != null) {
            test.log(Status.SKIP, "Test skipped: " + (result.getThrowable() != null 
                ? result.getThrowable().getMessage() : "No reason provided"));
        } else {
            logger.error("ExtentTest is null. Test skip could not be logged.");
        }
    }

    
    public void onFinish(ISuite suite) {
        logger.info("Test Suite Completed: " + suite.getName());
        ExtentReporterUtility.flushReport();
    }
}
