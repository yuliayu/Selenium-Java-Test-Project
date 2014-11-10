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
                new Object[] {"http://hotline.ua", "Nexus"},

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

        driver.get(siteURL);
        System.out.println( "Opened result page " + driver.getCurrentUrl());
        WebDriverWait wait = new WebDriverWait(driver,5);
        driver.findElement(searchField).sendKeys(searchQuery);
        driver.findElement(searchBtn).submit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.className("but-box")).click();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        int sum = driver.findElements(By.xpath("//a[contains(@href,'/go/price/') and @class='orng']")).size();
        Assert.assertTrue(sum >= 2);
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
