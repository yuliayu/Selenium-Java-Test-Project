package pages;

import actors.Product;
import org.openqa.selenium.By;
import selenium.WebDriverWrapper;
import utils.Log4Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by test on 11/12/14.
 */
public class FilterPage extends HomePage
{
    private static final By sortBox = By.xpath("//*[contains(@class,'sorter-new')]/*[@class='box'][1]//*[@class='ddopener']");
    private static  final By sortTypeByPrice = By.xpath("//a[contains(@href,'sort=0')]");

    public FilterPage(WebDriverWrapper driver)
    {
        super(driver);
    }

    public void addFilter(String filterName)
    {
        final By filter = By.xpath("//*[@id='filters']//a[contains(text(), '" + filterName + "')]");

        Log4Test.info("Selecting of " +filterName+ " filter");
        driver.findElement(filter).click();
        driver.manage().timeouts().implicitlyWait(WebDriverWrapper.TIME_TO_WAIT, TimeUnit.SECONDS);
    }
    public void sortByPrice()
    {
        Log4Test.info("Opening sorting menu...");
        driver.findElement(sortBox).click();
        driver.findElement(sortTypeByPrice).click();
    }
    public boolean isFridgeFilterPageOpened()
    {
        return driver.getCurrentUrl().contains("/holodilniki/");
    }
    public Product getProductByIndex(int index)
    {
        final By productName = By.xpath("//ul[contains(@class,'catalog')]/li["+index+"]//div[@class = 'title-box']//a");
        final By productPrice = By.xpath("//ul[contains(@class,'catalog')]/li["+index+"]//div[@class = 'price']//span[@class= 'orng']");

        Product product = new Product();
        product.name = driver.findElement(productName).getText();
        product.price = driver.findElement(productPrice).getText();
        Log4Test.info("Found product index : " + index + " name: " + product.name + " price: " +product.price );
        return product;

    }

}
