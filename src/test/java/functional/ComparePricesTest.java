package functional;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ComparePricesPage;
import pages.GoodsPage;
import pages.HomePage;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;
import utils.PropertyLoader;

/**
 * Created by test on 11/10/14.
 */
public class ComparePricesTest extends BaseTest{

    @DataProvider
    public Object [][] filters() {
        return new Object[][] {
                new Object[] {"Nexus"},

        };
    }

    @Test(dataProvider = "filters")
    public void comparePrices(String searchQuery)

    {
        Log4Test.info("-------------Starting test of prices comparing-------------");
        HomePage homePage = new HomePage(driver);
        homePage.open();
        GoodsPage goodsPage = homePage.findProduct(searchQuery);
        Assert.assertTrue(goodsPage.isFound(searchQuery));
        ComparePricesPage comparePrices = goodsPage.firstItemComparePrices();
        int sum = comparePrices.countPriceElements();
        Assert.assertTrue((sum >= 2), "Test failed, number of price elements " + sum);
        Log4Test.info("Test passed, number of prices on page is : " + sum);

    }
}
