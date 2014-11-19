package functional;

import actors.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.RegistrationPage;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;
import utils.PropertyLoader;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by test on 11/3/14.
 */
public class RegistrationTest {
    public WebDriverWrapper driver;
    @BeforeSuite
    public void setEnv()

    {
        driver = WebDriverFactory.initDriver(PropertyLoader.loadProperty("browser.name"));
        driver.get("http://hotline.ua");
    }
    @DataProvider
    public Object [][] positive() {
        return new Object[][] {
                new Object[] {"Yulia Yurchenko", "12345", "12345"},
        };
    }
    @DataProvider
    public Object [][] negative() {
        return new Object[][] {
                new Object[] {"yulia.ys@ukr.net","Yulia Yurchenko", "12345", "12345"},
                new Object[] {"yulia.y@ukr.net","Yulia Yurchenko", "123", "123"},
                new Object[] {"yulia.s@ukr.net","", "12345", "12345"},
        };
    }


    @Test (dataProvider = "positive")
    public void register(String nick, String passwd, String passwd2)
    {   String randomEmail = "a" + UUID.randomUUID().toString() + "@ukr.net";
        Log4Test.info("Starting registration positive test");

        HomePage homePage = new HomePage(driver);
        homePage.open();
        RegistrationPage registrationPage = homePage.goRegistration();
        User user = new User();
        user.email = randomEmail;
        user.name = nick;
        user.passwd = passwd;
        user.passwd2 = passwd2;
        registrationPage.fillRegistrationForm(user);
        Assert.assertTrue(registrationPage.isRegistered(),"Failed to register");
    }

    @Test (dataProvider = "negative")
    public void failedRegister(String email, String nick, String passwd, String passwd2)
    {
        Log4Test.info("Starting registration negative test");

        HomePage homePage = new HomePage(driver);
        homePage.open();
        RegistrationPage registrationPage = homePage.goRegistration();
        User user = new User();
        user.email = email;
        user.name = nick;
        user.passwd = passwd;
        user.passwd2 = passwd2;
        registrationPage.fillRegistrationForm(user);
        Assert.assertFalse(registrationPage.isRegistered(), "Registration passed in negative test");
    }
    @AfterSuite
      public void closeEnv()
    {
        if (driver!=null)
        {
            driver.quit();
        }
    }

}
