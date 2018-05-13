package com.test.scripts;

import com.test.framework.TestBase;
import com.test.framework.TestData;
import com.test.pages.HomePage;
import com.test.pages.LogInPage;
import org.testng.annotations.Test;

/**
 * Created by ashley on 2018/5/13.
 */
public class LoginTest extends TestBase {

    @Test
    public void test_login() {

        //Get test data from data file
        TestData testData = loadData("...xls", "...");
        String userName = testData.getData(1, 1);
        String pwd = testData.getData(1, 2);

        //Create page object
        LogInPage logInPage = new LogInPage(getDriver());

        //login and goto homepage
        HomePage homePage = logInPage.login(userName, pwd);

        // Verify it's homepage ---TODO
        homePage.verify();

    }

    @Test
    public void test_login_negative(){

    }
}
