package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.nio.file.Paths;

public class ExtentReporterUtility {
    private static ExtentSparkReporter extentSparkReporter;
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(ExtentReporterUtility.class);

    // Setup the Spark Reporter
    public static void setupSparkReporter(String reportName) {
        if (reportName == null || reportName.trim().isEmpty()) {
            logger.error("Report name is null or empty. Cannot initialize ExtentSparkReporter.");
            return;
        }

        try {
            String reportPath = Paths.get(System.getProperty("user.dir"), reportName).toString();
            extentSparkReporter = new ExtentSparkReporter(reportPath);
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);

            logger.info("ExtentReports setup completed. Report will be generated at: " + reportPath);
        } catch (Exception e) {
            logger.error("Failed to setup ExtentSparkReporter", e);
        }
    }

    // Create a new test case
    public static void createExtentTest(String testName) {
        if (extentReports == null) {
            logger.error("ExtentReports is not initialized. Call setupSparkReporter() before creating tests.");
            return;
        }
        if (testName == null || testName.trim().isEmpty()) {
            logger.error("Test name is null or empty. Cannot create ExtentTest.");
            return;
        }

        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
        logger.info("ExtentTest created for: " + testName);
    }

    // Get the current test case
    public static ExtentTest getTest() {
        ExtentTest test = extentTest.get();
        if (test == null) {
            logger.warn("No ExtentTest is associated with the current thread.");
        }
        return test;
    }

    // Flush the report
    public static void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
            logger.info("ExtentReports flushed.");
        } else {
            logger.error("Cannot flush ExtentReports as it is not initialized.");
        }
    }

    // Clear ThreadLocal to prevent memory leaks
    public static void clearTest() {
        extentTest.remove();
        logger.info("Cleared ThreadLocal ExtentTest for the current thread.");
    }
}


