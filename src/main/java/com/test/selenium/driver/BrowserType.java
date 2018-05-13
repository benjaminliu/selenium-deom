package com.test.selenium.driver;

/**
 * Created by admin on 2018/4/30.
 */
public enum BrowserType {
    CHROME, FIREFOX, SAFARI, IE;

    public static BrowserType getType(String type) {
        switch (type.trim().toLowerCase()) {
            case "firefox":
                return FIREFOX;
            case "ie":
                return IE;
            case "chrome":
                return CHROME;
            case "safari":
                return SAFARI;
            default:
                return FIREFOX;
        }
    }
}
