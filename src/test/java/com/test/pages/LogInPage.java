package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ashley on 2018/5/13.
 */
public class LogInPage extends PageBase{

    //Capture web elements
    @FindBy(xpath = "...")
    private WebElement userNameText;

    @FindBy(xpath = "...")
    private WebElement passwordText;

    @FindBy(xpath = "...")
    private WebElement loginButton;

    @FindBy(xpath = "...")
    private WebElement cancelButton;

    //Initial page elements
    public LogInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Action - input username and pwd, login, then go to homepage
    public HomePage login(String userName, String pwd){

        //Input username and pwd
        userNameText.sendKeys(userName);
        passwordText.sendKeys(pwd);

        //Click login
        loginButton.click();

        //return homepage
        return new HomePage(driver);

    }


    @Override
    protected void verify() throws Exception {

        //Verify it's login page
        //TODO
    }
}
