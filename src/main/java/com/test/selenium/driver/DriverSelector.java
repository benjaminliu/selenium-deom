package com.test.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 2018/5/1.
 */
public class DriverSelector {
    private static Logger logger = LoggerFactory.getLogger(DriverSelector.class);

    public WebDriver setDriver(String browserTypeStr) {
        BrowserType browserType = BrowserType.getType(browserTypeStr);
        BrowserDriver browser = new BrowserDriver();
        WebDriver driver = null;
        try {
            switch (browserType) {
                case CHROME:
                    driver = browser.chromeDriver();
                    logger.info("Launch Chrome Browser Complete");
                    return driver;
                case FIREFOX:
                    driver = browser.fireFoxDriver();
                    logger.info("Launch Firefox Browser Complete");
                    return driver;
                case SAFARI:
                    driver = browser.safariDriver();
                    logger.info("Launch Safari Browser Complete");
                    return driver;
                case IE:
                    driver = browser.ieDriver();
                    logger.info("Launch IE Browser Complete");
                    return driver;
                default:
                    logger.info("Please Check the Type of Browser Driver");
                    return driver;
            }
        } catch (Exception e) {
            logger.error("Launch Browser Failed", e);
            return null;
        }
    }
}
