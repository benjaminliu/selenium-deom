package com.test.scripts;

import com.test.framework.TestBase;
import com.test.framework.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by admin on 2018/4/30.
 */
public class IETest extends TestBase {

    @Test
    public void test() {
        getDriver().get("http://baidu.com");

        String a = "asdf";
        String b = "asdf";
        Assert.assertEquals(a, b);
    }

    @Test
    public void test1() throws Exception {
        getDriver().get("http://baidu.com");


        String a = "asdf";
        String b = "asdf1";
        Assert.assertEquals(a, b);
    }
}

