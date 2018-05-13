package com.test.selenium.driver;

import com.test.config.Configs;
import com.test.utils.FileUtil;
import com.test.utils.SysUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2018/4/30.
 */
public class BrowserDriver {
    private static Logger logger = LoggerFactory.getLogger(BrowserDriver.class);

    private static int RETRY_TIME = Configs.getInstance().RetryTime;
    private static int RETRY_WAIT = Configs.getInstance().RetryWait;
    private static long TIME_OUT = Configs.getInstance().TimeOut;

    protected WebDriver chromeDriver() throws Exception {
        logger.info("Launch Chrome Browser...");
        String chromeDriverPath = Configs.getInstance().getPropertyString("ChromeDriverPath");
        String chromeDriverAbsolutePath = FileUtil.driverResourcePath(chromeDriverPath);
        System.setProperty("webdriver.chrome.driver", chromeDriverAbsolutePath);

        WebDriver driver = null;
        for (int i = 1; i <= RETRY_TIME; i++) {
            try {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
                break;
            } catch (Exception e) {
                logger.warn("Launch Chrome Browser Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
                SysUtil.sleepSecond(RETRY_WAIT);
                if (i == RETRY_TIME) {
                    throw e;
                }
            }
        }
        return driver;
    }

    protected WebDriver fireFoxDriver() {
        logger.info("Launch Firefox Browser...");
        String firefoxDriverPath = Configs.getInstance().getPropertyString("FirefoxDriverPath");
        String firefoxDriverAbsolutePath = FileUtil.driverResourcePath(firefoxDriverPath);
        System.setProperty("webdriver.gecko.driver", firefoxDriverAbsolutePath);

        WebDriver driver = null;
        for (int i = 1; i <= RETRY_TIME; i++) {
            try {
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                break;
            } catch (Exception e) {
                logger.warn("Launch Firefox Browser Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
                SysUtil.sleepSecond(RETRY_WAIT);
                if (i == RETRY_TIME) {
                    throw e;
                }
            }
        }
        return driver;
    }

    protected WebDriver safariDriver() {
        logger.info("Launch Safari Browser...");
        WebDriver driver = null;
        for (int i = 1; i <= RETRY_TIME; i++) {
            try {
                driver = new SafariDriver();
                driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
                break;
            } catch (Exception e) {
                logger.warn("Launch Safari Browser Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
                SysUtil.sleepSecond(RETRY_WAIT);
                if (i == RETRY_TIME) {
                    throw e;
                }
            }
        }
        return driver;
    }

    protected WebDriver ieDriver() {
        logger.info("Launch IE Browser...");
        String iEDriverPath = Configs.getInstance().getPropertyString("IEDriverPath");
        String iEDriverAbsolutePath = FileUtil.driverResourcePath(iEDriverPath);
        logger.info(iEDriverAbsolutePath);
        System.setProperty("webdriver.ie.driver", iEDriverAbsolutePath);
        WebDriver driver = null;
        for (int i = 1; i <= RETRY_TIME; i++) {
            try {
                driver = new InternetExplorerDriver();
                driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                break;
            } catch (Exception e) {
                logger.warn("Launch IE Browser Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
                SysUtil.sleepSecond(RETRY_WAIT);
                if (i == RETRY_TIME) {
                    throw e;
                }
            }
        }
        return driver;
    }
}
