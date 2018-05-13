package com.test.scripts;

import com.test.framework.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by admin on 2018/4/30.
 */
public class FirefoxTest1 extends TestBase {
    @Test
    public void test() {
        getDriver().get("http://baidu.com");
    }
}
