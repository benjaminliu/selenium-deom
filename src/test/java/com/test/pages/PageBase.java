package com.test.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by ashley on 2018/5/13.
 */
public abstract class PageBase {

    public int TimeoutValue = 30;

    protected WebDriver driver;

    protected abstract void verify() throws Exception;
}
