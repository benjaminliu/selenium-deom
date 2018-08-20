package com.test.scripts;

import com.test.framework.TestBase;
import com.test.pages.LogInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Created by admin on 2018/4/30.
 */
public class ChromeTest1 extends TestBase {
    @Test
    public void test() {
        getDriver().get("http://test.huali-tec.com/msp/#/login");
        LogInPage logInPage = new LogInPage(getDriver());
        logInPage.login("asdf","asdf");

    }
}
