package com.herokuapp.theinternet;

import com.herokuapp.theinternet.util.LoginUtil;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {

    private LoginUtil loginUtil;

    @BeforeTest(alwaysRun = true)
    public void init() {
        loginUtil = new LoginUtil();
        loginUtil.init("");
    }

    @Parameters({"username", "password", "expecteLogindMsg"})
    @Test
    public void incorrectUsernameTest(String username, String password, String expecteLogindMsg) {
        loginUtil.login(username, password);
        loginUtil.loginMsgVerify(expecteLogindMsg);
    }

    @AfterTest(alwaysRun = true)
    public void destroy() {
        loginUtil.destroy();
    }

}
