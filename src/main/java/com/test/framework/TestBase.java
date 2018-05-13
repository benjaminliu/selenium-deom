package com.test.framework;

import com.test.selenium.driver.DriverSelector;
import com.test.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2018/5/1.
 */
@Listeners(ScreenShotListerner.class)
public abstract class TestBase {
    private static Logger logger = LoggerFactory.getLogger(TestBase.class);

    private WebDriver driver;
    private TestData data;

    public WebDriver getDriver() {
        return driver;
    }

    public TestData getData() {
        return data;
    }

    @BeforeMethod
    @Parameters("browser")
    public void startBrowser(@Optional("firefox") String browser) {
        driver = new DriverSelector().setDriver(browser);

        beforeTest();
    }

    @AfterMethod
    public void stopBrowser() {
        afterTest();

        closeDriver();
    }

    public void closeDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    public String takeScreenShot(String name) {
        if (StringUtils.isBlank(name))
            return null;
        try {
            String fileFullPath = FileUtil.saveScreenShot(driver, name);
            return fileFullPath;
        } catch (Exception e) {
            logger.error("截图时出错", e);
        }
        return null;
    }

    protected void beforeTest() {
    }

    protected void afterTest() {
    }

    protected void shortWait() throws Exception {
        Thread.sleep(1);
    }

    protected void middleWait() throws Exception {
        Thread.sleep(5);
    }

    protected void longWait() throws Exception {
        Thread.sleep(10);
    }

    protected TestData loadData(String fileName, String sheetName) {
        data = new TestData(fileName, sheetName);
        return data;
    }
}

