package functional;

import actors.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoggedInPage;
import pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by test on 11/3/14.
 */
public class RegistrationTest {
    private WebDriver driver;
    @DataProvider
    public Object [][] regtests() {
        return new Object[][] {
                new Object[] {"yulia.ys@ukr.net", "Yulia Yurchenko", "12345", "12345"},
                new Object[] {"invalidmail@", "Yulia", "1234", "1234"},
                new Object[] {"validmail@gmail.com", "", "1234", "1234"},
                new Object[] {"validmail@mail.ru", "TEst Invalid Password", "123", "123"},
        };
    }
    @BeforeSuite
    public void setEnv()
    {
        driver = new HtmlUnitDriver();
    }
    @Test (dataProvider = "regtests")
    public void register(String email, String nick, String passwd, String passwd2)
    {

        driver.get("http://hotline.ua/");
        WebElement elementRegisterButton = driver.findElement(By.className("reg"));
        elementRegisterButton.click();
        System.out.println( "Opened page " + driver.getCurrentUrl());
        WebElement elementEmail =  driver.findElement(By.name("email"));
        elementEmail.sendKeys(email);
        System.out.println( "Entered email: " + email);
        WebElement elementNick = driver.findElement(By.name("nick"));
        elementNick.sendKeys(nick);
        System.out.println( "Entered nick: " + nick);
        WebElement elementPasswd =  driver.findElement(By.name("password"));
        elementPasswd.sendKeys(passwd);
        System.out.println( "Entered password: " + passwd);
        WebElement elementPasswd2 =  driver.findElement(By.name("password2"));
        elementPasswd2.sendKeys(passwd2);
        System.out.println( "Confirm password: " + passwd2);
        WebElement elementForm =  driver.findElement(By.id("reg-form"));
        elementForm.submit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println( "Opened result page " + driver.getCurrentUrl());


        Assert.assertFalse(driver.getCurrentUrl().contains("/final/"));


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
