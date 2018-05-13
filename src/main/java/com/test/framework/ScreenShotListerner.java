package com.test.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by ben on 2018/5/13.
 */
public class ScreenShotListerner extends TestListenerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ScreenShotListerner.class);

    private String getTestName(ITestResult iTestResult) {
        if (null == iTestResult)
            return null;

        String className = iTestResult.getTestClass().getName();
        String methodName = iTestResult.getMethod().getMethodName();
        return className + "_" + methodName;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        super.onTestStart(iTestResult);
        logger.info(getTestName(iTestResult) + " started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        super.onTestSuccess(iTestResult);
        String testName = getTestName(iTestResult);
        logger.info(testName + " success");

        TestBase testBase = (TestBase) iTestResult.getInstance();
        testBase.takeScreenShot(testName);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        super.onTestSkipped(iTestResult);
        logger.info(getTestName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String testName = getTestName(iTestResult);
        logger.info(testName + " failed");

        TestBase testBase = (TestBase) iTestResult.getInstance();
        testBase.takeScreenShot(testName);
    }
}
