package functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ComparePricesPage;
import pages.FilterPage;
import pages.GoodsPage;
import pages.HomePage;
import selenium.WebDriverFactory;
import selenium.WebDriverWrapper;
import utils.Log4Test;
import utils.PropertyLoader;

/**
 * Created by test on 11/12/14.
 */
public class FindFridgeTest {



    private static WebDriver driver;

    @BeforeSuite
    public void setEnv()
    {
//        driver = WebDriverFactory.initDriver(PropertyLoader.loadProperty("browser.name"));
        driver = new HtmlUnitDriver();
    }

    @Test
    public void findFridge()

    {
        Log4Test.info("Start test of finding fridge");
        HomePage homePage = new HomePage(driver);
        homePage.open();
        FilterPage filterPage = homePage.findCatalogItem("Бытовая техника", "Холодильники");
        Assert.assertTrue(filterPage.isFilterPageOpened(), "Page 'Холодильники' not found");


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



