package functional;
import actors.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasketPage;
import pages.HomePage;
import pages.LoginRegisterPage;

import java.util.HashMap;

/**
 * Created by bionic on 10/29/14.
 */
public class BuyProductTest {
    @Test
    public void buySingleProduct(HashMap<String,Double> product, User user)
    {
        HomePage homePage = new HomePage();
        homePage.addToBasket(product);
        homePage.openBasket();
        BasketPage basketPage = new BasketPage();
        basketPage.buy();
        if (!basketPage.isSuccess()) {
            LoginRegisterPage loginPage = new LoginRegisterPage();
            loginPage.login(user);
        }
        Assert.assertTrue(basketPage.isSuccess());
    }
}
