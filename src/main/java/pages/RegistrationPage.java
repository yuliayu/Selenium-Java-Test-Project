package pages;

import actors.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Log4Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by test on 11/3/14.
 */
public class RegistrationPage extends HomePage {
    public RegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    public void fillRegistrationForm(User user)
    {

       driver.findElement(By.name("email")).sendKeys(user.email);
        Log4Test.info("Entered email: " + user.email);

        driver.findElement(By.name("nick")).clear();
        driver.findElement(By.name("nick")).sendKeys(user.name);
        Log4Test.info( "Entered nick: " + user.name);

        driver.findElement(By.name("password")).sendKeys(user.passwd);
        Log4Test.info( "Entered password: " + user.passwd);

        driver.findElement(By.name("password2")).sendKeys(user.passwd2);
        Log4Test.info( "Confirm password: " + user.passwd2);

        driver.findElement(By.id("reg-form")).submit();
       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Log4Test.info(driver.getCurrentUrl());

    }
    public boolean isRegistered()
    {
        return driver.getCurrentUrl().contains("/final/");

    }

}
