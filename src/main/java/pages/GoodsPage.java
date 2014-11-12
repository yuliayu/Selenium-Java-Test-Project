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
    public GoodsPage(WebDriver driver)
    {
        super(driver);
    }

    public boolean isFound(String product)
    {
        try
        {
            return driver.findElement(By.xpath("//a[contains(text(), '" + product + "')]")).isDisplayed();

        }
        catch(TimeoutException ex)
        {
            Log4Test.info("Couldn't find element. Timeout exception: " + ex.getMessage());
            return false;
        }
    }
    public ComparePricesPage firstItemComparePrices()
    {
        driver.findElement(By.className("but-box")).click();
        Log4Test.info("Opening page with prices: " + driver.getCurrentUrl());
        driver.manage().timeouts().implicitlyWait(WebDriverWrapper.TIME_TO_WAIT, TimeUnit.SECONDS);
        return new ComparePricesPage(driver);
     }

}
