package functional;

import actors.Product;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.FilterPage;
import pages.HomePage;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;
import utils.PropertyLoader;

/**
 * Created by test on 11/12/14.
 */
public class FindFridgeTest {


public static final String testFilterName = "LG";
    private static WebDriverWrapper driver;

    @BeforeSuite
    public void setEnv()
    {
       driver = WebDriverFactory.initDriver(PropertyLoader.loadProperty("browser.name"));
//        driver = new HtmlUnitDriver();
    }

    @Test
    public void findFridge()

    {
        Log4Test.info("Start test of finding fridge");
        HomePage homePage = new HomePage(driver);
        homePage.open();
        FilterPage filterPage = homePage.findFridgeItem();
        filterPage.addFilter(testFilterName);
        filterPage.sortByPrice();
        Assert.assertTrue(filterPage.isFridgeFilterPageOpened(), "Page 'Холодильники' not found");
        Product firstItem = filterPage.getProductByIndex(1);
        Product secondItem =  filterPage.getProductByIndex(2);
        Assert.assertTrue(firstItem.name.toUpperCase().contains(testFilterName), "Fail to find " +testFilterName);
        Assert.assertTrue(secondItem.name.toUpperCase().contains(testFilterName),"Fail to find " + testFilterName);
        Assert.assertTrue(fetchPrice(firstItem) < fetchPrice(secondItem),"Sorting is incorrect");


    }
    private int fetchPrice(Product product)
    {
        String price = product.price;
        String splitPrices [] = price.split("грн");
        String removeSpace = splitPrices[0].replace(" ","");
        Log4Test.info("Price (string) is " + removeSpace);
        int finalPrice = Integer.parseInt(removeSpace);
        Log4Test.info("Final price (int) is " + finalPrice);
        return finalPrice;
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


