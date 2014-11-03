package functional;

import actors.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoggedInPage;
import pages.RegistrationPage;

/**
 * Created by test on 11/3/14.
 */
public class RegistrationTest {
    @Test
public void fillFormTest(User user)
    {
        HomePage homePage = new HomePage();
        homePage.registration();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.registrationForm(User user);
        registrationPage.registerButton();

        if (registrationPage.isSuccess())
        {
            LoggedInPage loggedInPage = new LoggedInPage()
        }
        Assert.assertTrue(registrationPage.isSuccess());
    }

}
