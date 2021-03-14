package com.herokuapp.theinternet;

import com.herokuapp.theinternet.util.LoginUtil;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PositiveTests {

    private LoginUtil loginUtil;

    @BeforeTest(alwaysRun = true)
    public void init() {
        loginUtil = new LoginUtil();
        loginUtil.init("");
    }

    @Parameters({"username", "password", "expectedUrl", "expectedSecureMsg"})
    @Test
    public void loginTest(String username, String password, String expectedUrl, String expectedSecureMsg) {
        loginUtil.login(username, password);
        loginUtil.login2Secure(expectedUrl, expectedSecureMsg);
    }

    @AfterTest(alwaysRun = true)
    public void destroy() {
        loginUtil.destroy();
    }

}
