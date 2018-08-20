package com.test.pages;

import com.test.utils.SysUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * Created by ashley on 2018/5/13.
 */
public class LogInPage extends PageBase {

    //Capture web elements
    @FindBy(css = "input[placeholder=Email]")
    private WebElement userNameText;

    @FindBy(css = "input[placeholder=Password]")
    private WebElement passwordText;

    @FindBy(className = "el-button--primary")
    private WebElement loginButton;

    //Initial page elements
    public LogInPage(WebDriver driver) {
        this.driver = driver;

        //有超时等待时间
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TimeoutValue), this);
    }

    // Action - input username and pwd, login, then go to homepage
    public HomePage login(String userName, String pwd) {

        //Input username and pwd
        userNameText.sendKeys(userName);
        SysUtil.sleepSecond(1);
        passwordText.sendKeys(pwd);
        SysUtil.sleepSecond(3);

        //Click login
        loginButton.click();
        SysUtil.sleepSecond(3);

        //return homepage
        return new HomePage(driver);
    }


    @Override
    protected void verify() throws Exception {

        //Verify it's login page
        //TODO
    }
}
