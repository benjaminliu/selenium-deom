package com.test.scripts;

import com.test.framework.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Created by admin on 2018/4/30.
 */
public class ChromeTest1 extends TestBase{
    @Test
    public void test() {
        getDriver().get("http://baidu.com");
    }
}
