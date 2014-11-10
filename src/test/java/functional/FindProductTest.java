package functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by bionic on 11/5/14.
 */
public class FindProductTest {

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
    @Test (dataProvider = "filters")
    public void findProduct(String siteURL, String searchQuery)

    {
        driver.get(siteURL);
//        System.out.println( "Opened result page " + driver.getCurrentUrl());
        WebDriverWait wait = new WebDriverWait(driver,5);
        driver.findElement(searchField).sendKeys(searchQuery);
        driver.findElement(searchBtn).submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        System.out.println( "Opened result page " + driver.getCurrentUrl());
//       driver.findElement(By.("//a[contains(text(),'"+searchQuery+"']"));
        Assert.assertTrue(driver.findElement(By.partialLinkText("Nexus")).isDisplayed());

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
