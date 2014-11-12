package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Log4Test;

/**
 * Created by test on 11/12/14.
 */
public class ComparePricesPage extends HomePage {

    public ComparePricesPage(WebDriver driver)
    {
        super(driver);
    }
    public int countPriceElements()
    {
        return driver.findElements(By.xpath("//a[contains(@href,'/go/price/') and @class='orng']")).size();
    }



}
