package pages;

import actors.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by test on 11/3/14.
 */
public class RegistrationPage extends HomePage {
    public RegistrationPage(WebDriverWrapper driver)
    {
        super(driver);
    }

    private static final By emailField = By.name("email");
    private static final By nickField = By.name("nick");
    private static final By passwordField = By.name("password");
    private static final By confirmPwdField = By.name("password2");
    private static final By registrationBtn = By.xpath("//*[@id = 'reg-form']//*[@class = 'blue-button']");

    public void fillRegistrationForm(User user)
    {
        driver.findElement(nickField).clear();
        driver.findElement(emailField).sendKeys(user.email);
        Log4Test.info("Entered email: " + user.email);

        driver.findElement(nickField).clear();
        driver.findElement(nickField).sendKeys(user.name);
        Log4Test.info( "Entered nick: " + user.name);

        driver.findElement(passwordField).sendKeys(user.passwd);
        Log4Test.info( "Entered password: " + user.passwd);

        driver.findElement(confirmPwdField).sendKeys(user.passwd2);
        Log4Test.info( "Confirm password: " + user.passwd2);

        driver.findElement(registrationBtn).click();
       driver.manage().timeouts().implicitlyWait(WebDriverWrapper.TIME_TO_WAIT, TimeUnit.SECONDS);

        Log4Test.info(driver.getCurrentUrl());

    }
    public boolean isRegistered()
    {
        return driver.getCurrentUrl().contains("/final/");

    }

}
