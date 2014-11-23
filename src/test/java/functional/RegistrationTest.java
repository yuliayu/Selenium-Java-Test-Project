package functional;

import actors.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;
import utils.Log4Test;

import java.util.UUID;

/**
 * Created by test on 11/3/14.
 */
public class RegistrationTest extends BaseTest {
    @DataProvider
    public Object [][] positive() {
        return new Object[][] {
                new Object[] {"Test Account", "12345", "12345"},
        };
    }
    @DataProvider
    public Object [][] negative() {
        return new Object[][] {
                new Object[] {"yulia.ys@ukr.net","Test Account", "12345", "12345"},
        };
    }
    @Test (dataProvider = "positive")
    public void register(String nick, String passwd, String passwd2)
    {
        String randomEmail = "a" + UUID.randomUUID().toString() + "@ukr.net";
        Log4Test.info("-------------Starting registration positive test-------------");

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
        Log4Test.info("Registration is successful");
    }

    @Test (dataProvider = "negative")
    public void failedRegister(String email, String nick, String passwd, String passwd2)
    {
        Log4Test.info("-------------Starting registration negative test-------------");

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
        Log4Test.info("Negative registration test passed");
    }
}
