package functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.Log4Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by test on 11/10/14.
 */
public class ComparePricesTest {

    private static WebDriver driver;
    private static final By searchField = By.id("searchbox");
    private static final By searchBtn = By.id("doSearch");

    @DataProvider
    public Object [][] filters() {
        return new Object[][] {
                new Object[] {"http://hotline.ua", "Newdwe"},

        };
    }


    @BeforeSuite
    public void setEnv()
    {
        driver = new FirefoxDriver();
    }
    // @Parameters({ "siteURL", "searchField"})
    @Test(dataProvider = "filters")
    public void comparePrices(String siteURL, String searchQuery)

    {
        Log4Test.info("Start test of prices comparing");
        driver.get(siteURL);
        Log4Test.info("Opening URL: " + siteURL );
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(searchField).sendKeys(searchQuery);
        driver.findElement(searchBtn).submit();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Assert.assertTrue((driver.findElement(By.partialLinkText(searchQuery)).isDisplayed()), Log4Test.error("Couldn't find product" + searchQuery));
        Log4Test.info("Searching for product:" + searchQuery + "on: " + driver.getCurrentUrl());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.className("but-box")).click();
        Log4Test.info("Opening page with prices: " + driver.getCurrentUrl());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        int sum = driver.findElements(By.xpath("//a[contains(@href,'/go/price/') and @class='orng']")).size();
        Assert.assertTrue((sum >= 2), Log4Test.error("Test passed, number of prices on page is : " + sum));
/*
        if (sum >= 2)
        {System.out.println("Passed");}

        else System.out.println("Failed");
*/
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
