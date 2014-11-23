package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by bionic on 11/5/14.
 */
public class GoodsPage extends HomePage {
    private static  final By comparePricesBtn = By.className("but-box");


    public GoodsPage(WebDriverWrapper driver)
    {
        super(driver);
    }

    public boolean isFound(String product)
    {
        final By productToSearch = By.xpath("//a[contains(text(), '" + product + "')]");

        try
        {
            return driver.findElement(productToSearch).isDisplayed();

        }
        catch(TimeoutException ex)
        {
            Log4Test.info("Couldn't find element. Timeout exception: " + ex.getMessage());
            return false;
        }
    }
    public ComparePricesPage firstItemComparePrices()
    {
        driver.findElement(comparePricesBtn).click();
        Log4Test.info("Opening page with prices: " + driver.getCurrentUrl());
        driver.manage().timeouts().implicitlyWait(WebDriverWrapper.TIME_TO_WAIT, TimeUnit.SECONDS);
        return new ComparePricesPage(driver);
     }

}
