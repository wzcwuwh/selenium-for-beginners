package com.herokuapp.theinternet.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;

import java.time.Duration;

public class LoginUtil {

    private WebDriver driver;
    private WebDriverWait wait;

    public void login(String username, String password) {

        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);

        wait.until(ExpectedConditions.urlMatches(driver.getCurrentUrl()));

        WebElement usernameElement = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        wait.until(ExpectedConditions.visibilityOf(usernameElement));
        usernameElement.sendKeys(username);

        WebElement passwordElement = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        wait.until(ExpectedConditions.visibilityOf(passwordElement));
        passwordElement.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button"));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
    }

    public void loginMsgVerify(String expectedLoginMsg) {
        WebElement loginMsg = driver.findElement(By.xpath("//*[@id=\"flash\"]"));
        String actualMsg = loginMsg.getText();
        Assert.assertTrue(actualMsg.contains(expectedLoginMsg));
    }

    public void login2Secure(String expectedUrl, String expectedSecureMsg) {
        wait.until(ExpectedConditions.urlMatches(expectedUrl));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        WebElement logoutBtn = driver.findElement(By.xpath("/html/body/div[2]/div/div/a"));
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));
        Assert.assertTrue(logoutBtn.isDisplayed());

        WebElement welcomeMsg = driver.findElement(By.xpath("//*[@id=\"flash\"]"));
        wait.until(ExpectedConditions.visibilityOf(welcomeMsg));
        String actualMsg = welcomeMsg.getText();
        Assert.assertTrue(actualMsg.contains(expectedSecureMsg));
    }

    public void init(@Optional("chrome") String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
                break;
            case "chrome" :

            default :
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver = new ChromeDriver();
                break;
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(5).getSeconds());
        driver.manage().window().maximize();
    }

    public void destroy() {
        driver.quit();
    }

}
