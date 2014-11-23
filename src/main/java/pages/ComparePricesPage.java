package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import selenium.WebDriverWrapper;
import utils.Log4Test;

/**
 * Created by test on 11/12/14.
 */
public class ComparePricesPage extends HomePage {

    private static final By productItem = By.xpath("//a[contains(@href,'/go/price/') and @class='orng']");

    public ComparePricesPage(WebDriverWrapper driver)
    {
        super(driver);
    }
    public int countPriceElements()

    {
        return driver.findElements(productItem).size();
    }

}
