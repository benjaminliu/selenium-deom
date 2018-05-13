package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ashley on 2018/5/13.
 */
public class HomePage extends PageBase{

    //Find web elements in homepage
    //TODO

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verify() {

        //Verify it's  homepage
        //TODO
    }
}
