package com.bstack.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    // ThreadLocal to handle parallel execution
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getReporter() {
        if (extent == null) {
            // Path and configuration
            String path = "test-output/ExtentReports/AutomationReport1.html";
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
            htmlReporter.config().setDocumentTitle("BStackDemo Automation Report");
            htmlReporter.config().setReportName("Functional Test Execution Results");
            htmlReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            
            // System information
            extent.setSystemInfo("Machine name:", "Local Host");
            extent.setSystemInfo("Tester name:", "Rajalakshmi");
            extent.setSystemInfo("OS", "Windows");
            extent.setSystemInfo("Browser", "Google Chrome");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest newTest = getReporter().createTest(testName);
        test.set(newTest);
        return newTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void logPass(String message) {
        getTest().log(Status.PASS, message);
    }

    public static void logFail(String message) {
        getTest().log(Status.FAIL, message);
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
